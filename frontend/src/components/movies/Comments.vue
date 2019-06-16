<template>
  <div>
    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="电影简介" key="1">{{description}}</a-tab-pane>
    </a-tabs>
    <a-tabs defaultActiveKey="1" style="margin-top: 60px">
      <a-tab-pane tab="电影评价" key="1">
        <a-list itemLayout="horizontal" :dataSource="comments">
          <a-list-item slot="renderItem" slot-scope="item">
            <a-comment :author="item.userName" avatar="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png">
              <p slot="content">{{item.content}}</p>
              <template slot="actions">
                <span>来自{{translate(item.source)}}</span>
                <span style="font-weight: bold; color: #2c3e50">{{item.tag}}</span>
                <span>{{item.score}}分</span>
              </template>
              <a-tooltip slot="datetime" :title="item.time">
                <span>{{item.time}}</span>
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
  import Language from '@/utils/Language';

  export default {
    name: 'Comments',
    props: { movieId: String, description: String},
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
        url: `${this.baseUrl}/comment?id=${this.movieId}`, method: 'GET'
      }).then((response) => {
        this.comments = response.data;
      });
    },
    methods: {
      translate(key) {
        return Language.translate(key);
      }
    }
  };
</script>

<style scoped>

</style>
