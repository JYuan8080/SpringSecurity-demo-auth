<!--
 * @name: index
 * @author:  JYuan
 * @date: 2021/8/20 19:58
 * @description：index
 * @update: 2021/8/20 19:58
-->
<template>
  <div class="login">
    <div class="login-form">
      <div class="login-form-right">
        <div class="login-title">SpringSecurity</div>
        <el-input
          prefix-icon="el-icon-user"
          v-model.trim="form.username"
          placeholder="请输入账号"
        />
        <el-input
          prefix-icon="el-icon-lock"
          v-model.trim="form.password"
          placeholder="请输入密码"
          show-password
        />
        <el-button type="primary" @click="onLogin">登录</el-button>
        <div class="login-forget">忘记密码可联系管理员找回</div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from '@vue/reactivity'
import { login } from '../../api/user'

export default {
  name: 'index',
  setup() {
    const form = reactive({
      username: null,
      password: null
    })

    return {
      form,
      async onLogin() {
        const res = await login(form.username, form.password)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.login {
  width: 100vw;
  height: 100vh;
  background-image: url('@/assets/images/login/BG.png');
  background-size: 100% 100%;
  .login-form {
    position: fixed;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    width: 1076px;
    height: 562px;
    opacity: 0.82;
    background-image: url('@/assets/images/login/formBG.png');
  }

  .login-form-right {
    position: absolute;
    bottom: 0;
    right: 45px;
    width: 448px;
    height: 461px;
    .el-input {
      font-size: 18px;
      &:nth-of-type(2) {
        margin-bottom: 40px;
      }
      &:nth-of-type(3) {
        margin-bottom: 52px;
      }
      :deep(.el-input__clear) {
        line-height: 56px;
        font-size: 18px;
      }
    }
    :deep(.el-input__icon) {
      line-height: 56px;
      font-size: 18px;
    }
    :deep(.el-input__inner) {
      height: 56px;
      border-radius: 10px;
    }

    .el-button {
      width: 448px;
      height: 56px;
      border-radius: 10px;
      font-size: 24px;
      margin-bottom: 26px;
    }
  }

  .login-title {
    font-family: 'Consolas';
    font-size: 42px;
    text-align: center;
    font-weight: 900;
    margin-bottom: 40px;
  }

  .login-forget {
    font-size: 16px;
    color: #999;
    font-weight: 400;
  }
}
</style>
