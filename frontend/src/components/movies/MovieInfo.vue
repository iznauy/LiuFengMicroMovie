<template>
  <div>
    <div class="movie-header">
      <img src="https://p0.meituan.net/movie/f29c0f9ff0340d00085f4bc1a395ecf02603950.jpg" alt="" class="poster container">
      <div class="movie-detail container">
        <div style="font-size: 28px;">电影中文名</div>
        <div style="font-size: 20px;">English Name</div>
        <div style="margin-top: 32px;">电影时长：108分钟</div>
        <div>电影分类：冒险、奇幻</div>
        <div>电影导演：詹姆斯·卡梅隆</div>
        <div>上映时间：2019-05-17（未上映）</div>
        <a-button type="primary" size="large" class="purchase-button">购买电影票</a-button>
      </div>
      <div class="movie-score container">
        <div class="score-list">
          <div v-for="index in [1, 3, 4]" :key="index" class="rate-info">
            <div style="display: inline-block; font-size: 28px; color: rgb(249, 213, 20); ">9.3</div>
            <div style="display: inline-block; margin-left: 20px;">
              <div>猫眼评分</div>
              <a-rate :defaultValue="4.65" allowHalf disabled/>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="movie-info">
      <a-tabs defaultActiveKey="1">
        <a-tab-pane tab="电影简介" key="1">
          蒂姆·古德曼（贾斯提斯·史密斯 饰） 为寻找下落不明的父亲来到莱姆市，意外与父亲的前宝可梦搭档大侦探皮卡丘（瑞恩·雷诺兹 配音）相遇，并惊讶地发现自己是唯一能听懂皮卡丘说话的人类，他们决定组队踏上揭开真相的刺激冒险之路。探案过程中他们邂逅了各式各样的宝可梦，并意外发现了一个足以毁灭整个宝可梦宇宙的惊天阴谋。
        </a-tab-pane>
      </a-tabs>
      <a-tabs defaultActiveKey="1" style="margin-top: 100px">
        <a-tab-pane tab="电影评价" key="1">
          <a-list itemLayout="horizontal" :dataSource="data">
            <a-list-item slot="renderItem" slot-scope="item">
              <a-comment :author="item.author" :avatar="item.avatar">
                <template slot="actions">
                  <span v-for="action in item.actions" :key="action">{{action}}</span>
                </template>
                <p slot="content">{{item.content}}</p>
                <a-tooltip slot="datetime" :title="item.datetime.format('YYYY-MM-DD HH:mm:ss')">
                  <span>{{item.datetime.fromNow()}}</span>
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
  import moment from 'moment';

  export default {
    name: "MovieInfo",
    data() {
      return {
        data: [
          {
            actions: ['Reply to'],
            author: 'Han Solo',
            avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
            content: 'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
            datetime: moment().subtract(1, 'days'),
          },
          {
            actions: ['Reply to'],
            author: 'Han Solo',
            avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
            content: 'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
            datetime: moment().subtract(2, 'days'),
          },
        ],
        movieList: [1, 3, 4, 5, 4, 4, 4, 4, 3, 3, 43, 4, 4, 6, 4, 2, 2, 2, 2, 2, 2, 2, 3, 5, 7, 4, 3, ]
      }
    }
  }
</script>

<style scoped>
  .movie-header {
    display: flex;
    width: 100%;
    height: 400px;
    padding: 0 360px;
    background: linear-gradient(to right, rgba(61, 40, 68, 1), rgba(68, 45, 66, 0.9), rgba(83, 51, 64, 0.8), rgba(50, 35, 70, 0.9), rgba(48, 34, 70, 0.95), rgba(43, 33, 71, 1));
  }
  .movie-header .container {
    width: 240px;
    height: 350px;
    margin-top: auto;
    margin-bottom: -20px;
  }
  .poster {
    box-shadow: 10px 15px 20px black;
  }
  .movie-detail {
    margin-left: 50px;
    color: white;
    font-weight: bolder;
    display: inline-block;
  }
  .movie-detail div {
    margin: 8px;
  }
  .purchase-button {
    margin-top: 32px;
    width: 200px;
  }
  .movie-score {
    margin-left: 200px;
    color: white;
    font-weight: bolder;
    display: inline-flex;
  }
  .score-list {
    margin-top: auto;
    margin-bottom: 60px;
  }
  .rate-info {
    margin-top: 16px;
    display: flex;
    justify-content:center;
    align-items: center;
  }

  .movie-info {
    padding: 60px 360px 0 360px;
    background-color: white;
  }
</style>
