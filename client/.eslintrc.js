module.exports = {
  env: {
    browser: true,
    es2021: true
  },
  extends: [
    'plugin:vue/essential',
    'airbnb-base',
    'plugin:prettier/recommended' // 添加 prettier 插件
  ],
  parserOptions: {
    ecmaVersion: 12,
    sourceType: 'module'
  },
  plugins: ['vue'],
  rules: {
    'import/no-extraneous-dependencies': 0,
    'vue/no-multiple-template-root': 0, // 忽略vue模板标签下有多个根标签
    'vue/html-self-closing': 2 // html标签自闭合
  }
}
