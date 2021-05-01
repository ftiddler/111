<!--数据表格-->
<template>
  <div>
    <el-card class="member-box-card">
      <el-input
        id="input-field"
        v-model="search"
        size="mini"
        placeholder="输入物料名称进行搜索"
      />
      <el-table id="member-table" ref="memberTable" :key="itemKey" v-loading="loading" stripe border height="700" style="width: 100%" :data="searchHandler()">
        <el-table-column
          prop="id"
          label="序号"
        />
        <el-table-column
          prop="materialName"
          label="物资名称"
        />
        <el-table-column
          prop="modelSpec"
          label="型号及规格"
        />
        <el-table-column
          prop="measureUnit"
          label="计量单位"
        />
        <el-table-column
          prop="materialType"
          label="物料类型"
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
        <el-form-item label="物资名称">
          <el-input v-model="updForm.materialName" />
        </el-form-item>
        <el-form-item label="型号及规格">
          <el-input v-model="updForm.modelSpec" />
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="updForm.measureUnit" />
        </el-form-item>
        <el-form-item label="物料类型">
          <el-input v-model="updForm.materialType" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updFormCancel()">取 消</el-button>
        <el-button type="primary" @click="updFormConfirm()">确 定</el-button>
      </div>
    </el-dialog>
    <!--添加弹窗-->
    <el-dialog title="添加物料" :visible.sync="insFormVisible" :before-close="handleInsClose" width="40%">
      <el-form class="action-form" :model="insForm" label-width="100px">
        <el-form-item label="物资名称">
          <el-input v-model="insForm.chgdMaterialName" />
        </el-form-item>
        <el-form-item label="型号及规格">
          <el-input v-model="insForm.chgdModelSpec" />
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="insForm.chgdMeasureUnit" />
        </el-form-item>
        <el-form-item label="物料类型">
          <el-input v-model="insForm.chgdMaterialType" />
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
import { deleteById, insertMaterial, selAllMaterial, updMaterialInfo } from '@/api/system-management/codeing-management'
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
        materialName: '',
        modelSpec: '',
        measureUnit: '',
        materialType: ''
      },
      insForm: {
        chgdmaterialName: '',
        chgdModelSpec: '',
        chgdmeasureUnit: '',
        chgdMaterialType: ''
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
    },
    searchHandler() {
      return this.tableData.filter(data => !this.search || data.materialName.toLowerCase().includes(this.search.toLowerCase()))
    },
    handleEdit(index, row) {
      this.editFormVisible = true
      this.updForm.index = index
      this.updForm.id = this.tableData[index].id
      this.updForm.materialName = row.materialName
      this.updForm.modelSpec = row.modelSpec
      this.updForm.measureUnit = row.measureUnit
      this.updForm.materialType = row.materialType
    },
    updFormConfirm() {
      this.editFormVisible = false
      updMaterialInfo(
        this.updForm.materialName,
        this.updForm.modelSpec,
        this.updForm.measureUnit,
        this.updForm.materialType,
        this.updForm.id
      ).then((response) => {
        this.$message.success(response.message)
        this.loading = true
        selAllMaterial().then((response) => {
          this.tableData = response.data
          this.loading = false
          this.itemKey = Math.random()
        })
        this.updForm.materialName = ''
        this.updForm.modelSpec = ''
        this.updForm.measureUnit = ''
        this.updForm.materialType = ''
      }).catch((error) => {
        console.log(error)
      })
    },
    updFormCancel() {
      this.editFormVisible = false
      this.updForm.materialName = ''
      this.updForm.modelSpec = ''
      this.updForm.measureUnit = ''
      this.updForm.materialType = ''
    },
    handleEditClose() {
      this.editFormVisible = false
      this.updForm.materialCode = ''
      this.updForm.materialName = ''
      this.updForm.modelSpec = ''
      this.updForm.measureUnit = ''
      this.updForm.materialType = ''
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
          selAllMaterial().then((response) => {
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
      if (this.insForm.chgdMaterialType === '' || this.insForm.chgdMaterialName === '' || this.insForm.chgdModelSpec === '' || this.insForm.chgdMeasureUnit === '' || this.insForm.chgdMeasureUnit === '') {
        this.$message.error('信息不能为空')
      } else {
        insertMaterial(this.insForm.chgdMaterialName, this.insForm.chgdModelSpec, this.insForm.chgdMeasureUnit, this.insForm.chgdMaterialType).then(response => {
          this.$message.success(response.message)
          this.insFormVisible = false
          this.loading = true
          selAllMaterial().then((response) => {
            this.tableData = response.data
            this.loading = false
            this.itemKey = Math.random()
          })
          this.insForm.chgdMaterialName = ''
          this.insForm.chgdModelSpec = ''
          this.insForm.chgdMeasureUnit = ''
          this.insForm.chgdMaterialType = ''
        })
      }
    },
    insFormCancel() {
      this.insFormVisible = false
      this.insForm.chgdMaterialName = ''
      this.insForm.chgdModelSpec = ''
      this.insForm.chgdMeasureUnit = ''
      this.insForm.chgdMaterialType = ''
    },
    handleInsClose() {
      this.insFormVisible = false
      this.insForm.chgdMaterialName = ''
      this.insForm.chgdModelSpec = ''
      this.insForm.chgdMeasureUnit = ''
      this.insForm.chgdMaterialType = ''
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
