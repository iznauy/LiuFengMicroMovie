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
        <a-button type="primary" size="large" class="purchase-button" @click="changeInfo">{{buttonText}}</a-button>
      </div>
      <div class="movie-score container">
        <div class="score-list">
          <div v-for="index in [1, 3, 4]" :key="index" class="rate-info">
            <div style="display: inline-block; font-size: 28px; color: rgb(249, 213, 20); ">9.3</div>
            <div style="display: inline-block; margin-left: 20px;">
              <div>猫眼评分</div>
              <a-rate :defaultValue="4.5" allowHalf disabled/>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="movie-info">
      <Cinemas v-if="showCinemas"></Cinemas>
      <Comments v-else></Comments>
    </div>
  </div>
</template>

<script>
  import Cinemas from '@/components/movies/Cinemas';
  import Comments from '@/components/movies/Comments';

  export default {
    name: 'MovieInfo',
    components: {Comments, Cinemas},
    data() {
      return {
        showCinemas: false,
        buttonText: '购买电影票'
      };
    },
    methods: {
      changeInfo() {
        this.showCinemas = !this.showCinemas;
        this.buttonText = this.showCinemas ? '电影评论' : '购买电影票';
      }
    }
  };
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
    padding: 60px 360px;
    background-color: white;
  }
</style>
