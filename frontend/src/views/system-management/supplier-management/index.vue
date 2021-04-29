<!--数据表格-->
<template>
  <div>
    <el-card class="member-box-card">
      <el-input
        id="input-field"
        v-model="search"
        size="mini"
        placeholder="输入编号或名称进行搜索"
      />
      <el-table id="member-table" :key="itemKey" v-loading="loading" stripe border height="700" style="width: 100%" :data="searchHandler()">
        <el-table-column
          prop="id"
          label="序号"
        />
        <el-table-column
          prop="regisNumber"
          label="登记编号"
        />
        <el-table-column
          prop="supName"
          label="供应商名称"
        />
        <el-table-column
          prop="supType"
          label="供应商类型"
        />
        <el-table-column
          prop="addressPostcode"
          label="联系地址及邮编"
        />
        <el-table-column
          prop="contacts"
          label="联系人"
        />
        <el-table-column
          prop="phone"
          label="联系电话"
        />
        <el-table-column
          prop="remark"
          label="备注"
        />
        <el-table-column align="right" width="200">
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
      <el-form class="action-form" :model="updForm" label-width="120px">
        <el-form-item label="序号">
          <el-input :placeholder="updForm.id" :disabled="true" />
        </el-form-item>
        <el-form-item label="登记编号">
          <el-input v-model="updForm.regisNumber" />
        </el-form-item>
        <el-form-item label="供应商名称">
          <el-input v-model="updForm.supName" />
        </el-form-item>
        <el-form-item label="供应商类型">
          <el-select v-model="updForm.supType" placeholder="请选择">
            <el-option label="普通" value="普通" />
            <el-option label="服务" value="服务" />
          </el-select>
        </el-form-item>
        <el-form-item label="联系地址及邮编">
          <el-input v-model="updForm.addressPostcode" />
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model="updForm.contacts" />
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="updForm.phone" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="updForm.remark" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updFormCancel()">取 消</el-button>
        <el-button type="primary" @click="updFormConfirm()">确 定</el-button>
      </div>
    </el-dialog>
    <!--添加弹窗-->
    <el-dialog title="添加供应商" :visible.sync="insFormVisible" :before-close="handleInsClose" width="40%">
      <el-form class="action-form" :model="insForm" label-width="120px">
        <el-form-item label="登记编号">
          <el-input v-model="insForm.chgdRegisNumber" />
        </el-form-item>
        <el-form-item label="供应商名称">
          <el-input v-model="insForm.chgdSupName" />
        </el-form-item>
        <el-form-item label="供应商类型">
          <el-select v-model="insForm.chgdSupType" placeholder="请选择">
            <el-option label="普通" value="普通" />
            <el-option label="服务" value="服务" />
          </el-select>
        </el-form-item>
        <el-form-item label="联系地址及邮编">
          <el-input v-model="insForm.chgdAddressPostcode" />
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model="insForm.chgdContacts" />
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input v-model="insForm.chgdPhone" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="insForm.chgdRemark" />
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
import { updateSupplier, deleteById, selectAll, insertSupplier } from '@/api/system-management/supplier-management'
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
        regisNumber: '',
        supName: '',
        supType: '',
        addressPostcode: '',
        contacts: '',
        phone: '',
        remark: ''
      },
      insForm: {
        chgdRegisNumber: '',
        chgdSupName: '',
        chgdSupType: '',
        chgdAddressPostcode: '',
        chgdContacts: '',
        chgdPhone: '',
        chgdRemark: ''
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
          if (this.tableData[i].supType === true) {
            this.tableData[i].supType = '服务'
          } else {
            this.tableData[i].supType = '普通'
          }
        }
      })
    },
    searchHandler() {
      return this.tableData.filter(data => !this.search ||
      data.regisNumber.toLowerCase().includes(this.search.toLowerCase()) ||
      data.supName.toLowerCase().includes(this.search.toLowerCase()))
    },
    handleEdit(index, row) {
      this.editFormVisible = true
      this.updForm.index = index
      this.updForm.id = this.tableData[index].id
      this.updForm.regisNumber = row.regisNumber
      this.updForm.supName = row.supName
      this.updForm.supType = row.supType
      this.updForm.addressPostcode = row.addressPostcode
      this.updForm.contacts = row.contacts
      this.updForm.phone = row.phone
      this.updForm.remark = row.remark
    },
    updFormConfirm() {
      if (this.updForm.supType === '普通') {
        this.updForm.supType = 0
      } else {
        this.updForm.supType = 1
      }
      this.editFormVisible = false
      updateSupplier(
        this.updForm.regisNumber,
        this.updForm.supName,
        this.updForm.supType,
        this.updForm.addressPostcode,
        this.updForm.contacts,
        this.updForm.phone,
        this.updForm.remark,
        this.updForm.id
      ).then((response) => {
        this.$message.success(response.message)
        this.loading = true
        selectAll().then((response) => {
          this.tableData = response.data
          for (var i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].supType === true) {
              this.tableData[i].supType = '服务'
            } else {
              this.tableData[i].supType = '普通'
            }
          }
          this.loading = false
          this.itemKey = Math.random()
        })
        this.updForm.regisNumber = ''
        this.updForm.supName = ''
        this.updForm.supType = ''
        this.updForm.addressPostcode = ''
        this.updForm.contacts = ''
        this.updForm.phone = ''
        this.updForm.remark = ''
      }).catch((error) => {
        console.log(error)
      })
    },
    updFormCancel() {
      this.editFormVisible = false
      this.updForm.regisNumber = ''
      this.updForm.supName = ''
      this.updForm.supType = ''
      this.updForm.addressPostcode = ''
      this.updForm.contacts = ''
      this.updForm.phone = ''
      this.updForm.remark = ''
    },
    handleEditClose() {
      this.editFormVisible = false
      this.updForm.regisNumber = ''
      this.updForm.supName = ''
      this.updForm.supType = ''
      this.updForm.addressPostcode = ''
      this.updForm.contacts = ''
      this.updForm.phone = ''
      this.updForm.remark = ''
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
          selectAll().then((response) => {
            this.tableData = response.data
            for (var i = 0; i < this.tableData.length; i++) {
              if (this.tableData[i].supType === true) {
                this.tableData[i].supType = '服务'
              } else {
                this.tableData[i].supType = '普通'
              }
            }
            this.itemKey = Math.random()
            this.loading = false
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
      if (
        this.insForm.chgdRegisNumber === '' ||
        this.insForm.chgdSupName === '' ||
        this.insForm.chgdSupType === '' ||
        this.insForm.chgdAddressPostcode === '' ||
        this.insForm.chgdContacts === '' ||
        this.insForm.chgdPhone === '' ||
        this.insForm.chgdRemark === '') {
        this.$message.error('信息不能为空')
      } else {
        if (this.insForm.chgdSupType === '普通') {
          this.insForm.chgdSupType = 0
        } else {
          this.insForm.chgdSupType = 1
        }
        insertSupplier(
          this.insForm.chgdRegisNumber,
          this.insForm.chgdSupName,
          this.insForm.chgdSupType,
          this.insForm.chgdAddressPostcode,
          this.insForm.chgdContacts,
          this.insForm.chgdPhone,
          this.insForm.chgdRemark
        ).then(response => {
          this.$message.success(response.message)
          this.insFormVisible = false
          this.loading = true
          selectAll().then((response) => {
            this.tableData = response.data
            for (var i = 0; i < this.tableData.length; i++) {
              if (this.tableData[i].supType === true) {
                this.tableData[i].supType = '服务'
              } else {
                this.tableData[i].supType = '普通'
              }
            }
            this.itemKey = Math.random()
            this.loading = false
          })
          this.insForm.chgdRegisNumber = ''
          this.insForm.chgdSupName = ''
          this.insForm.chgdSupType = ''
          this.insForm.chgdAddressPostcode = ''
          this.insForm.chgdContacts = ''
          this.insForm.chgdPhone = ''
          this.insForm.chgdRemark = ''
        })
      }
    },
    insFormCancel() {
      this.insFormVisible = false
      this.insForm.chgdRegisNumber = ''
      this.insForm.chgdSupName = ''
      this.insForm.chgdSupType = ''
      this.insForm.chgdAddressPostcode = ''
      this.insForm.chgdContacts = ''
      this.insForm.chgdPhone = ''
      this.insForm.chgdRemark = ''
    },
    handleInsClose() {
      this.insFormVisible = false
      this.insForm.chgdRegisNumber = ''
      this.insForm.chgdSupName = ''
      this.insForm.chgdSupType = ''
      this.insForm.chgdAddressPostcode = ''
      this.insForm.chgdContacts = ''
      this.insForm.chgdPhone = ''
      this.insForm.chgdRemark = ''
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
