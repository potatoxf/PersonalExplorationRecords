import {assign} from "./_.js";
import {desc} from "./_.js";
import {mergeArray} from "./arr.js";

/*********************************************************************************/
/*                                     NUM                                       */
/*********************************************************************************/

const REG_NUMBER = /^([+-])?0*(\d+)(\.(\d+))?$/;

const REG_E = /^([+-])?0*(\d+)(\.(\d+))?e(([+-])?(\d+))$/i

export const gcd = function (a, b) {
    for (let temp = b; b; b = a % temp, a = temp) temp = b;
    return a;
};
desc(gcd, '最大公约数', '一个数字', '另一个数字');

export const lcm = function (a, b) {
    return (a * b) / gcd(a, b);
}
desc(lcm, '最小公倍数', '一个数字', '另一个数字');

export const mumOfPower2 = function (value) {
    if (typeof value === 'number' && value % 2 === 0) {
        let i = 0;
        for (; value !== 1; value >>>= 1) i++;
        return i;
    }
    throw Error('Error value');
}
desc(mumOfPower2, '2的多少次方', '一个数字');

export const e2ten = function (num) {
    let result = REG_E.exec(num.toString());
    if (!result) return num;
    let zs = result[2]
        , xs = result[4] || ""
        , e = result[5] ? +result[5] : 0;
    if (e > 0) {
        let _zs = xs.slice(0, e);
        _zs = _zs.length < e ? _zs + new Array(e - _zs.length + 1).join("0") : _zs;
        xs = xs.slice(e);
        zs += _zs;
    } else {
        e = -e;
        let s_start = zs.length - e;
        s_start = s_start < 0 ? 0 : s_start;
        let _xs = zs.slice(s_start, e);
        _xs = _xs.length < e ? new Array(e - _xs.length + 1).join("0") + _xs : _xs;
        zs = zs.substring(0, s_start);
        xs = _xs + xs;
    }
    zs = zs === "" ? "0" : zs;
    return (result[1] === "-" ? "-" : "") + zs + (xs ? "." + xs : "");
}
desc(e2ten, '科学计数法转十进制', '科学记数法字符串');

export const getNumbResult = function (num) {
    let result = REG_NUMBER.exec(num.toString());
    if (!result && REG_E.test(num.toString())) {
        result = REG_NUMBER.exec(e2ten(num.toString()))
    }
    if (result) {
        return {
            int: result[2],
            decimal: result[4],
            minus: result[1] === "-",
            num: result.slice(1, 3).join('')
        }
    }
}
desc(getNumbResult, '分析数字字符串', '数字字符串');

/**
 *
 *
 * @param {number} index
 */
export const getDigit = function (index) {
    return index >= 5 ? (index - 4) * 4 + 4 : index;
}
desc(getDigit, '获取真实数位', '中文单位的索引');

export const unshiftZero = function (arr, n) {
    if (n === null) n = 1;
    if (n <= 0) return;
    for (; n--;) arr.unshift(0);
}
desc(getDigit, '往数组头部插入0', '数组', '长度');

/*********************************************************************************/
/*                                     STR                                       */
/*********************************************************************************/

export const trim = function (str, trim, type) {
    if (!str) return "";
    if (!(typeof str !== "string")) str = str.toString();
    let exp = "";
    for (let i = 0; i < trim.length; i++) {
        exp += ("*.?+$^[](){}|\\/".indexOf(trim[i]) >= 0 ? "\\" + trim[i] : trim[i]);
    }
    exp = `[${exp}]`;
    if (type.indexOf("^") >= 0)
        str = str.replace(new RegExp(`^${exp}+`), "");
    if (type.indexOf("$") >= 0)
        str = str.replace(new RegExp(`${exp}+$`), "");
    if (type.indexOf("1") >= 0) {
        if (trim.length === 1) {
            str = str.replace(new RegExp(exp + "{2}", "g"), trim);
        } else if (trim.length === 2 && trim[0] === "\\") {
            str = str.replace(new RegExp(exp + "{2}", "g"), trim[1]);
        }
    }
    return str;
};
desc(trim, '清理字符串', '字符串', '要清理字符', '包括^，清理开头字符, 包含$，清理结尾字符, 包含1，多个连续变一个');


