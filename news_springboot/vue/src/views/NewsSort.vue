<template>
<div style="width: 95%;margin: 0 auto;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);padding: 10px">
  <div >
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
    <el-menu-item index="1" @click="load(1)">体育</el-menu-item>
    <el-menu-item index="2" @click="load(2)">娱乐</el-menu-item>
    <el-menu-item index="3" @click="load(3)">教育</el-menu-item>
    <el-menu-item index="4" @click="load(4)">时尚</el-menu-item>
    <el-menu-item index="5" @click="load(5)">旅游</el-menu-item>
    <el-menu-item index="6" @click="load(6)">科技</el-menu-item>
    <el-menu-item index="7" @click="load(7)">财经</el-menu-item>
    <el-menu-item index="8" @click="load(8)">军事</el-menu-item>
    <el-menu-item index="9" @click="load(9)">时政</el-menu-item>
    <el-menu-item index="10" @click="load(10)">文化</el-menu-item>

  </el-menu>
  </div>
  <div class="line"></div>
  <el-table
      :data="tableData"
      style="width: 100%"
  >
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item >
            <label >新闻ID</label>
            <span>&nbsp;{{ props.row.newsId }}</span>
          </el-form-item>
          <el-form-item >
            <label >新闻标题</label>
            <span>{{ props.row.title }}</span>
          </el-form-item>
          <el-form-item  >
            <label >摘要</label>
            <span>&nbsp;&nbsp;{{ props.row.newsAbstract }}</span>
          </el-form-item>
          <el-form-item>
            <label >来源</label>
            <span>&nbsp;&nbsp;{{ props.row.source }}</span>
          </el-form-item>
          <el-form-item >
            <label >原文链接</label>
            <span>{{ props.row.url }}</span>
          </el-form-item>
          <el-form-item >
            <label >发布时间</label>
            <span>{{ props.row.publishDate }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
        label="新闻ID"
        prop="newsId"
        width="150px"
    >
    </el-table-column>
    <el-table-column
        label="新闻标题"
        prop="title">
    </el-table-column>
    <el-table-column
        label="来源"
        prop="source">
    </el-table-column>

    <el-table-column prop="operate"  label="操作" width="400">
      <template slot-scope="scope">
        <el-button @click="dialogVisible = true" type="success" icon="el-icon-document" >摘要</el-button>
        <el-dialog
            title="摘要"
            :visible.sync="dialogVisible"
            width="30%"
            :before-close="handleClose">
          <!-- 通过作用域插槽将当前行的数据传递给dialog组件 -->
          <span style="display: inline-block">
                <span style="font-weight: bold" >标题：</span>{{scope.row.title}}
              </span>
          <span>摘要：{{scope.row.newsAbstarct}}</span>
          <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="dialogVisible = false">关闭</el-button>
              </span>
        </el-dialog>
        <el-button type="primary" icon="el-icon-s-order" @click="handleDetail(scope.row)"  style="margin: 5px" >详情</el-button>
      </template>
    </el-table-column>
  </el-table>

  <!--         分页      -->
  <div style="padding: 10px 0">
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
</div>
</div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "NewsSort",
  data() {
    return {
      activeIndex: '1',
      tableData: [],
      total:0,
      pageNum:1,
      pageSize:10,
      title:'',
      source:'',
      publishDate:'',
      dialogVisible:false,
      categoryId:10
    };
  },
  created(){
    this.load(10)
  },
  methods: {
    load(categoryId) {
      request.get("/news/sort",{
        params:{
               categoryId:categoryId,
               pageSize:this.pageSize,
               pageNum:this.pageNum
          }
        }).then(res => {
        console.log('分类搜索请求')
        console.log(res)
        // this.tableData = res
        this.total = res.categoryCount;
        this.tableData = res.newsList;
        this.categoryId = categoryId
      })
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load(this.categoryId)
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load(this.categoryId)
    },
    reset(){
      this.title=''
      this.source=''
      this.publishDate=''
    },
    handleDetail(row){
      // this.$router.push("/detail")
      // console.log(param)
      this.$router.push({name:"详情",params:{myData:row}})
    },
    handleClose(){
      this.dialogVisible = false
    },

  }
}
</script>

<style scoped>
el-menu-item{
  margin: 50px;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.el-form-item{
  margin-left: 50px;
}
label{
  font-size: 14px;
  font-weight: 500;
  color: #909399;
  margin-right: 30px;
}
</style>