<template>
  <div>
    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="电影简介" key="1">{{description}}</a-tab-pane>
    </a-tabs>
    <a-tabs defaultActiveKey="1" style="margin-top: 60px">
      <a-tab-pane tab="电影评价" key="1">
        <a-list itemLayout="horizontal" :dataSource="comments">
          <a-list-item slot="renderItem" slot-scope="item">
            <a-comment :author="item.userName" :avatar="item.avatar">
              <p slot="content">{{item.content}}</p>
              <template slot="actions">
                <span>来自{{item.source}}</span>
                <span>{{item.score}}分</span>
              </template>
              <a-tooltip slot="datetime" :title="item.time.format('YYYY-MM-DD HH:mm:ss')">
                <span>{{item.time.fromNow()}}</span>
              </a-tooltip>
            </a-comment>
          </a-list-item>
        </a-list>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex';

  export default {
    name: 'Comments',
    props: { movieId: Number, description: String},
    computed: {
      ...mapGetters(['baseUrl'])
    },
    data() {
      return {
        comments: []
      };
    },
    mounted() {
      this.$http({
        url: `${this.baseUrl}/comments`, method: 'GET',
        params: { id: this.movieId }
      }).then((response) => {
        this.comments = response.data;
      });
    },
  };
</script>

<style scoped>

</style>
