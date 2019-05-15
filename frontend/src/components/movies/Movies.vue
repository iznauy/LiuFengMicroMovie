<template>
  <div class="content-container">
    <a-card :bordered="false">
      <a-card-grid v-for="(movie, index) in movieList" :key="index" class="movie-card-grid">
        <a-card class="movie-info" :bordered="false" hoverable @click="gotoMovieInfo">
          <img class="movie-poster" slot="cover" alt="example" src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"/>
          <div class="text-style">
            <h3>电影名称<br/></h3>
            <span>电影分数</span>
          </div>
        </a-card>
      </a-card-grid>
    </a-card>
  </div>
</template>

<script>
  export default {
    name: "Movies",
    data() {
      return {
        movieList: [1, 3, 4, 5, 4, 4, 4, 4, 3, 3, 43, 4, 4, 6, 4, 2, 2, 2, 2, 2, 2, 2, 3, 5, 7, 4, 3, ]
      }
    },
    methods: {
      gotoMovieInfo() {
        this.$router.push('/MovieInfo');
      }
    }
  }
</script>

<style scoped>
  .content-container {
    background-color: white;
    padding: 30px 250px;
  }
  .movie-card-grid {
    width: 20%;
    min-width: 200px;
    padding: 20px;
    box-shadow: none!important;
  }
  .movie-info {
    width: 160px;
  }
  .movie-poster {
    width: 160px;
    height: 200px;
  }
  .text-style {
    margin-top: 10px;
    text-align: center;
  }
</style>
