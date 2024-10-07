<template>
<div class="detail" style="width: 90%;margin: auto " >
<ul>
  <li class="row">
    <div class="box">
      <strong>新闻编号：</strong>
      <span>{{rowData.newsId}}</span>
    </div>
  </li>
  <li class="row">
    <div class="box">
      <strong>原文链接：</strong>
      <span>{{rowData.url}}</span>
    </div>
  </li>
  <li class="row">
    <div class="box">
      <strong>新闻来源：</strong>
      <span>{{rowData.source}}</span>
    </div>
  </li>
  <li class="row">
    <div class="box">
      <strong>发布日期：</strong>
      <span>{{rowData.publishDate}}</span>
    </div>
  </li>
  <li class="row">
    <div class="box">
      <strong>新闻图片：</strong>
      <span>{{rowData.image}}</span>
    </div>
  </li>
</ul>
  <div class="title" style="text-align: center;font-weight: bold;font-size:20px ">
    <p>{{rowData.title}}</p>
  </div>
  <div class="content">
    <p align="left" style="font-size: 16px;line-height:1.5 ">{{rowData.content}}</p>
  </div>



</div>
</template>

<script>


export default {
  name: "NewsDetail",
  data(){
    return{
      rowData:[],
    }
  },
  mounted:function (){
     this.rowData = this.$route.params.myData
  },
  methods:{
  }
}
</script>

<style scoped>
ul{
  padding: 20px;
  margin-bottom: 50px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  /*background-color:rgb(238, 238, 238);*/
}
ul li{
  list-style: none;
  color: #999;
  letter-spacing: 0;
  font-size: 14px;
  margin: 10px;
  font-family:"Microsoft YaHei";
  /*overflow: auto;*/
}
.detail{
  overflow: hidden;
}
</style>