<template>
  <div>
    <a-list itemLayout="horizontal" :bordered="false" class="cinema-list">
      <a-list-item v-for="cinema in visibleCinemaList" :key="cinema">
        <a-list-item-meta :description="cinema.position">
          <a slot="title">{{cinema.name}}</a>
        </a-list-item-meta>
        <div style="margin-right: 20px">{{cinema.price}}</div>
        <div>
          <a-button type="primary" slot="actions" @click="jumpTo(cinema.url)">购买电影票</a-button>
        </div>
      </a-list-item>
    </a-list>
    <div class="footer">
      <a-pagination v-model="currentPage" total="50" @change="setPage"/>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex';

  export default {
    name: 'Cinemas',
    props: { movieId: Number },
    computed: {
      ...mapGetters(['baseUrl'])
    },
    data() {
      return {
        cinemaList: [1, 3, 4, 6, 4, 6, 4, 6, 4, 6],
        visibleCinemaList: [],
        currentPage: 1
      };
    },
    mounted() {
      this.$http({
        url: `${this.baseUrl}/cinema?id=${this.movieId}`, method: 'GET'
      }).then((response) => {
        this.cinemaList = response.data;
        this.setPage(1);
      });
    },
    methods: {
      jumpTo(url) {
        open(url);
      },
      setPage(newPage) {
        this.currentPage = newPage;
        const start = (this.currentPage - 1) * 10;
        this.visibleCinemaList = this.cinemaList.slice(start, Math.min(start + 10, this.cinemaList.length));
    }
  };
</script>

<style scoped>
  .cinema-list {
    width: 100%;
  }
  .footer {
    margin-top: 20px;
    display: flex;
    justify-content:center;
    align-items: center;
  }
</style>
