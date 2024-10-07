<template>
  <div>
    <div style="padding: 10px 0;">
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入用户名" suffix-icon="el-icon-search" v-model="userName"></el-input>
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入联系电话" suffix-icon="el-icon-s-operation" v-model="tel"></el-input>
      <el-input style="width: 200px;margin-right: 10px" placeholder="请输入角色" suffix-icon="el-icon-date" v-model="role"></el-input>
      <el-button type="primary" @click="load" >搜索</el-button>
      <el-button type="warning" @click="reset" >重置</el-button>
    </div>
    <div style="padding: 10px 0">
      <el-button type="primary" @click="handleAdd" >新增<i class="el-icon-circle-plus-outline" style="margin-left: 5px"></i></el-button>
      <el-popconfirm
          confirm-button-text="确定"
          cancle-button-text="我再想想"
          icon="el-icon-info"
          title="确定删除吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference" style="margin: 5px">批量删除<i class="el-icon-remove-outline" style="margin-left: 5px"></i></el-button>
      </el-popconfirm>


    </div>
    <el-table
        :data="tableData"
        :default-sort = "{prop: 'date', order: 'descending'}"
        stripe
        border
        :header-cell-class-name="headerBg"
        @selection-change="handleSelectionChange"
    >
      <el-table-column
          type="selection"
          width="55"
      >
      </el-table-column>
      <el-table-column prop="userId" label="ID" sortable width="120" >
      </el-table-column>
      <el-table-column prop="userName" label="用户名" sortable width="200">
      </el-table-column>
      <!--           <el-table-column prop="passWord" label="密码" sortable width="150">-->
      <!--           </el-table-column>-->
      <el-table-column prop="tel" label="联系电话"  width="200">
      </el-table-column>
      <el-table-column prop="role" label="角色" sortable width="200">
      </el-table-column>
      <el-table-column prop="operate"  label="操作" width="400">
        <template slot-scope="scope">
          <el-button type="success" icon="el-icon-edit" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm
              confirm-button-text="确定"
              cancle-button-text="我再想想"
              icon="el-icon-info"
              title="确定删除吗？"
              @confirm="del(scope.row.userId)"
          >
            <el-button type="danger" icon="el-icon-delete" slot="reference"  style="margin: 5px">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>

    </el-table>

    <!--         分页  -->
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

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="400px">
      <el-form label-width="80px">
        <el-form-item label="用户名" >
          <el-input v-model="form.userName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" >
          <el-input v-model="form.passWord" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" >
          <el-input v-model="form.tel" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色" >
          <el-select v-model="form.role" placeholder="请选择用户角色">
            <el-option label="用户" value="用户"></el-option>
            <el-option label="管理员" value="管理员"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "User",
  data(){
   return{
     tableData: [],
     total:0,
     pageNum:1,
     pageSize:2,
     userName:"",
     passWord:"",
     tel:"",
     role:"",
     input:'',
     collapseBtnClass:'el-icon-s-fold',
     isCollapse:false,
     form:{
       userId:"",
       userName:"",
       passWord:"",
       tel:"",
       role:""
     },
     popoverVisible:false,
     multipleSelection:[],
     headerBg:'headerBg',
     dialogFormVisible:false
   }
  },
  created() {
   this.load()
  },
  methods:{
    load(){
      // fetch("http://localhost:8023/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&username="+this.username+"&tel="+this.tel+"&role="+this.role)
      //     .then(res=> res.json()).then(res=>{
      //   console.log(res)
      //   this.tableData = res.data
      //   this.total = res.total
      // })

      // request.get("/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&userName="+this.userName+"&tel="+this.tel+"&role="+this.role).then(res=>{
      //   // console.log(res)
      //   this.tableData = res.records
      //   this.total = res.total
      //   console.log(this.tableData)
      // })
      request.get("/user/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          userName:this.userName,
          tel:this.tel,
          role:this.role
        }
      }).then(res=>{
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    save(){
      request.post("/user",this.form).then(res=>{
        if(res){
          this.$message.success("保存成功！")
        }else{
          this.$message.error("保存失败！")
        }
        this.dialogFormVisible = false
      })
    },
    del(id){
      // console.log(id)
      request.delete("/user/"+id).then(res=>{
        if(res){
          this.$message.success("删除成功！")
          this.load()
        }else{
          this.$message.error("删除失败！")
        }
        this.dialogFormVisible = false
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
      this.userName=""
      this.tel=""
      this.role=""
      this.load()
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row){
      this.form = row
      this.dialogFormVisible = true
    },
    handleSelectionChange(val){
      // console.log(val)
      this.multipleSelection = val

    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.userId)  //[{},{},{}]  --> [|1,2,3]
      request.post("/user/del/batch",ids).then(res=>{
        if(res){
          this.$message.success("批量删除成功！")
          this.load()
        }else{
          this.$message.error("批量删除失败！")
        }
        // this.dialogFormVisible = false
      })
    }
  }
}
</script>
<style scoped>
</style>