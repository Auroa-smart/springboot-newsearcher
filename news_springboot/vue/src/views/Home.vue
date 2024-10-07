<template>
<div>
  <div style="padding: 10px 0;">
    <el-input style="width: 200px;margin-right: 10px" placeholder="请输入新闻标题" suffix-icon="el-icon-search" v-model="title"></el-input>
    <el-input style="width: 200px;margin-right: 10px" placeholder="请输入新闻来源" suffix-icon="el-icon-s-operation" v-model="source"></el-input>
    <el-input style="width: 200px;margin-right: 10px" placeholder="请输入发布日期" suffix-icon="el-icon-date" v-model="publishDate"></el-input>
    <el-button type="primary" @click="load" >搜索</el-button>
    <el-button type="warning" @click="reset" >重置</el-button>
  </div>
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
              <span style="display: block;font-weight: bold;margin-top: 20px">摘要：waiting...{{scope.row.newsAbstarct}}</span>
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

  <el-backtop target=".page-component__scroll .el-scrollbar__wrap"></el-backtop>
</div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Home",
    data() {
    return {
      tableData: [],
      total:0,
      pageNum:1,
      pageSize:10,
      title:'',
      source:'',
      publishDate:'',
      dialogVisible:false,
    }
  },
  created(){
    this.load()
  },
  methods: {
    load() {
      request.get("/news/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          title: this.title,
          source: this.source,
          publishDate:this.publishDate
        }
      }).then(res => {
        // console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    },
    reset(){
      // request.get("/news/spider")
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