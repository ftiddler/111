<!--党员表格-->
<template>
  <div>
    <el-card class="member-box-card">
      <el-input
        id="input-field"
        v-model="search"
        size="mini"
        placeholder="输入关键字搜索"
      />
      <el-table id="member-table" ref="memberTable" :key="itemKey" stripe border height="700" style="width: 100%" :data="searchHandler()">
        <el-table-column
          prop="id"
          label="序号"
        />
        <el-table-column
          prop="matCode"
          label="物料编码"
        />
        <el-table-column
          prop="matName"
          label="物资名称"
        />
        <el-table-column
          prop="modelSpec"
          label="型号及规格"
        />
        <el-table-column
          prop="measUnit"
          lebel="计量单位"
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
    <el-dialog title="修改信息" :visible.sync="editFormVisible" width="30%">
      <el-form ref="updForm" :model="updForm">
        <p>{{ updForm.id }}</p>
        <el-form-item label="物料编码">
          <el-input v-model="updForm.matCode" />
        </el-form-item>
        <el-form-item label="物资名称">
          <el-input v-model="updForm.matName" />
        </el-form-item>
        <el-form-item label="型号及规格">
          <el-input v-model="updForm.modelSpec" />
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="updForm.measUnit" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updFormCancel()">取 消</el-button>
        <el-button type="primary" @click="updFormConfirm()">确 定</el-button>
      </div>
    </el-dialog>
    <!--添加弹窗-->
    <el-dialog title="添加党员" :visible.sync="insFormVisible" width="30%">
      <el-form :model="insForm">
        <el-form-item label="物料编码">
          <el-input v-model="insForm.matCode" />
        </el-form-item>
        <el-form-item label="物资名称">
          <el-input v-model="insForm.matName" />
        </el-form-item>
        <el-form-item label="型号及规格">
          <el-input v-model="insForm.modelSpec" />
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="insForm.measUnit" />
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
import { deleteById, insertMaterial, selAllMaterial, selMaxId, updMaterialInfo } from '@/api/system-management'
import { getToken } from '@/utils/auth'

export default {
  data() {
    return {
      tableData: [],
      editFormVisible: false,
      insFormVisible: false,
      search: '',
      maxId: '',
      itemKey: '',
      updForm: {
        id: '',
        index: '',
        matCode: '',
        matName: '',
        modelSpec: '',
        measUnit: '',
        chgdMatCode: '',
        chgdMatName: '',
        chgdModelSpec: '',
        chgdMeasUnit: ''
      },
      insForm: {
        chgdMatCode: '',
        chgdMatName: '',
        chgdModelSpec: '',
        chgdMeasUnit: ''
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
      selAllMaterial().then((response) => {
        this.tableData = response.data
      })
      selMaxId().then((response) => {
        this.maxId = response.data
      })
    },
    searchHandler() {
      return this.tableData.filter(data => !this.search || data.id.toLowerCase().includes(this.search.toLowerCase()) || data.matCode.toLowerCase().includes(this.search.toLowerCase()) || data.matName.toLowerCase().includes(this.search.toLowerCase()))
    },
    handleEdit(index, row) {
      this.editFormVisible = true
      this.updForm.index = index
      this.updForm.id = this.tableData[index].id
      this.updForm.matCode = row.matCode
      this.updForm.matName = row.matName
      this.updForm.modelSpec = row.modelSpec
      this.updForm.measUnit = row.measUnit
    },
    updFormConfirm() {
      var matCode = this.updForm.chgdMatCode
        ? this.updForm.chgdMatCode
        : this.updForm.matCode
      var matName = this.updForm.chgdMatName
        ? this.updForm.chgdMatName
        : this.updForm.matName
      var modelSpec = this.updForm.chgdModelSpec
        ? this.updForm.chgdModelSpec
        : this.updForm.modelSpec
      var measUnit = this.updForm.chgdMeasUnit
        ? this.updForm.chgdMeasUnit
        : this.updForm.measUnit
      this.editFormVisible = false
      updMaterialInfo(
        this.updForm.id,
        matCode,
        matName,
        modelSpec,
        measUnit
      ).then((response) => {
        this.$message.success(response.message)
        this.tableData[this.updForm.index] = { id: this.updForm.id, matCode: matCode, matName: matName, modelSpec: modelSpec, meadUnit: measUnit }
        this.itemKey = Math.random()
      }).catch((error) => {
        console.log(error)
      })
    },
    updFormCancel() {
      this.editFormVisible = false
    },
    handleDelete(index, row) {
      this.$confirm('此操作将删除物料信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteById(this.tableData[index].id).then((response) => {
          this.$message.success(response.message)
        })
        this.tableData.splice(index, 1)
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
      if (this.insForm.chgdMatCode === '' || this.insForm.chgdMatName === '' || this.insForm.chgdModelSpec === '' || this.insForm.chgdMeasUnit === '') {
        this.$message.error('信息不能为空')
      } else {
        insertMaterial(this.insForm.chgdMatCode, this.insForm.chgdMatName, this.insForm.chgdModelSpec, this.insForm.chgdMeasUnit).then(response => {
          this.$message.success(response.message)
        })
        this.maxId = this.maxId + 1
        this.tableData.push({ id: this.maxId, orgDescribe: this.insForm.seledOrgDescribe, orgName: this.insForm.seledOrgName, roleName: this.insForm.seledRoleName })
        this.itemKey = Math.random()
        this.insFormVisible = false
        this.insForm.chgdMatCode = ''
        this.insForm.chgdMatName = ''
        this.insForm.chgdModelSpec = ''
        this.insForm.chgdMeasUnit = ''
      }
    },
    insFormCancel() {
      this.insFormVisible = false
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
</style>
