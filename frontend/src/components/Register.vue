<template>
  <div>
    <div class="top_div"></div>
    <div
      style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 420px; text-align: center;">
      <div style="width: 165px; height: 250px; position: absolute;">
        <div class="tou"></div>
        <div class="initial_left_hand" id="left_hand"></div>
        <div class="initial_right_hand" id="right_hand"></div>
      </div>

      <p style="padding: 30px 0px 10px; position: relative;"> <input id="email" class="ipt" type="email" v-model="email" placeholder="请输入邮箱"
                                      value="">
      </p>
      <button @click="sendCode" style="margin-top: -10px;margin-bottom: 10px;">发送验证码</button>
      <p style="position: relative;"><span
        ></span> <input id="code" class="ipt" type="text" v-model="code" placeholder="验证码"
                                      value="">
      </p>
      <p style=" position: relative;"> <input id="username" class="ipt" type="text" v-model="username" placeholder="请输入用户名"
                                      value="">
      </p>
      <p style="position: relative;">
        <input class="ipt" id="password" type="password" v-model="password" placeholder="请输入密码" value="">
      </p>
      <p style="position: relative;margin-top: 10px;">
        <input class="ipt" id="password2" type="password" v-model="password2" placeholder="请再次输入密码" value="">
      </p>


      <div style="height: 20px; margin-top: 10px; color: red;">
        <p>{{ errorMessage }}</p>
      </div>
      <div
        style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
        <!--        <p style="margin: 0px 35px 20px 45px;"><span style="float: left;"><a style="color: rgb(204, 204, 204);"-->
        <!--                                                                             href="#">忘记密码?</a></span>-->
        <router-link to='/'>
          <span style="float: left;margin-left: 10px;font-size: 14px;">已有账号，现在登录</span>
        </router-link>

        <span style="float: right;">
              <a id="loginBtn" @click="register()">注册</a>
           </span></div>
    </div>
  </div>
</template>

<script>

  export default {
    name: 'Register',
    data() {
      return {
        email: "",
        username: "",
        password: "",
        password2: "",
        code: "",
        errorMessage: ""
      }
    },
    methods: {
      sendCode() {
      this.$axios.post("/user/register/send-code", { email: this.email }),{
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
           }
      }
        .then(response => {
          this.errorMessage = "验证码已发送，请查收";
        })
        .catch(error => {
          this.errorMessage = error.response.data || "发送验证码失败";
        });
    },
    register() {
      if (this.password !== this.password2) {
        this.errorMessage = "两次输入的密码不一致";
        return;
      }

      this.$axios.post("/user/register", {
        email: this.email,
        username: this.username,
        password: this.password,
        code: this.code
      }).then(response => {
        alert("注册成功");
        this.$router.push('/');  // 可以根据实际情况跳转到登录页面或其他页面
      }).catch(error => {
        this.errorMessage = error.response.data || "注册失败";
      });
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  body {
    background: #ebebeb;
    font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei", "\9ED1\4F53", Arial, sans-serif;
    color: #222;
    font-size: 12px;
  }

  * {
    padding: 0px;
    margin: 0px;
  }

  .top_div {
    background: #008ead;
    width: 100%;
    height: 400px;
  }

  .ipt {
    border: 1px solid #d3d3d3;
    padding: 10px 10px;
    width: 290px;
    border-radius: 4px;
    padding-left: 35px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
    -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
  }

  .ipt:focus {
    border-color: #66afe9;
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
  }

  .u_logo {
    background: url("../assets/images/username.png") no-repeat;
    padding: 10px 10px;
    position: absolute;
    top: 43px;
    left: 40px;

  }

  .p_logo {
    background: url("../assets/images/password.png") no-repeat;
    padding: 10px 10px;
    position: absolute;
    top: 12px;
    left: 40px;
  }

  a {
    text-decoration: none;
  }

  .tou {
    background: url("../assets/images/tou.png") no-repeat;
    width: 97px;
    height: 92px;
    position: absolute;
    top: -87px;
    left: 140px;
  }

  .left_hand {
    background: url("../assets/images/left_hand.png") no-repeat;
    width: 32px;
    height: 37px;
    position: absolute;
    top: -38px;
    left: 150px;
  }

  .right_hand {
    background: url("../assets/images/right_hand.png") no-repeat;
    width: 32px;
    height: 37px;
    position: absolute;
    top: -38px;
    right: -64px;
  }

  .initial_left_hand {
    background: url("../assets/images/hand.png") no-repeat;
    width: 30px;
    height: 20px;
    position: absolute;
    top: -12px;
    left: 100px;
  }

  .initial_right_hand {
    background: url("../assets/images/hand.png") no-repeat;
    width: 30px;
    height: 20px;
    position: absolute;
    top: -12px;
    right: -112px;
  }

  .left_handing {
    background: url("../assets/images/left-handing.png") no-repeat;
    width: 30px;
    height: 20px;
    position: absolute;
    top: -24px;
    left: 139px;
  }

  .right_handinging {
    background: url("../assets/images/right_handing.png") no-repeat;
    width: 30px;
    height: 20px;
    position: absolute;
    top: -21px;
    left: 210px;
  }

  #loginBtn {
    margin-right: 30px;
    background: rgb(0, 142, 173);
    padding: 7px 10px;
    border-radius: 4px;
    border: 1px solid rgb(26, 117, 152);
    border-image: none;
    color: rgb(255, 255, 255);
    font-weight: bold;
    cursor: pointer;
  }

</style>
