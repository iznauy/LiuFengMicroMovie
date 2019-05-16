<template>
  <div>
    <a-list itemLayout="horizontal" :bordered="false" class="cinema-list">
      <a-list-item v-for="cinema in cinemaList" :key="cinema">
        <a-list-item-meta description="地址：秦淮区洪武路26号天丰大厦裙楼（正洪街路与中山东路间">
          <a slot="title">金陵工人影城</a>
        </a-list-item-meta>
        <div style="margin-right: 20px">39元</div>
        <div>
          <a-button type="primary" slot="actions">购买电影票</a-button>
        </div>
      </a-list-item>
    </a-list>
    <div class="footer">
      <a-pagination v-model="currentPage" total="50"/>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Cinemas',
    data() {
      return {
        cinemaList: [1, 3, 4, 6, 4, 6, 4, 6, 4, 6],
        currentPage: 1
      };
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
