<template>
   <div>
     <div class="top_div"></div>
     <div style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 224px; text-align: center;">
       <div style="width: 165px; height: 96px; position: absolute;">
         <div class="tou"></div>
         <div class="initial_left_hand" id="left_hand"></div>
         <div class="initial_right_hand" id="right_hand"></div>
       </div>
       <p style="padding: 30px 0px 10px; position: relative;"> <input id="nameOrEmail" class="ipt" type="text" v-model="nameOrEmail" placeholder="请输入用户名或邮箱" value="">
       </p>
       <p style="position: relative;">
         <input class="ipt" id="password" type="password" v-model="password" placeholder="请输入密码" value="">
       </p>
       <div id="errorText" style="height: 20px;margin-top:10px">
        <p v-if="errorMessage" style="color: red;">{{ errorMessage }}</p>
      </div>
       <div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
                <!-- <p style="margin: 0px 35px 20px 45px;"><span style="float: left;"><a style="color: rgb(204, 204, 204);"
                                                                                     href="#">忘记密码?</a></span> -->
         <router-link to='/Register'>
           <span style="float: left;margin-left: 10px;font-size: 14px;">没有账号？现在注册</span>
         </router-link>
         <router-link to='/ResetPassword'>
           <span style="float: left;margin-left: 10px;font-size: 14px;">忘记密码？点击重置</span>
         </router-link>

         <span style="float: right;">
              <a id="loginBtn" @click="login()">登录</a>
           </span></div>

     </div>
   </div>
</template>

<script>

export default {
  name: 'Login',
  data () {
    return {
      nameOrEmail: "",
      password:"",
      errorMessage: ''
    }
  },
  // created(){

  // },
  methods:{
    async login() {
      try {
        const formData = new FormData();
        formData.append("nameOrEmail", this.nameOrEmail);
        formData.append("password", this.password);

        // const response = await this.$axios.post("/user/login", formData, {
        const response = await this.$axios.post("/user/login", {
          headers: { 'Content-Type': 'application/json'
           },
          nameOrEmail: this.nameOrEmail,
          password: this.password
        });

        // Handle successful login
        if (response.data) {
          localStorage.setItem('token', response.data); // Save the token locally
          this.$router.push({ path: '/tarot' });
        } else {
          this.errorMessage = "登录失败，请检查您的用户名或密码";
        }
      } catch (error) {
        this.errorMessage = error.response.data.message || 'Login failed';
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  body{
    background: #ebebeb;
    font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif;
    color: #222;
    font-size: 12px;
  }
  *{padding: 0px;margin: 0px;}
  .top_div{
    background: #008ead;
    width: 100%;
    height: 400px;
  }
  .ipt{
    border: 1px solid #d3d3d3;
    padding: 10px 10px;
    width: 290px;
    border-radius: 4px;
    padding-left: 35px;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s
  }
  .ipt:focus{
    border-color: #66afe9;
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
  }
  .u_logo{
    background: url("../assets/images/username.png") no-repeat;
    padding: 10px 10px;
    position: absolute;
    top: 43px;
    left: 40px;

  }
  .p_logo{
    background: url("../assets/images/password.png") no-repeat;
    padding: 10px 10px;
    position: absolute;
    top: 12px;
    left: 40px;
  }
  a{
    text-decoration: none;
  }
  .tou{
    background: url("../assets/images/tou.png") no-repeat;
    width: 97px;
    height: 92px;
    position: absolute;
    top: -87px;
    left: 140px;
  }
  .left_hand{
    background: url("../assets/images/left_hand.png") no-repeat;
    width: 32px;
    height: 37px;
    position: absolute;
    top: -38px;
    left: 150px;
  }
  .right_hand{
    background: url("../assets/images/right_hand.png") no-repeat;
    width: 32px;
    height: 37px;
    position: absolute;
    top: -38px;
    right: -64px;
  }
  .initial_left_hand{
    background: url("../assets/images/hand.png") no-repeat;
    width: 30px;
    height: 20px;
    position: absolute;
    top: -12px;
    left: 100px;
  }
  .initial_right_hand{
    background: url("../assets/images/hand.png") no-repeat;
    width: 30px;
    height: 20px;
    position: absolute;
    top: -12px;
    right: -112px;
  }
  .left_handing{
    background: url("../assets/images/left-handing.png") no-repeat;
    width: 30px;
    height: 20px;
    position: absolute;
    top: -24px;
    left: 139px;
  }
  .right_handinging{
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
