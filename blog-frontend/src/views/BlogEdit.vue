<template>
    <div>
        <el-container>
            <el-header>Header</el-header>
            <el-main>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="ruleForm.title"></el-input>
                    </el-form-item>

                    <el-form-item label="摘要" prop="description">
                        <el-input type="textarea" v-model="ruleForm.description"></el-input>
                    </el-form-item>

                    <el-form-item label="内容" prop="content">
                        <mavon-editor v-model="ruleForm.content"></mavon-editor>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
  name: "BlogEdit",
  data() {
    return {
      ruleForm: {
        id: '',
        title: '',
        description: '',
        content: ''
      },
      rules: {
        title: [
          {required: true, message: '请输入博客标题', trigger: 'blur'},
          {min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur'}
        ],
        description: [
          {required: true, message: '请输入摘要', trigger: 'blur'}
        ],
        content: [
          {required: true, message: '请输入内容', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // console.log(this.ruleForm)
            this.$axios.post('/blog/edit', this.ruleForm, {
                headers: {
                    'Authorization': localStorage.getItem('token')
                }
            }).then(res => {
                console.log(res)
                this.$alert('操作成功', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$router.push('/blog-list')
                    }
                })
            })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  created() {
    const blogId = this.$route.params.blogId
    if(blogId) {
      this.$axios.get('/blog/' + blogId).then(res => {
        const blog = res.data.data
        this.ruleForm.id = blog.id
        this.ruleForm.title = blog.title
        this.ruleForm.description = blog.description
        this.ruleForm.content = blog.content
      })
    }
  }
}
</script>

<style scoped>
.el-container {
    /*width: 900px;*/
    margin: 0 auto;
}


.el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
}

.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
}

body > .el-container {
    margin-bottom: 40px;

}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}
</style>