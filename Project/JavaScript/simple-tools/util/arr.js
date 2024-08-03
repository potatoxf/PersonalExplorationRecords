import {desc} from "./_.js";

export const flatArray = function (any, res) {
    res = Array.isArray(res) ? res : [];
    if (any !== null && any !== undefined) {
        const type = any.toString();
        if (Array.isArray(any)) {
            for (let i = 0; i < any.length; i++) flatArray(any[i], res);
        } else if (type === '[object Arguments]') {
            for (let i = 0; i < any.length; i++) flatArray(any[i], res);
        } else if (Symbol.iterator && any[Symbol.iterator]) {
            let it = any[Symbol.iterator](), data;
            while (!(data = it.next()).done) flatArray(data.value, res);
        } else if (type === '[object Set]') {
            any.forEach((value) => {
                flatArray(value, res);
            });
        } else if (type === '[object Map]') {
            any.forEach((value, key) => {
                flatArray({key, value}, res);
            });
        } else {
            res.push(any);
        }
    }
    return res;
};

desc(flatArray, '将任何参数平摊成一维数组');

export const mergeArray = function (baseArray) {
    for (let i = 1; i < arguments.length; i++) {
        baseArray.splice(0, arguments[i].length, ...arguments[i].splice(0, arguments[i].length));
    }
    return baseArray;
};
desc(mergeArray, '合并数组(按索引覆盖合并数组,并清空被合并的数组)');

export class IndexArray {
    #key;
    #array = [];
    #index = {};

    constructor(id) {
        if (id) {
            let type = typeof id;
            if (type === 'function') {
                this.#key = (row) => id(row);
            } else if (type === 'string' || type === 'number' || type === 'boolean' || type === 'symbol') {
                this.#key = (row) => row[id];
            } else {
                this.#key = (row) => row;
            }
        } else {
            this.#key = (row) => row;
        }
    }

    addElement(element) {
        if (element) {
            const key = this.#key(element);
            if (!this.containKey(key)) {
                const length = this.#array.length;
                this.#array.push(element);
                this.#index[key] = length;
            }
        }
        return this;
    }

    addElements() {
        const elements = flatArray(arguments, []);
        if (!('length' in elements)) throw Error();
        for (let i = 0; i < elements.length; i++) {
            this.addElement(elements[i]);
        }
        return this;
    }

    addLength(length) {
        if (typeof length !== 'number') throw Error();
        for (let i = 0; i < length; i++) {
            this.#array.push(null);
        }
        return this;
    }

    delElement(element) {
        if (element) {
            return this.delElementByKey(this.#key(element));
        }
        return null;
    }

    delElementByKey(key) {
        if (key) {
            if (this.containKey(key)) {
                const index = this.#index[key];
                const value = this.#array[index];
                this.#array[index] = null;
                delete this.#index[key];
                return value;
            }
        }
        return null;
    }

    delElements() {
        const elements = flatArray(arguments, []);
        if (!('length' in elements)) throw Error();
        let result = [];
        for (let i = 0; i < elements.length; i++) {
            result.push(this.delElement(elements[i]));
        }
        return result;
    }

    moveElement(element, other) {
        if (!(other instanceof IndexArray)) throw Error();
        if (element) {
            this.moveElementByKey(this.#key(element), other);
        }
    }

    moveElementByKey(key, other) {
        if (!(other instanceof IndexArray)) throw Error();
        if (key) {
            if (this.containKey(key)) {
                const index = this.#index[key];
                const value = this.#array[index];

                this.#array[index] = null;
                delete this.#index[key];

                other.#index[key] = index;
                other.#array[index] = value;
            }
        }
    }

    moveElementWithAll(other) {
        if (!(other instanceof IndexArray)) throw Error();
        const keys = Object.keys(this.#index);
        for (let i = 0; i < keys.length; i++) {
            this.moveElementByKey(keys[i], other);
        }
    }

    containKey(key) {
        return key && typeof this.#index[key] === 'number';
    }

    containElement(element) {
        return element && this.containKey(this.#key(element));
    }

    filter(predicate) {
        const result = [];
        for (let i = 0; i < this.#array.length; i++) {
            const e = this.#array[i];
            if (e && (!predicate || predicate(e))) {
                result.push(e);
            }
        }
        return result;
    }
}
desc(IndexArray, '索引数组');

export class IndexArrayMapping {
    #origin;
    #target;

    constructor(id, elements) {
        this.#origin = new IndexArray(id).addElements(elements);
        this.#target = new IndexArray(id).addLength(elements.length);
    }

    toOrigin(element) {
        this.#target.moveElement(element, this.#origin);
    }

    toOriginByKey(key) {
        this.#target.moveElementByKey(key, this.#origin);
    }

    toOrigins() {
        const elements = flatArray(arguments, []);
        for (let i = 0; i < elements.length; i++) {
            this.toOrigin(elements[i]);
        }
    }

    toOriginsByKeys(key) {
        const keys = flatArray(arguments, []);
        for (let i = 0; i < keys.length; i++) {
            this.toOriginByKey(keys[i]);
        }
    }

    toOriginsWithAll() {
        this.#target.moveElementWithAll(this.#origin);
    }

    toTarget(element) {
        this.#origin.moveElement(element, this.#target);
    }

    toTargetByKey(key) {
        this.#origin.moveElementByKey(key, this.#target);
    }

    toTargets() {
        const elements = flatArray(arguments, []);
        for (let i = 0; i < elements.length; i++) {
            this.toTarget(elements[i]);
        }
    }

    toTargetsByKeys() {
        const keys = flatArray(arguments, []);
        for (let i = 0; i < keys.length; i++) {
            this.toTargetByKey(keys[i]);
        }
    }

    toTargetsWithAll() {
        this.#origin.moveElementWithAll(this.#target);
    }

    filterOrigin(predicate) {
        return this.#origin.filter(predicate);
    }

    filterTarget(predicate) {
        return this.#target.filter(predicate);
    }
}
desc(IndexArrayMapping, '左右索引数组映射关系选用');
