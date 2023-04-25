<template>
  <div>
    <div class="block">
      <el-timeline>
        <el-timeline-item
          :timestamp="blog.createTime"
          placement="top"
          v-for="blog in blogList"
        >
          <el-card>
            <h4>
              <router-link
                :to="{ name: 'BlogDetail', params: { blogId: blog.id } }"
              >
                {{ blog.title }}
              </router-link>
            </h4>
            <p>{{ blog.description }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>

      <el-pagination
        background
        layout="prev, pager, next"
        :current-page="page"
        :page-size="pageSize"
        :total="total"
        @current-change="getBlogList"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "BlogListView",
  data() {
    return {
      blogList: {},
      page: 1,
      pageSize: 5,
      total: 0,
    };
  },
  methods: {
    getBlogList(page) {
      this.$axios
        .get("/blog/list", {
          params: {
            page: page,
            pageSize: this.pageSize,
          },
        })
        .then((res) => {
          // console.log(res.data)
          this.blogList = res.data.data.records;
          this.page = res.data.data.current;
          this.pageSize = res.data.data.size;
          this.total = res.data.data.total;
        });
    },
  },
  mounted() {
    this.getBlogList(1);
  },
};
</script>

<style scoped>
.block {
  margin: 0 auto;
  width: 900px;
}
</style>