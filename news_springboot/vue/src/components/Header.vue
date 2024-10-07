<template>
  <div style="font-size: 18px ;display: flex">
  <!--    收缩按钮-->
  <div style="flex: 1; font-size: 18px;">
    <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
    <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
      <el-breadcrumb-item to="/">首页</el-breadcrumb-item>
      <el-breadcrumb-item >{{ currentPathName }}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>

  <div style=" font-size: 12px ;display: flex; width: 900px;">
    <el-input
        placeholder="请输入内容"
        v-model="input"
        clearable
        style="float: left; width: 500px"
        suffix-icon="el-icon-search"
    >
    </el-input>
    &nbsp;
    <el-button type="primary" style="height: 40px;margin-top: 10px" >全局搜索</el-button>
  </div>

  <el-dropdown style="width: 100px;height: 50px; cursor: pointer"  >
    <div style="display: inline-block">
      <img src="" alt=""
       style="width: 30px;border-radius: 50%;position: relative;top:10px;right: 5px"
      >
      <span v-if="user.userName">{{user.userName}}</span>
      <a v-else @click="$router.push('/login')">登录</a>
      <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
    </div>
    <el-dropdown-menu slot="dropdown"  style="width: 100px;text-align: center">
<!--      <el-dropdown-item >个人信息</el-dropdown-item>-->
      <el-dropdown-item>
        <span @click="logout" style="text-decoration: none">退出</span>
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>

  </div>
</template>

<script>
export default {
  name: "Header",
  data(){
    return {
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) :{},  //判断是否为空
      input:'',
    }
  },
  props:{
    collapseBtnClass:String,
    collapse:String
  },
  computed:{
    currentPathName(){
      return this.$store.state.currentPathName;// 需要监听的数据
    }
  },
  watch:{
    currentPathName(newVal,oldVal){
      console.log(newVal)
    }
  },
  methods:{
    logout(){
      this.$message.success("退出成功！")
      this.$router.push("/login")
      localStorage.removeItem("user")
    },
    // collapse(){
    //   this.$emit("asideCollapse")
    // },

  }
}
</script>

<style scoped>

</style>