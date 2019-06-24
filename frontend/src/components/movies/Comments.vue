<template>
  <div>
    <a-tabs defaultActiveKey="1">
      <a-tab-pane tab="电影简介" key="1">{{description}}</a-tab-pane>
    </a-tabs>
    <div style="position: relative; margin-top: 60px">
      <a-dropdown class="dropdown">
        <a class="ant-dropdown-link">
          {{selectedTagType}}<a-icon type="down"/>
        </a>
        <a-menu slot="overlay" @click="filterTags">
          <a-menu-item key="所有"><a-icon type="user" />所有</a-menu-item>
          <a-menu-item key="好评"><a-icon type="user" />好评</a-menu-item>
          <a-menu-item key="中评"><a-icon type="user" />中评</a-menu-item>
          <a-menu-item key="差评"><a-icon type="user" />差评</a-menu-item>
        </a-menu>
      </a-dropdown>
      <a-tabs defaultActiveKey="1">
        <a-tab-pane tab="电影评价" key="1">
          <a-list itemLayout="horizontal" :dataSource="visibleComments">
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
        comments: [],
        visibleComments: [],
        selectedTagType: '所有'
      };
    },
    mounted() {
      this.$http({
        url: `${this.baseUrl}/comment?id=${this.movieId}`, method: 'GET'
      }).then((response) => {
        this.comments = response.data;
        this.visibleComments = response.data;
      });
    },
    methods: {
      translate(key) {
        return Language.translate(key);
      },
      filterTags(item) {
        const tag = item.key;
        if (tag === '所有') {
          this.visibleComments = this.comments;
        } else {
          this.selectedTagType = tag;
          this.visibleComments = this.comments.filter((item => {
            return item.tag === tag;
          }));
        }
      }
    }
  };
</script>

<style scoped>
.dropdown {
  position: absolute;
  right: 16px;
  top: 16px;
  font-size: 16px;
  font-weight: bold;
  z-index: 3;
}
</style>
