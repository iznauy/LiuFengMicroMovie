<template>
  <div style="width: 100%; height: 100%">
    <a-layout style="width: 100%; height: 100%">
      <a-layout-header><Header/></a-layout-header>
      <a-layout-content>
        <router-view></router-view>
      </a-layout-content>
    </a-layout>
  </div>
</template>

<script>
  import Header from './Header';

  export default {
    name: 'Home',
    components: {
      Header
    },
    mounted () {
      this.$router.push('/Movies');
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .ant-layout-header {
    padding: 0!important;
    background-color: white!important;
  }
</style>
