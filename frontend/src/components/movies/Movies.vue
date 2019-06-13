<template>
  <div class="content-container">
    <a-card :bordered="false">
      <a-card-grid v-for="(movie, index) in movieList" :key="index" class="movie-card-grid">
        <a-card class="movie-info" :bordered="false" hoverable @click="gotoMovieInfo(movie.id)">
          <img class="movie-poster" slot="cover" alt="example" :src="movie.picUrl"/>
          <div class="text-style">
            <h3>{{movie.name}}<br/></h3>
            <span>{{movie.score}}</span>
          </div>
        </a-card>
      </a-card-grid>
    </a-card>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex';

  export default {
    name: 'Movies',
    data() {
      return {
        movieList: []
      };
    },
    computed: {
      ...mapGetters(['baseUrl'])
    },
    mounted() {
      this.$http({
        url: `${this.baseUrl}/list`,
        method: 'GET',
      }).then((response) => {
        this.movieList = response.data;
        this.movieList.map((movie) => {
          let meanScore = 0;
          let sourceNum = 0;
          for (let source in movie.scores) {
            meanScore += movie.scores[source];
            sourceNum += 1;
          }
          meanScore = meanScore / sourceNum;
          movie['score'] = meanScore;
        });
      });
    },
    methods: {
      gotoMovieInfo(movieId) {
        this.$router.push(`/MovieInfo/${movieId}`);
      }
    }
  };
</script>

<style scoped>
  .content-container {
    background-color: white;
    padding: 30px 250px;
  }
  .movie-card-grid {
    width: 20%;
    min-width: 160px;
    padding: auto;
    box-shadow: none!important;
  }
  .movie-info {
    width: 160px;
    height: 270px;
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
