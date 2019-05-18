<template>
  <div>
    <div class="movie-header">
      <img :src="movieInfo.picUrl" alt="" class="poster container">
      <div class="movie-detail container">
        <div style="font-size: 28px;">{{movieInfo.name}}</div>
        <div style="font-size: 20px;">{{movieInfo.EnName}}</div>
        <div style="margin-top: 32px;">电影时长：{{movieInfo.length}}</div>
        <div>电影分类：{{movieInfo.categories.join(", ")}}</div>
        <div>电影导演：{{movieInfo.directors.join(", ")}}</div>
        <div>上映时间：{{movieInfo.releaseTime}}</div>
        <a-button type="primary" size="large" class="purchase-button" @click="changeInfo">{{buttonText}}</a-button>
      </div>
      <div class="movie-score container">
        <div class="score-list">
          <div v-for="(source, key) in movieInfo.filmDetailVOMap" :key="source" class="rate-info">
            <div style="display: inline-block; font-size: 28px; color: rgb(249, 213, 20); ">{{source.score}}</div>
            <div style="display: inline-block; margin-left: 20px;">
              <div>{{key}}</div>
              <a-rate :defaultValue="Math.floor(source.score) / 2" allowHalf disabled/>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="movie-info">
      <Cinemas v-if="showCinemas" :movieId="movieId"></Cinemas>
      <Comments v-else :movieId="movieId" :description="movieInfo.description"></Comments>
    </div>
  </div>
</template>

<script>
  import Cinemas from '@/components/movies/Cinemas';
  import Comments from '@/components/movies/Comments';
  import { mapGetters } from 'vuex';

  export default {
    name: 'MovieInfo',
    components: {Comments, Cinemas},
    data() {
      return {
        showCinemas: false,
        buttonText: '购买电影票',
        movieInfo: Object,
        movieId: undefined
      };
    },
    computed: {
      ...mapGetters(['baseUrl'])
    },
    mounted() {
      this.movieId = this.$route.params.movieId;
      this.$http({
        url: `${this.baseUrl}/info?id=${this.movieId}`, method: 'GET'
      }).then((response) => {
        this.movieInfo = response.data;
      });
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
    padding: 0 250px;
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
    justify-content: flex-end;
    align-items: end;
  }
  .movie-info {
    padding: 60px 360px;
    background-color: white;
  }
</style>