export const utf8 = function (value, encode) {
    const type = typeof value;
    if (encode) {
        if (type !== 'string') {
            throw Error(`Error value type "${type}"`);
        }
        let result = []
        for (let i = 0; i < value.length; i++) {
            let code = value.charCodeAt(i)
            if (code > 1114111) throw Error();
            if (code >= 65536 && code <= 1114111) {// 位运算， 补齐8位
                result.push((code >> 18) | 0xf0)
                result.push(((code >> 12) & 0x3f) | 0x80)
                result.push(((code >> 6) & 0x3f) | 0x80)
                result.push((code & 0x3f) | 0x80)
            } else if (code >= 2048 && code <= 65535) {
                result.push((code >> 12) | 0xe0)
                result.push(((code >> 6) & 0x3f) | 0x80)
                result.push((code & 0x3f) | 0x80)
            } else if (code >= 128 && code <= 2047) {
                result.push((code >> 6) | 0xc0)
                result.push((code & 0x3f) | 0x80)
            } else {
                result.push(code)
            }
        }
        return result
    } else {
        if (!('length' in value)) {
            throw Error(`Error value type "${type}"`);
        }
        let result = '';
        const prefix = '000000';
        for (let i = 0; i < value.length;) {
            let code = value[i]
            if (!(typeof value === 'number' && value >= 0 && value < 255)) {
                throw Error();
            }
            let code1, code2, code3, code4, hex
            if ((code & 240) === 240) {
                code1 = (code & 0x07).toString(2)
                code2 = (prefix + (value[i + 1] & 0x3f).toString(2)).slice(-6);
                code3 = (prefix + (value[i + 2] & 0x3f).toString(2)).slice(-6);
                code4 = (prefix + (value[i + 3] & 0x3f).toString(2)).slice(-6);
                hex = parseInt((code1 + code2 + code3 + code4), 2)
                result = result + String.fromCharCode(hex)
                i = i + 4
            } else if ((code & 224) === 224) {
                code1 = (code & 0x0F).toString(2)
                code2 = (prefix + (value[i + 1] & 0x3f).toString(2)).slice(-6);
                code3 = (prefix + (value[i + 2] & 0x3f).toString(2)).slice(-6);
                hex = parseInt((code1 + code2 + code3), 2)
                result = result + String.fromCharCode(hex)
                i = i + 3
            } else if ((code & 192) === 192) {
                code1 = (code & 0x1F).toString(2)
                code2 = (prefix + (value[i + 1] & 0x3f).toString(2)).slice(-6);
                hex = parseInt((value + code2), 2)
                result = result + String.fromCharCode(hex)
                i = i + 2
            } else if ((code & 128) === 0) {
                hex = code
                result = result + String.fromCharCode(code)
                i = i + 1
            } else {
                throw Error();
            }
        }
        return result
    }
};
desc(utf8, 'utf8编码转换', '编码，输入字符串；解码，输入字节数组；', 'true：编码，false：解码');

export const utf8WithHex = function (value, encode) {
    if (encode) {
        return hex(utf8(value, true), true);
    } else {
        return utf8(hex(value, false), false);
    }
};
desc(utf8WithHex, '用base64编解码urf8字符串', '编码，输入字符串；解码，输入字节数组；', 'true：编码，false：解码');

export const utf8WithBase64 = function (value, encode, select = 0) {
    if (encode) {
        return base64(utf8(value, true), select);
    } else {
        return utf8(base64(value, select), false);
    }
};
desc(utf8WithBase64, '用base64编解码urf8字符串', '编码，输入字符串；解码，输入字节数组；', 'true：编码，false：解码', 'base64模式');

export const utf8WithBase32 = function (value, encode) {
    if (encode) {
        return base32(utf8(value, true));
    } else {
        return utf8(base32(value), false);
    }
}
desc(utf8WithBase32, '用base32编解码urf8字符串', '编码，输入字符串；解码，输入字节数组；', 'true：编码，false：解码');

const HEXES = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15];

