export const desc = function (any, description) {
    let desc = '';
    if (typeof description === 'string') {
        if (description.length === 2) {
            desc += description;
        } else if (arguments.length > 2) {
            desc += `${arguments[1]}`;
            for (let i = 2; i < arguments.length; i++) {
                desc += '\n@param' + (i - 1) + ' ' + arguments[i];
            }
        } else {
            desc = 'Empty';
        }
    } else {
        desc = 'Empty';
    }
    desc = `
****************************************
*               comments               *
****************************************
${typeof any} ${any.name}
${desc}
----------------------------------------
`;
    Object.defineProperty(any, '__description__', {
        value: desc,
        configurable: false,
        enumerable: false,
        writable: false,
    });
    return any;
};
desc(desc, '在任何对象中条件描述', '任何对象', '描述', '参数值i...');

export const constant = function (any, name, value) {
    if (any) {
        Object.defineProperty(any, name, {
            value: value,
            configurable: false,
            enumerable: true,
            writable: false,
        });
    }
};
desc(constant, '在object中声明name的常量value', '对象', '名称', '值');

export const tag = function (any) {
    return toString.call(any);
};
desc(tag, '获取值的字符串的值', '任何对象');

export const assign = function (target) {
    for (let i = 1; i < arguments.length; i++) {
        for (let key in arguments[i]) {
            target[key] = arguments[i][key];
        }
    }
    return target
};
desc(assign, '将arguments[1]后的对象属性分配给target', '目标对象');

export const test = function (any) {
    if (any === null || any === undefined) {
        return false;
    } else if (typeof any === 'boolean') {
        return any;
    } else if (typeof any === 'number') {
        if (isNaN(any) || isFinite(any)) return false;
        return any > 0;
    } else if (typeof any === 'string') {
        if (any === '' || (any = any.trim()) === '') {
            return false;
        } else {
            any = any.toLowerCase();
            if (any.length === 1) {
                if (any === 'f') return false;
                if (any === 't') return true;
            } else if (any.length >= 4 && any.length <= 5) {
                if (any === 'false') return false;
                if (any === 'true') return true;
            } else if (any === '[object Null]' || any === '[object Undefined]') {
                return false;
            }
            return true;
        }
    } else if (typeof any === 'function') {
        if (any.length === 0) return test(any());
        return true;
    } else if (Array.isArray(any)) {
        return any.length !== 0;
    } else {
        return true;
    }
};
desc(test, '将arguments[1]测试任何对象返回boolean类型', '任何值');

export const isNoNull = function (any) {
    return any !== null && any !== undefined;
}

export const isBoolean = function (any) {
    return isNoNull(any) && (typeof any === 'boolean' || tag(any) === '[object Boolean]');
}

export const isSymbol = function (any) {
    return isNoNull(any) && (typeof any === 'symbol' || tag(any) === '[object Symbol]');
}

export const isInt = function (any) {
    return typeof any === 'number' && any % 1 === 0;
}

export const isNumber = function (any) {
    return isNoNull(any) && (typeof any === 'number' || tag(any) === '[object Number]');
}

export const isPlainObject = function (any) {
    if (!isObject(any) || tag(any) !== '[object Object]') {
        return false;
    }
    if (Object.getPrototypeOf(any) === null) {
        return true;
    }
    let proto = any;
    while (Object.getPrototypeOf(proto) !== null) {
        proto = Object.getPrototypeOf(proto);
    }
    return Object.getPrototypeOf(any) === proto;
}

export const isObject = function (any) {
    return isNoNull(any) && !Array.isArray(any);
}

export const isArguments = function (any) {
    return isNoNull(any) && tag(any) === '[object Arguments]';
}

export const isArray = function (any) {
    return isNoNull(any) && Array.isArray(any);
}

export const isIterable = function (any) {
    return isNoNull(any) && (isArray(any) || isArguments(any));
}

export const isNotEmpty = function (any) {
    return isNoNull(any) && length in any && typeof any.length === 'number' && any.length > 0;
};

export const isIndex = function (value, limit) {
    limit = test(limit) && typeof limit === 'number' && limit >= 0 ? limit : 9007199254740991;
    if (typeof value === 'string') {
        return /^(0|[1-9]\d*)$/.test(value) && value > -1 && value % 1 === 0 && value < limit;
    }
    return typeof value === 'number' && value > -1 && value % 1 === 0 && value <= limit;
};

/** Used to detect if a method is native. */
const reIsNative = RegExp(
    `^${Function.prototype.toString
        .call(Object.prototype.hasOwnProperty)
        .replace(/[\\^$.*+?()[\]{}|]/g, '\\$&')
        .replace(/hasOwnProperty|(function).*?(?=\\\()| for .+?(?=\\])/g, '$1.*?')}$`,
);

const isNative = function (value) {
    return isObject(value) && reIsNative.test(value);
}


