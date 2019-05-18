<template>
  <div class="content-container">
    <a-card :bordered="false">
      <a-card-grid v-for="(movie, index) in movieList" :key="index" class="movie-card-grid">
        <a-card class="movie-info" :bordered="false" hoverable @click="gotoMovieInfo">
          <img class="movie-poster" slot="cover" alt="example" src="https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"/>
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
        movieList: [1, 3, 4, 5, 4, 4, 4, 4, 3, 3, 43, 4, 4, 6, 4, 2, 2, 2, 2, 2, 2, 2, 3, 5, 7, 4, 3, ]
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
      gotoMovieInfo() {
        this.$router.push('/MovieInfo');
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
