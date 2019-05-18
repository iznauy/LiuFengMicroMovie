import Home from '@/components/common/Home';
import Movies from '@/components/movies/Movies';
import MovieInfo from '@/components/movies/MovieInfo';

import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: Home,
    name: 'Home',
    children: [
      {
        path: '/Movies',
        component: Movies,
        name: 'Movies'
      },
      {
        path: '/MovieInfo/:movieId?',
        component: MovieInfo,
        name: 'MovieInfo'
      }
    ]
  }
];
const router = new VueRouter({
  routes
});

export default router;