export const hex = function (value, encode) {
    const g = (value, encode) => {
        if (encode) {
            if (typeof value === 'number' && value >= 0 && value < 255) {
                return HEXES[((value & 0xF0) >>> 4) + 23] + HEXES[(value & 0x0F) + 23];
            }
        } else {
            if (typeof value === 'string') {
                let h = 0, l;
                if (value.length === 2) {
                    h = value.charCodeAt(0) - 48;
                    l = value.charCodeAt(1) - 48;
                } else {
                    l = value.charCodeAt(0) - 48;
                }
                if (h < HEXES.length && l < HEXES.length
                    && typeof HEXES[h] === 'number' && HEXES[h] !== -1
                    && typeof HEXES[l] === 'number' && HEXES[l] !== -1) {
                    return (HEXES[h] << 4) | HEXES[l];
                }
            }
        }
        throw Error(`Error to ${encode ? 'encode' : 'decode'} byte hex value:` + value);
    }
    if (encode) {
        let result = '';
        for (let i = 0; i < value.length; i++) {
            result += g(value[i], true);
        }
        return result;
    } else {
        let result = [];
        for (let i = 0; i < value.length; i += 2) {
            result.push(g(value.slice(i, i + 2), false))
        }
        return result;
    }
};
desc(hex, ' hex与byte互相转换', '编码，输入字符串；解码，输入字节数组；', 'true：编码，false：解码');

const INDEX_BASE32 = Object.freeze(['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7']);
const BASE32_INDEX = Object.freeze({'A': 0, 'B': 1, 'C': 2, 'D': 3, 'E': 4, 'F': 5, 'G': 6, 'H': 7, 'I': 8, 'J': 9, 'K': 10, 'L': 11, 'M': 12, 'N': 13, 'O': 14, 'P': 15, 'Q': 16, 'R': 17, 'S': 18, 'T': 19, 'U': 20, 'V': 21, 'W': 22, 'X': 23, 'Y': 24, 'Z': 25, '2': 26, '3': 27, '4': 28, '5': 29, '6': 30, '7': 31});

export const base32 = function (value) {
    if ('length' in value && typeof value !== 'string') {
        return base(value, true, 0, INDEX_BASE32);
    } else {
        return base(value, false, 0, BASE32_INDEX);
    }
};
desc(base32, ' 用base32编解码', '编码，输入字节数组；解码，输入字符串；');

