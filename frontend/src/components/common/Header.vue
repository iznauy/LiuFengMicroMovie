<template>
  <div class="header-container">
    <div style="display: inline-block;">
      <img src="../../assets/maoyan.png" alt="" class="logo">
      <span class="title">流锋微电影</span>
    </div>
    <div style="display: inline-block; margin: 0 auto 0 20px;">
      <a-menu mode="horizontal" class="menu" @click="handleClick" defaultSelectedKeys="['/Movies']">
        <a-menu-item key="/Movies">电影列表</a-menu-item>
      </a-menu>
    </div>
    <div style="display: inline-block; float: right;">
      <a-input-search placeholder="input search text"
              @search="onSearch" size="large" enterButton></a-input-search>
    </div>
  </div>
</template>

<script>
  export default {
    methods: {
      handleClick (item) {
        this.$router.push(item.key);
      }
    }
  };
</script>

<style scoped>
  .header-container {
    padding: 0 300px;
    background-color: #F8F8F8;
    align-content: baseline;
  }
  .logo {
    float: left;
    width: 64px;
    height: 64px;
  }
  .title {
    font-size: 20px;
    color: #E81A1B;
    margin: auto 20px;
  }
  .menu {
    font-size: 16px;
    line-height: 64px;
    height: 64px;
    background-color: #F8F8F8;
  }
</style>
