<template>
  <div>
    <a-list itemLayout="horizontal" :bordered="false" class="cinema-list">
      <a-list-item v-for="cinema in visibleCinemaList" :key="cinema">
        <a-list-item-meta :description="cinema.description">
          <a slot="title">{{cinema.name}}</a>
        </a-list-item-meta>
        <div>
          <div v-for="url in cinema.urls" :key="url" class="cinema-link">
            <span class="price-info">{{url.price}}</span>
            <a-button type="primary" slot="actions" @click="jumpTo(url.url)" class="purchase-button">{{translate(url.source)}}</a-button>
          </div>
        </div>
      </a-list-item>
    </a-list>
    <div class="footer">
      <a-pagination v-model="currentPage" :total="cinemaList.length" @change="setPage"/>
    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex';
  import Language from '@/utils/Language';

  export default {
    name: 'Cinemas',
    props: { movieId: String },
    computed: {
      ...mapGetters(['baseUrl'])
    },
    data() {
      return {
        cinemaList: [],
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
        this.visibleCinemaList.map((cinema) => {
          cinema.description = [];
          cinema.urls = [];
          for (let source in cinema.details) {
            cinema.description.push(cinema.details[source].position);
            cinema.urls.push({source: source, url: cinema.details[source].url, price: cinema.details[source].price});
          }
          cinema.description = cinema.description.join(', ');
        });
      },
      translate(key) {
        return Language.translate(key);
      }
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
  .purchase-button {
    width: 100px;
  }
  .cinema-link {
    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
    margin: 10px 0;
  }
  .price-info {
    margin: auto 20px 2px auto;
    font-size: 16px;
    font-weight: bold;
  }
</style>
