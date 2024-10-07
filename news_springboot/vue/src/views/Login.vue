<template>
  <div class="wrapper">
    <div class="container"  >
    <div  class="box" >
        <div style="margin:20px 0; text-align: center; font-size: 24px;width: 250px"><b>欢迎登录</b></div>
      <el-form :rules="rules" :model="user" ref="userForm">
        <el-form-item  prop="userName">
          <el-input size="medium" style="width: 250px" prefix-icon="el-icon-user" v-model="user.userName" ></el-input>
        </el-form-item>
        <el-form-item prop="passWord" >
          <el-input size="medium" style="width: 250px" prefix-icon="el-icon-lock" show-password v-model="user.passWord"></el-input>
        </el-form-item>
        <el-form-item style="margin: 20px 10px;text-align: center;width: 250px">
          <el-button type="primary" size="small" style="margin-right: 20px" @click="login">登录</el-button>
          <el-button type="primary" size="small" @click="$router.push('/register')">注册</el-button>
        </el-form-item>
      </el-form>

      </div>
    </div>
  </div>
</template>

<script>
import user from "@/views/User";

export default {
  name: "Login",
  data() {
    return {
      user:{},
      rules: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        passWord: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
      }
    };
  },
  methods:{
    login(){
      this.$refs["userForm"].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.request.post("/user/login",this.user).then(res=>{
            if(res.code === "200") { // 返回结果不是空
              localStorage.setItem("user",JSON.stringify(res.data))//存储用户信息到浏览器
              this.$message.success("登录成功！")
              this.$router.push("/")//跳转到主页面
            }else{
              this.$message.error("用户名或密码错误！")
            }
          });
        } else {
          return false;
        }
      })
    },

  }

}
</script>

<style scoped>
.container{
  display: flex;
  height: 600px;
  float: right;
  width: 310px;
  padding: 0 10px;
  background-color: #f5f7f6;
  margin:50px 100px;
}
.box{
  margin: 10px auto;
  background-color: rgba(255, 255, 255, 0.5);
  width: 270px;
  height:250px ;
  padding: 20px;
  border-radius: 10px;
  text-align: center;
  position: relative;
}
.wrapper{
  /*占满屏幕*/
  height: 100vh;
  background-image: url("../assets/loginBG.png");
  overflow: hidden;
}
</style>
