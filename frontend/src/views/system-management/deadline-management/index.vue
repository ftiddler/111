<!--数据表格-->
<template>
  <div>
    <el-card class="member-box-card">
      <el-input
        id="input-field"
        v-model="search"
        size="mini"
        placeholder="输入截止日期进行搜索"
      />
      <el-table id="member-table" ref="memberTable" :key="itemKey" v-loading="loading" stripe border height="700" style="width: 100%" :data="searchHandler()">
        <el-table-column
          prop="id"
          label="序号"
        />
        <el-table-column
          prop="approver"
          label="审批人"
        />
        <el-table-column
          prop="closingDate"
          label="截止日期"
        />
        <el-table-column
          prop="auditor"
          label="审核人"
        />
        <el-table-column
          prop="prepare"
          label="编制人"
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
    <el-dialog title="修改信息" :visible.sync="editFormVisible" :before-close="handleEditClose" width="40%">
      <el-form class="action-form" :model="updForm" label-width="100px">
        <el-form-item label="序号">
          <el-input :placeholder="updForm.id" :disabled="true" />
        </el-form-item>
        <el-form-item label="审批人">
          <el-input v-model="updForm.approver" />
        </el-form-item>
        <el-form-item label="截止日期">
          <el-date-picker v-model="updForm.closingDate" type="datetime" placeholder="选择日期时间" />
        </el-form-item>
        <el-form-item label="审核人">
          <el-input v-model="updForm.auditor" />
        </el-form-item>
        <el-form-item label="编制人">
          <el-input v-model="updForm.prepare" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updFormCancel()">取 消</el-button>
        <el-button type="primary" @click="updFormConfirm()">确 定</el-button>
      </div>
    </el-dialog>
    <!--添加弹窗-->
    <el-dialog title="添加采购计划" :visible.sync="insFormVisible" :before-close="handleInsClose" width="40%">
      <el-form class="action-form" :model="insForm" label-width="100px">
        <el-form-item label="审批人">
          <el-input id="test" v-model="insForm.approver" />
        </el-form-item>
        <el-form-item label="截止日期">
          <el-date-picker v-model="insForm.closingDate" type="datetime" placeholder="选择日期时间" />
        </el-form-item>
        <el-form-item label="审核人">
          <el-input v-model="insForm.auditor" />
        </el-form-item>
        <el-form-item label="编制人">
          <el-input v-model="insForm.prepare" />
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
import { deleteById, insertPurchaseInfo, selectAll, updatePurchaseInfo } from '@/api/system-management/deadline-management'
import { getToken } from '@/utils/auth'
import dateFormat from 'dateformat'

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
        approver: '',
        closingDate: '',
        auditor: '',
        prepare: ''
      },
      insForm: {
        approver: '',
        closingDate: '',
        auditor: '',
        prepare: ''
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
        for (var i = 0; i < this.tableData.length; i++) {
          this.tableData[i].closingDate = dateFormat(this.tableData[i].closingDate, 'yyyy-mm-dd')
        }
      })
    },
    searchHandler() {
      return this.tableData.filter(data => !this.search || data.closingDate.toLowerCase().includes(this.search.toLowerCase()))
    },
    handleEdit(index, row) {
      this.editFormVisible = true
      this.updForm.index = index
      this.updForm.id = this.tableData[index].id
      this.updForm.approver = row.approver
      this.updForm.closingDate = row.closingDate
      this.updForm.auditor = row.auditor
      this.updForm.prepare = row.prepare
    },
    updFormConfirm() {
      this.editFormVisible = false
      updatePurchaseInfo(
        this.updForm.approver,
        this.updForm.closingDate,
        this.updForm.auditor,
        this.updForm.prepare,
        this.updForm.id
      ).then((response) => {
        this.$message.success(response.message)
        this.loading = true
        selectAll().then((response) => {
          this.tableData = response.data
          this.tableData = response.data
          for (var i = 0; i < this.tableData.length; i++) {
            this.tableData[i].closingDate = dateFormat(this.tableData[i].closingDate, 'yyyy-mm-dd')
          }
          this.loading = false
          this.itemKey = Math.random()
        })
        this.updForm.approver = ''
        this.updForm.closingDate = ''
        this.updForm.auditor = ''
        this.updForm.prepare = ''
      }).catch((error) => {
        console.log(error)
      })
    },
    updFormCancel() {
      this.updForm.approver = ''
      this.updForm.closingDate = ''
      this.updForm.auditor = ''
      this.updForm.prepare = ''
    },
    handleEditClose() {
      this.editFormVisible = false
      this.updForm.approver = ''
      this.updForm.closingDate = ''
      this.updForm.auditor = ''
      this.updForm.prepare = ''
    },
    handleDelete(index, row) {
      this.$confirm('此操作将删除采购计划信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteById(this.tableData[index].id).then((response) => {
          this.$message.success(response.message)
          this.loading = true
          selectAll().then((response) => {
            this.tableData = response.data
            this.tableData = response.data
            for (var i = 0; i < this.tableData.length; i++) {
              this.tableData[i].closingDate = dateFormat(this.tableData[i].closingDate, 'yyyy-mm-dd')
            }
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
      if (this.insForm.approver === '' || this.insForm.closingDate === '' || this.insForm.auditor === '' || this.insForm.prepare === '') {
        this.$message.error('信息不能为空')
      } else {
        insertPurchaseInfo(this.insForm.approver, this.insForm.closingDate, this.insForm.auditor, this.insForm.prepare).then(response => {
          this.$message.success(response.message)
          this.insFormVisible = false
          this.loading = true
          selectAll().then((response) => {
            this.tableData = response.data
            this.tableData = response.data
            for (var i = 0; i < this.tableData.length; i++) {
              this.tableData[i].closingDate = dateFormat(this.tableData[i].closingDate, 'yyyy-mm-dd')
            }
            this.loading = false
            this.itemKey = Math.random()
          })
          this.insForm.approver = ''
          this.insForm.closingDate = ''
          this.insForm.auditor = ''
          this.insForm.prepare = ''
        })
      }
    },
    insFormCancel() {
      this.insFormVisible = false
      this.insForm.approver = ''
      this.insForm.closingDate = ''
      this.insForm.auditor = ''
      this.insForm.prepare = ''
    },
    handleInsClose() {
      this.insFormVisible = false
      this.insForm.approver = ''
      this.insForm.closingDate = ''
      this.insForm.auditor = ''
      this.insForm.prepare = ''
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
