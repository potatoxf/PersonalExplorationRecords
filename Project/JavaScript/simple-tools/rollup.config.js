// 输出格式 amd / es / cjs / iife / umd
import {defineConfig} from 'rollup';
import resolve from "@rollup/plugin-node-resolve";
import babel from "@rollup/plugin-babel";

export default defineConfig({
    input: 'index.js',
    plugins: [
        resolve(),
        babel({
            babelHelpers: 'bundled'
        }),
    ],
    output: [
        {
            format: 'es',
            name: 'SimpleTools',
            file: `./dist/simple-tools.es.js`,
        },
        {
            format: 'cjs',
            name: 'SimpleTools',
            file: `./dist/simple-tools.cjs.js`,

        },
        {
            format: 'iife',
            name: 'SimpleTools',
            file: `./dist/simple-tools.iife.js`,

        },
        {
            format: 'amd',
            name: 'SimpleTools',
            file: `./dist/simple-tools.amd.js`,
        },
        {
            format: 'umd',
            name: 'SimpleTools',
            file: `./dist/simple-tools.umd.js`,
        },
    ],
});
