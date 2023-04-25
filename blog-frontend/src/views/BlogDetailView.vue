<template>
  <div>
    <div class="blog">
      <h2>{{ blog.title }}</h2>
      <el-link icon="el-icon-edit">
        <router-link :to="{ name: 'BlogEdit', params: { blogId: blog.id } }">
          编辑
        </router-link>
      </el-link>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>
  </div>
</template>

<script>
import "github-markdown-css";

export default {
  name: "BlogDetailVue",
//   components: { Header },
  data() {
    return {
      blog: {
        id: "",
        title: "",
        content: "",
      },
      ownBlog: false,
    };
  },
  created() {
    const blogId = this.$route.params.blogId;
    console.log(blogId);
    this.$axios.get("/blog/" + blogId).then((res) => {
      const blog = res.data.data;
      this.blog.id = blog.id;
      this.blog.title = blog.title;

      var MardownIt = require("markdown-it");
      var md = new MardownIt();

      var result = md.render(blog.content);
      this.blog.content = result;
    });
  },
};
</script>

<style scoped>
.blog {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    width: 100%;
    min-height: 700px;
    padding: 20px 15px;
  }
</style>