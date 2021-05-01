<!--数据表格-->
<template>
  <div>
    <el-card class="member-box-card">
      <el-input
        id="input-field"
        v-model="search"
        size="mini"
        placeholder="输入部门名称进行搜索"
      />
      <el-table id="member-table" ref="memberTable" :key="itemKey" v-loading="loading" stripe border height="700" style="width: 100%" :data="searchHandler()">
        <el-table-column
          prop="id"
          label="序号"
        />
        <el-table-column
          prop="departmentName"
          label="部门名称"
        />
        <el-table-column
          prop="principal"
          label="负责人"
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
            >修改</el-button>
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
    <el-dialog title="修改信息" :visible.sync="editFormVisible" :before-close="handleEditClose" width="30%">
      <el-form class="action-form" :model="updForm" label-width="100px">
        <el-form-item label="序号">
          <el-input :placeholder="updForm.id" :disabled="true" />
        </el-form-item>
        <el-form-item label="部门名称">
          <el-input v-model="updForm.departmentName" />
        </el-form-item>
        <el-form-item label="负责人">
          <el-input v-model="updForm.principal" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updFormCancel()">取 消</el-button>
        <el-button type="primary" @click="updFormConfirm()">确 定</el-button>
      </div>
    </el-dialog>
    <!--添加弹窗-->
    <el-dialog title="添加部门" :visible.sync="insFormVisible" :before-close="handleInsClose" width="40%">
      <el-form class="action-form" :model="insForm" label-width="100px">
        <el-form-item label="部门名称">
          <el-input v-model="insForm.departmentName" />
        </el-form-item>
        <el-form-item label="负责人">
          <el-input v-model="insForm.principal" />
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
import { selectAll, updateDepartment,deleteById, insertDepartment } from '@/api/system-management/department-management'
import { getToken } from '@/utils/auth'

export default {
  data() {
    return {
      tableData: [],
      editFormVisible: false,
      insFormVisible: false,
      search: '',
      itemKey: '',
      loading: true,
      updForm: {
        id: '',
        index: '',
        departmentName: '',
        principal: ''
      },
      insForm: {
        departmentName: '',
        principal: ''
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
        this.loading = false
        this.itemKey = Math.random()
      })
    },
    searchHandler() {
      return this.tableData.filter(data => !this.search || data.departmentName.toLowerCase().includes(this.search.toLowerCase()))
    },
    handleEdit(index, row) {
      this.editFormVisible = true
      this.updForm.index = index
      this.updForm.id = this.tableData[index].id
      this.updForm.departmentName = this.tableData[index].departmentName
      this.updForm.principal = this.tableData[index].principal
    },
    updFormConfirm() {
      this.editFormVisible = false
      updateDepartment(
        this.updForm.departmentName,
        this.updForm.principal,
        this.updForm.id
      ).then((response) => {
        this.$message.success(response.message)
        this.loading = true
        this.init()
        this.updForm.departmentName = ''
        this.updForm.principal = ''
      }).catch((error) => {
        console.log(error)
      })
    },
    updFormCancel() {
      this.editFormVisible = false
      this.updForm.departmentName = ''
      this.updForm.principal = ''
    },
    handleEditClose() {
      this.editFormVisible = false
      this.updForm.departmentName = ''
      this.updForm.principal = ''
    },
    handleDelete(index, row) {
      this.$confirm('此操作将删除物料信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteById(this.tableData[index].id).then((response) => {
          this.$message.success(response.message)
          this.loading = true
          this.init()
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
      if (this.insForm.departmentName === '' || this.insForm.principal === '') {
        this.$message.error('信息不能为空')
      } else {
        insertDepartment(this.insForm.departmentName, this.insForm.principal).then(response => {
          this.$message.success(response.message)
          this.insFormVisible = false
          this.loading = true
          this.init()
          this.insForm.departmentName = ''
          this.insForm.principal = ''
        })
      }
    },
    insFormCancel() {
      this.insFormVisible = false
      this.insForm.departmentName = ''
      this.insForm.principal = ''
    },
    handleInsClose() {
      this.insFormVisible = false
      this.insForm.departmentName = ''
      this.insForm.principal = ''
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
