<template>
  <el-menu
      :default-openeds="['1', '3']"
      background-color="#eee"
      text-color="#000"
      :collapse-transition="false"
      :collapse="isCollapse"
      router
      @select="handleSelect"
  >
    <div style="height: 60px;line-height: 60px;text-align: center">
      <img src="../assets/news.png" alt="" style="width: 20px;position: relative;top:5px;margin-right: 5px">
      <b style="color:black" v-show="logoTextShow">新闻检索系统</b>
    </div>

    <el-submenu index="1">
      <template slot="title"><i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/"><i class="el-icon-s-management"></i>新闻页面</el-menu-item>
        <el-menu-item v-if="user.role === '管理员'"  index="/user"><i class="el-icon-user-solid"></i>用户管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>

    <el-submenu index="2">
      <template slot="title"><i class="el-icon-s-cooperation"></i>
        <span slot="title">检索工具</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/tool"><i class="el-icon-s-data"></i>分类搜索</el-menu-item>
<!--        <el-menu-item index="/keyword"><i class="el-icon-key"></i>关键词检索</el-menu-item>-->
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  data(){
    return{
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) :{},
    }
  },
  created() {
    console.log(this.user.userName)
    console.log(this.user.passWord)
  },
  props:{
    isCollapse:Boolean,
    logoTextShow:Boolean
  },
  methods:{
    handleSelect(index){
       console.log(index)
    }
  }
}
</script>

<style scoped>

</style>