const INDEX_BASE64 = Object.freeze(['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ['+', '-'], ['/', '_']]);
const BASE64_INDEX = Object.freeze({'A': 0, 'B': 1, 'C': 2, 'D': 3, 'E': 4, 'F': 5, 'G': 6, 'H': 7, 'I': 8, 'J': 9, 'K': 10, 'L': 11, 'M': 12, 'N': 13, 'O': 14, 'P': 15, 'Q': 16, 'R': 17, 'S': 18, 'T': 19, 'U': 20, 'V': 21, 'W': 22, 'X': 23, 'Y': 24, 'Z': 25, 'a': 26, 'b': 27, 'c': 28, 'd': 29, 'e': 30, 'f': 31, 'g': 32, 'h': 33, 'i': 34, 'j': 35, 'k': 36, 'l': 37, 'm': 38, 'n': 39, 'o': 40, 'p': 41, 'q': 42, 'r': 43, 's': 44, 't': 45, 'u': 46, 'v': 47, 'w': 48, 'x': 49, 'y': 50, 'z': 51, '0': 52, '1': 53, '2': 54, '3': 55, '4': 56, '5': 57, '6': 58, '7': 59, '8': 60, '9': 61, '+': 62, '-': 62, '/': 63, '_': 63});

export const base64 = function (value, select = 0) {
    if ('length' in value && typeof value !== 'string') {
        return base(value, true, select, INDEX_BASE64);
    } else {
        return base(value, false, select, BASE64_INDEX);
    }
};
desc(base64, ' 用base64编解码', '编码，输入字节数组；解码，输入字符串；');

const base = function (value, encode, select, mapping) {
    const type = typeof value;
    if (encode) {
        if ('length' in value && typeof value.length !== 'number') {
            throw Error(`Error value type "${type}"`);
        }
        select = typeof select !== "number" || select < 0 ? 0 : select;
    } else {
        if (type !== 'string') {
            throw Error(`Error value type "${type}"`);
        }
    }
    const bitsInGroup = mumOfPower2((() => {
            if (encode) {
                return mapping.length;
            } else {
                let max = 0;
                for (let name of Object.keys(mapping)) {
                    if (mapping[name] > max) max = mapping[name];
                }
                return max + 1;
            }
        })()),
        bits = lcm(8, bitsInGroup),
        tolGroup = bits / bitsInGroup,
        tolBytes = bits / 8;
    if (encode) {

    } else {
        if (value.length % tolGroup !== 0) {
            throw Error(`Error value is not a multiple of ${tolGroup}`);
        }
        let c = 0;
        for (let i = value.length - 1; i >= 0; i--) {
            if (value[i] === '=') c++;
            else break;
        }
        if (c !== 0) {
            value = value.slice(0, -c);
        }
    }
    const mod = value.length % (encode ? tolBytes : tolGroup),
        len = value.length - mod,
        pgc = encode ? Math.ceil(mod * 8 / bitsInGroup) : Math.floor(mod * bitsInGroup / 8),
        sgc = (encode ? tolGroup : 8) - pgc,
        binaryPrefix = '0'.repeat(encode ? 7 : bitsInGroup - 1);
    if (encode) {
        const c = (i) => {
            if (typeof i === "number" && i >= 0 && i < 255 && i in mapping) {
                return select < mapping[i].length ? mapping[i][select] : mapping[i][0];
            }
            throw Error('Out of range');
        };
        let result = '', temp;
        for (let i = 0; i < len; i += tolBytes) {
            temp = '';
            for (let j = 0; j < tolBytes; j++) {
                temp += (binaryPrefix + value[i + j].toString(2)).slice(-8);
            }
            for (let j = 0; j < bits; j += bitsInGroup) {
                result += c(parseInt(temp.slice(j, j + bitsInGroup), 2));
            }
        }
        if (mod !== 0) {
            const v = value.slice(len, value.length);
            for (let i = tolBytes - mod; i > 0; i--) v.push(0);
            temp = '';
            for (let i = 0; i < pgc; i++) {
                temp += (binaryPrefix + v[i].toString(2)).slice(-8);
            }
            for (let i = 0; i < pgc; i++) {
                result += mapping[parseInt(temp.slice(i * bitsInGroup, (i + 1) * bitsInGroup), 2)];
            }
            for (let i = 0; i < sgc; i++) result += '=';
        }
        return result;
    } else {
        const c = (i) => {
            if (i in mapping) {
                return mapping[i];
            }
            throw Error('Out of range');
        };
        let result = [], temp;
        for (let i = 0; i < len; i += tolGroup) {
            temp = '';
            for (let j = 0; j < tolGroup; j++) {
                temp += (binaryPrefix + c(value[i + j]).toString(2)).slice(-bitsInGroup);
            }
            for (let j = 0; j < bits; j += 8) {
                result.push(parseInt(temp.slice(j, j + 8), 2));
            }
        }
        if (mod !== 0) {
            const v = value.slice(len, value.length);
            temp = '';
            for (let i = 0; i < v.length; i++) {
                temp += (binaryPrefix + c(v[i]).toString(2)).slice(-bitsInGroup);
            }
            temp = temp.slice(0, -(temp.length % 8));
            for (let i = 0; i < pgc; i++) {
                result.push(parseInt(temp.slice(i * 8, (i + 1) * 8), 2));
            }
        }
        return result;
    }

};

/**
 var nzh = new NZH({
 ch: "〇壹贰叁肆伍陆柒捌玖",      // 数字字符
 ch_u: "个十百千万亿兆京",       // 数位单位字符，万以下十进制，万以上万进制，个位不能省略
 ch_f: "负",                   // 负字符
 ch_d: "点",                   // 小数点字符
 m_u: "元角分厘",              // 金额单位
 m_t: "人民币",                // 金额前缀
 m_z: "正"                    // 金额无小数后缀
 });
 nzh.encode("10001000000000000"); // 壹京〇壹兆
 nzh.decode("壹兆");              // 1000000000000
 nzh.toMoney("1.234");           // 人民币壹元贰角叁分肆厘
 */
export class NZH {
    static cn_b = new NZH({
        ch: '零壹贰叁肆伍陆柒捌玖',
        ch_u: '个拾佰仟万亿',
        ch_f: '负',
        ch_d: '点',
        m_t: '人民币',
        m_z: '整',
        m_u: '元角分'
    });
    static cn_s = new NZH({
        ch: '零一二三四五六七八九',
        ch_u: '个十百千万亿',
        ch_f: '负',
        ch_d: '点',
        m_t: '人民币',
        m_z: '整',
        m_u: '元角分'
    });
    static hk_b = new NZH({
        ch: '零壹貳參肆伍陸柒捌玖',
        ch_u: '個拾佰仟萬億',
        ch_f: '負',
        ch_d: '點',
        m_t: '$',
        m_z: '整',
        m_u: '圓角分'
    });
    static hk_s = new NZH({
        ch: '零一二三四五六七八九',
        ch_u: '個十百千萬億',
        ch_f: '負',
        ch_d: '點',
        m_t: '$',
        m_z: '整',
        m_u: '圓角分'
    });

    #ch;
    #ch_u;
    #ch_f;
    #ch_d;
    #m_t;
    #m_z;
    #m_u;

    constructor(mode) {
        this.#ch = mode.ch;
        this.#ch_u = mode.ch_u;
        this.#ch_f = mode.ch_f;
        this.#ch_d = mode.ch_d;
        this.#m_t = mode.m_t;
        this.#m_z = mode.m_z;
        this.#m_u = mode.m_u;
    }

    /**
     * 阿拉伯数字转中文数字
     *
     * @param {String} input 阿拉伯数字/字符串 , 科学记数法字符串
     * @param options
     */
    encode(input, options) {
        let result = getNumbResult(input)
        if (!result) {
            return input;
        }
        options = assign({ww: false, tenMin: false}, typeof options === 'object' ? options : {});
        let ch = this.#ch             //数字
            , ch_u = this.#ch_u       //单位
            , ch_f = this.#ch_f || '' //负
            , ch_d = this.#ch_d || '.' //点
            , n0 = ch.charAt(0); //零
        let _int = result.int             //整数部分
            , _decimal = result.decimal   //小数部分
            , _minus = result.minus;      //负数标识
        let int
            , dicimal = ''
            , minus = _minus ? ch_f : ''; //符号位
        let encodeInt = function (_int, _m, _dg) {
            _int = getNumbResult(_int).int;
            let int = ''
                , tenm = arguments.length > 1 ? arguments[1] : options.tenMin
                , _length = _int.length;
            //一位整数
            if (_length === 1) return ch.charAt(+_int);
            if (_length <= 4) { //四位及以下
                for (let i = 0, n = _length; n--;) {
                    let _num = +_int.charAt(i);
                    int += (tenm && _length === 2 && i === 0 && _num === 1) ? '' : ch.charAt(_num);
                    int += (_num && n ? ch_u.charAt(n) : '')
                    i++;
                }
            } else {  //大数递归
                let d = _int.length / 4 >> 0
                    , y = _int.length % 4;
                //"兆","京"等单位处理
                while (y === 0 || !ch_u.charAt(3 + d)) {
                    y += 4;
                    d--;
                }
                let _maxLeft = _int.substr(0, y), //最大单位前的数字
                    _other = _int.substr(y); //剩余数字

                int = encodeInt(_maxLeft, tenm) + ch_u.charAt(3 + d)
                    + (_other.charAt(0) === '0' ? n0 : '') //单位后有0则加零
                    + encodeInt(_other, _other.length > 4 ? tenm : false)
            }
            int = trim(int, n0); //修整零
            return int;
        }

        //转换小数部分
        if (_decimal) {
            _decimal = trim(_decimal, '0', '$'); //去除尾部0
            for (let x = 0; x < _decimal.length; x++) {
                dicimal += ch.charAt(+_decimal.charAt(x));
            }
            dicimal = dicimal ? ch_d + dicimal : "";
        }

        //转换整数部分
        int = encodeInt(_int);  //转换整数

        //超级大数的万万化
        if (options.ww && ch_u.length > 5) {
            let dw_w = ch_u.charAt(4)
                , dw_y = ch_u.charAt(5);
            let lasty = int.lastIndexOf(dw_y);
            if (~lasty) {
                int = int.substring(0, lasty).replace(new RegExp(dw_y, 'g'), dw_w + dw_w) + int.substring(lasty);
            }
        }
        return minus + int + dicimal;
    }

    /**
     * 中文数字转阿拉伯数字
     *
     * @param {string} input 中文数字字符串
     * @returns Number
     */
    decode(input) {
        input = input.toString();
        let result = input.split(this.#ch_d);
        let _int = result[0].replace(this.#ch_f, "")
            , _decimal = result[1]
            , _minus = !!~result[0].indexOf(this.#ch_f);

        let dw_s = this.#ch_u.charAt(1)
            , dw_w = this.#ch_u.charAt(4)
            , dw_y = this.#ch_u.charAt(5);

        _int = _int.replace(new RegExp(dw_w + "{2}", "g"), dw_y);

        let cnarr = _int.split('');
        let dw = 0, maxdw = 0;
        let rnum_a = [], num_a = [], _num_a = [];
        for (let i = 0; i < cnarr.length; i++) {
            let chr = cnarr[i];
            let n = 0, u = 0;
            if (~(n = this.#ch.indexOf(chr))) {
                //_num = _num*10 + n;
                if (n > 0) _num_a.unshift(n);
                //_num_a.unshift(n);
            } else if (~(u = this.#ch_u.indexOf(chr))) {
                let digit = getDigit(u);
                if (dw > u) {//正常情况
                    unshiftZero(_num_a, digit);
                    mergeArray(num_a, _num_a);
                } else if (u >= maxdw) {//后跟大单位
                    if (i === 0) _num_a = [1];
                    mergeArray(rnum_a, num_a, _num_a);
                    if (rnum_a.length > 0) unshiftZero(rnum_a, digit);
                    maxdw = u;
                } else {
                    if (_num_a.length === 0 && dw_s === chr) _num_a = [1];
                    mergeArray(num_a, _num_a);
                    unshiftZero(num_a, getDigit(u));
                    dw = u;
                }
            }
        }
        mergeArray(rnum_a, num_a, _num_a).reverse();
        if (rnum_a.length === 0) rnum_a.push(0);
        let decimal = 0;
        if (_decimal) {
            rnum_a.push('.')
            decimal = '0.'
            for (let i = 0; i < _decimal.length; i++) {
                decimal += this.#ch.indexOf(_decimal.charAt(i));
                rnum_a.push(this.#ch.indexOf(_decimal.charAt(i)));
            }
            decimal = +decimal;

        }
        if (_minus) rnum_a.unshift('-');
        return parseFloat(rnum_a.join(''));
    }

    /**
     * 阿拉伯数字转金额
     *
     * @param {String} num 阿拉伯数字/字符串 , 科学记数法字符串
     * @param {Object} options 转换配置
     *                         {
     *                             ww:{万万化开关 | true},
     *                             unOmitYuan: {整数为0时不省略元| false},
     *                             complete:{完整金额格式 | false},
     *                             outSymbol:{是否输出金额符号 | true}
     *                             forceZheng:{以转换结果加“整” | false}
     *                         }
     * @returns String
     */
    toMoney(num, options) {
        options = assign({ww: true, complete: false, outSymbol: true, unOmitYuan: false, forceZheng: false}, typeof options === "object" ? options : {});
        let result = getNumbResult(num);
        let ch_0 = this.#ch.charAt(0);
        if (!result) {
            return num;
        }

        let _int = result.int
            , _decimal = result.decimal || "";
        let t_str = options.outSymbol ? this.#m_t : ""
            , zs_str = result.minus ? this.#ch_f : ""
            , xs_str = "";
        if (options.complete) {
            for (let i = 1; i < this.#m_u.length; i++) {
                xs_str += this.encode(_decimal.charAt(i - 1) || "0") + this.#m_u.charAt(i);
            }
            zs_str += this.encode(_int, options) + this.#m_u.charAt(0);
        } else {
            let hasYuan = options.unOmitYuan || _int !== '0';
            _decimal = _decimal.slice(0, this.#m_u.length - 1);
            _decimal = trim(_decimal, "0", "$"); //去除尾部的0
            if (_decimal) {
                let mark_0;
                for (let i = 0; i < this.#m_u.length - 1; i++) {
                    if (_decimal.charAt(i) && _decimal.charAt(i) !== "0") {
                        xs_str += this.encode(_decimal.charAt(i)) + this.#m_u.charAt(i + 1);
                        mark_0 = false;
                    }
                    if (_decimal.charAt(i) === "0" && !mark_0) {
                        if (i !== 0 || _int !== "0") xs_str += ch_0; //当没有输出元时,小数前无需加零
                        mark_0 = true;
                    }
                }
                //if(_num === "0"){xs_str = util.trim(xs_str,ch_0,"^")}
            }
            if (hasYuan || !xs_str) {
                zs_str += this.encode(_int, options) + this.#m_u.charAt(0);
            }
            if (!options.forceZheng) {
                zs_str += result.decimal ? "" : this.#m_z
            } else if (xs_str === '' || xs_str.charAt(xs_str.length - 1) !== this.#m_u[2]) {
                xs_str += this.#m_z
            }
            // if(result.minus) t_str += this.ch_f;
            if (options.forceZheng) {
            }
        }
        return t_str + zs_str + xs_str;
    }
}
