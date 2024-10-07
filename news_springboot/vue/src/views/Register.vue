<template>
  <div class="wrapper">
    <div class="container"  >
    <div  class="box" >
        <div style="margin:20px 0; text-align: center; font-size: 24px;width: 250px"><b>注册</b></div>
      <el-form :rules="rules" :model="user" ref="userForm">
        <el-form-item  prop="userName">
          <el-input placeholder="请输入用户名" size="medium" style="width: 250px" prefix-icon="el-icon-user" v-model="user.userName" ></el-input>
        </el-form-item>
        <el-form-item prop="passWord" >
          <el-input placeholder="请输入密码" size="medium" style="width: 250px" prefix-icon="el-icon-lock" show-password v-model="user.passWord"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassWord" >
          <el-input placeholder="请确认密码" size="medium" style="width: 250px" prefix-icon="el-icon-lock" show-password v-model="user.confirmPassWord"></el-input>
        </el-form-item>
        <el-form-item style="margin: 20px 10px;text-align: center;width: 250px">
          <el-button type="primary" size="small" style="margin-right: 20px" @click="login" >注册</el-button>
          <el-button type="primary" size="small" @click="$router.push('/login')">返回登录</el-button>
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
        confirmPassWord: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ]
      }

    };
  },
  methods:{
    login(){
      this.$refs["userForm"].validate((valid) => {
        if (valid) {
          if(this.user.passWord !== this.user.confirmPassWord){
            this.$message.error("两次密码输入不一致！")
            return false
          }
          // alert('submit!');
          this.request.post("/user/register",this.user).then(res=>{
            if(res.code === "200") { // 返回结果不是空
              this.$message.success("注册成功！")
              this.$router.push("/login")//跳转到主页面
            }else{
              this.$message.error("注册失败！")
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
