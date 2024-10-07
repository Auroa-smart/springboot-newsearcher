<template>
 <div >
   <el-container style="border: 1px solid #eee; height: 725px">
     <el-aside :width="sideWidth+'px'" style="background-color:#eee; box-shadow: 2px 0 6px #ddd" >
      <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
     </el-aside>

     <el-container>
       <el-header style="border-bottom: black">
          <Header :collapseBtnClass="collapseBtnClass"  :collapse="collapse" />
       </el-header>
       <el-main>
<!--         表示当前页面的子路由会在rout-view展示 children   -->
         <router-view/>
       </el-main>
     </el-container>
   </el-container>
 </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'
import request from "@/utils/request";
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
    data() {
        return {
          // tableData: [],
          // total:0,
          // pageNum:1,
          // pageSize:2,
          // userName:"",
          // passWord:"",
          // tel:"",
          // role:"",

          collapseBtnClass:'el-icon-s-fold',
          isCollapse:false,
          sideWidth:200,
          logoTextShow:true,
          // headerBg:'headerBg',
          // dialogFormVisible:false,
          // form:{
          //   userId:"",
          //   userName:"",
          //   passWord:"",
          //   tel:"",
          //   role:""
          // },
          // popoverVisible:false,
          // multipleSelection:[],
        }
    },
  created() {
  //    请求分页查询数据
  //    this.load()  User.vue里面写
  },
  components: {
    Aside,
    Header
  },
  methods: {
      formatter(row, column) {
        return row.image;
      },
      collapse() {
        this.isCollapse = !this.isCollapse
        if (this.isCollapse) {
          this.sideWidth = 64
          this.collapseBtnClass = 'el-icon-s-unfold'
          this.logoTextShow = false
        } else {//展开
          this.sideWidth = 200
          this.collapseBtnClass = 'el-icon-s-fold'
          this.logoTextShow = true
        }
      }

    }
  };
</script>

<style>

.el-header {
  /*background-color: #AAC0D1;*/
  color: #000;
  line-height: 60px;
  border-bottom: 1px solid #C0C4CC;
}

.el-aside {
  color: #eee;
}

.scrollable-content {
  max-height: 100%;
  overflow: hidden;
  transition: max-height 0.3s ease;
}
.headerBg{
  background-color: #eee!important;
}

.el-submenu__title{
  background-color: #eee;
}


</style>
