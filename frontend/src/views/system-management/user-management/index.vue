<!--数据表格-->
<template>
  <div>
    <el-card class="member-box-card">
      <el-input
        id="input-field"
        v-model="search"
        size="mini"
        placeholder="输入用户名或账号进行搜索"
      />
      <el-table id="member-table" ref="memberTable" :key="itemKey" v-loading="loading" stripe border height="700" style="width: 100%" :data="searchHandler()">
        <el-table-column
          prop="id"
          label="序号"
        />
        <el-table-column
          prop="account"
          label="账号"
        />
        <el-table-column
          prop="username"
          label="用户名"
        />
        <el-table-column align="right">
          <template slot="header" slot-scope="scope">
            <el-button
              size="mini"
              type="primary"
              @click="handleInsert(scope.$index, scope.row)"
            >添加</el-button>
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
            >修改密码</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!--编辑弹窗-->
    <el-dialog title="修改密码" :visible.sync="editFormVisible" :before-close="handleEditClose" width="40%">
      <el-form class="action-form" :model="updForm" label-width="100px">
        <el-form-item label="密码">
          <el-input v-model="updForm.password" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updFormCancel()">取 消</el-button>
        <el-button type="primary" @click="updFormConfirm()">确 定</el-button>
      </div>
    </el-dialog>
    <!--添加弹窗-->
    <el-dialog title="添加用户" :visible.sync="insFormVisible" :before-close="handleInsClose" width="40%">
      <el-form class="action-form" :model="insForm" label-width="100px">
        <el-form-item label="账号">
          <el-input id="test" v-model="insForm.account" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="insForm.password" />
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="insForm.username" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="insFormCancel()">取 消</el-button>
        <el-button type="primary" @click="insFormConfirm()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { changePasswordByAccount, deleteByAccount, insertUser, selectAll } from '@/api/system-management/user-management'
import { getToken } from '@/utils/auth'

export default {
  data() {
    return {
      tableData: [],
      editFormVisible: false,
      insFormVisible: false,
      search: '',
      itemKey: '',
      loading: false,
      updForm: {
        id: '',
        index: '',
        account: '',
        password: '',
        username: ''
      },
      insForm: {
        account: '',
        password: '',
        username: ''
      },
      headers: {
        Authorization: getToken()
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      selectAll().then((response) => {
        this.tableData = response.data
      })
    },
    searchHandler() {
      return this.tableData.filter(data => !this.search || data.account.toLowerCase().includes(this.search.toLowerCase()) || data.username.toLowerCase().includes(this.search.toLowerCase()))
    },
    handleEdit(index, row) {
      this.editFormVisible = true
      this.updForm.account = row.account
    },
    updFormConfirm() {
      if (this.updForm.password === '') {
        this.$message.error('密码不能为空')
      } else {
        this.editFormVisible = false
        changePasswordByAccount(this.updForm.password, this.updForm.account).then((response) => {
          this.$message.success(response.message)
          this.updForm.password = ''
        }).catch((error) => {
          console.log(error)
        })
      }
    },
    updFormCancel() {
      this.editFormVisible = false
      this.updForm.password = ''
    },
    handleEditClose() {
      this.editFormVisible = false
      this.updForm.password = ''
    },
    handleDelete(index, row) {
      this.$confirm('此操作将删除用户信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteByAccount(this.tableData[index].account).then((response) => {
          this.$message.success(response.message)
          this.loading = true
          selectAll().then((response) => {
            this.tableData = response.data
            this.loading = false
            this.itemKey = Math.random()
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handleInsert(index, row) {
      this.insFormVisible = true
    },
    insFormConfirm() {
      if (this.insForm.account === '' || this.insForm.password === '' || this.insForm.username === '') {
        this.$message.error('信息不能为空')
      } else {
        insertUser(this.insForm.account, this.insForm.password, this.insForm.username).then(response => {
          this.$message.success(response.message)
          this.insFormVisible = false
          this.loading = true
          selectAll().then((response) => {
            this.tableData = response.data
            this.loading = false
            this.itemKey = Math.random()
          })
          this.insForm.account = ''
          this.insForm.password = ''
          this.insForm.username = ''
        })
      }
    },
    insFormCancel() {
      this.insFormVisible = false
      this.insForm.account = ''
      this.insForm.password = ''
      this.insForm.username = ''
    },
    handleInsClose() {
      this.insFormVisible = false
      this.insForm.account = ''
      this.insForm.password = ''
      this.insForm.username = ''
    }
  }
}
</script>

<style>
  .member-box-card {
    width: 95%;
    margin: 20px auto;
  }
  #input-field {
    width: 200px;
    display: inline;
  }
  #member-table {
    margin-top: 5px;
  }
  .action-form {
    width: 400px;
  }

</style>
