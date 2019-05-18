import datetime as da
import asyncio
import requests

from flaskr.config import LOCATION_ID
from flaskr.data_model import FilmIntro, Film, Comment, CinemaInfo


def get(url: str, params=None):
    req = requests.get(url, params=params)
    if req.status_code == 200:
        return req.json()
    else:
        raise ValueError(f'request {url} failed')


class Crawler:

    def __init__(self, location_id=LOCATION_ID):
        self.location_id = location_id
        self.id_helper = IDHelper()

        self._hot_movies = []
        self._coming_movies = []

    def hot_movies(self):
        if self._hot_movies:
            return self._hot_movies

        api_url = f'https://api-m.mtime.cn/Showtime/LocationMovies.api?locationId={self.location_id}'
        res = get(api_url)
        movies = res['ms']

        result = []
        for m in movies:
            result.append(self._create_hot_film_intro(m))
        self._hot_movies = result
        return result

    def coming_movies(self):
        if self._coming_movies:
            return self._coming_movies

        api = f'https://api-m.mtime.cn/Movie/MovieComingNew.api?locationId={self.location_id}'
        movies = get(api)['moviecomings']

        result = [self._create_coming_film_intro(m) for m in movies]
        self._coming_movies = result
        return result

    def movie_detail(self, global_id):
        movie_id = self.id_helper.global_to_local(global_id)
        detail = self._get_movie_detail(movie_id)

        return self._create_film_detail(detail)

    def movie_comment(self, global_id):
        result = self._get_long_comments(global_id)
        result.extend(self._get_short_comments(global_id))

        return result

    def cinema_info(self, global_id, date=None):
        movie_id = self.id_helper.global_to_local(global_id)

        show_times = self._get_show_times(movie_id)
        if date is None:
            date = show_times[0]
        elif date not in show_times:
            raise ValueError('Invalid datetime for cinema_info')

        api = 'https://ticket-api-m.mtime.cn/Showtime/LocationMovieShowtimes.api'
        params = {
            'locationId': self.location_id,
            'movieId': movie_id,
            'date': date
        }

        result = []
        cinemas = get(api, params)['cs']
        for cinema in cinemas:
            result.append(self._create_cinema(global_id, cinema, date))

        return result

    def _get_show_times(self, movie_id):
        api = 'https://ticket-api-m.mtime.cn/Showtime/LocationMovieShowtimeDates.api'
        params = {
            'locationId': self.location_id,
            'movieId': movie_id
        }

        raw_info = get(api, params)
        show_times = []
        for date in raw_info['showtimeDates']:
            ts = date['seconds']
            show_times.append(da.datetime.fromtimestamp(ts).strftime('%Y%m%d'))

        return show_times

    def _create_cinema(self, global_id, cinema, date):
        name = cinema['cn']
        address = cinema['address']
        cinema_id = cinema['cid']
        movie_id = self.id_helper.global_to_local(global_id)
        min_price = cinema['minPrice'] / 100
        url = f'https://m.mtime.cn/#!/theater/{self.location_id}/{cinema_id}/date/{date}/movie/{movie_id}/'

        return CinemaInfo(global_id, name, address, min_price, url)

    def _get_long_comments(self, global_id):
        movie_id = self.id_helper.global_to_local(global_id)
        api = f'https://ticket-api-m.mtime.cn/Movie/HotLongComments.api?movieId={movie_id}&pageIndex=1'
        comments = get(api)['comments']

        return [self._create_long_comment(c, global_id) for c in comments]

    def _get_short_comments(self, global_id):
        movie_id = self.id_helper.global_to_local(global_id)
        api = f'https://api-m.mtime.cn/Showtime/HotMovieComments.api?movieId={movie_id}&pageIndex=1'
        comments = get(api)['data']['cts']

        return [self._create_short_comment(c, global_id) for c in comments]

    @staticmethod
    def _create_short_comment(comment, global_id):
        content = comment['ce']
        date_time = da.datetime.fromtimestamp(comment['cd']).strftime('%Y-%m-%d %H:%M:%S')
        username = comment['ca']
        rating = float(comment['cr'])

        return Comment(global_id, content, date_time, username, rating)

    @staticmethod
    def _create_long_comment(comment, global_id):
        content = comment['content']
        date_time = da.datetime.fromtimestamp(comment['modifyTime']).strftime('%Y-%m-%d %H:%M:%S')
        username = comment['nickname']
        rating = comment['rating']

        return Comment(global_id, content, date_time, username, rating)

    def _create_film_detail(self, detail: dict):
        basic_info = detail['data']['basic']

        name = basic_info['name']
        movie_id = basic_info['movieId']
        en_name = basic_info['nameEn']
        categories = basic_info['type']
        length = basic_info['mins']
        release_date = basic_info['releaseDate']
        rating = basic_info['overallRating']
        if not rating or rating <= 0:
            rating = 0
        person_count = basic_info['personCount']
        has_scored = bool(rating)
        img = basic_info['img']
        description = basic_info['story']
        global_id = self.id_helper.get_global_id(name, movie_id)
        director = basic_info['director']['name']

        box_office = detail['data']['boxOffice']
        total_box_des = box_office['totalBoxDes']

        return Film(global_id, name, en_name, categories, length,
                    release_date, rating, person_count, has_scored, total_box_des,
                    img, description, director)

    def _create_hot_film_intro(self, m: dict):
        name = m['tCn']
        movie_id = m['movieId']
        global_id = self.id_helper.get_global_id(name, movie_id)
        categories = [x.strip() for x in m['movieType'].split('/')]
        img = m['img']
        score = str(m['r']) if m['r'] and m['r'] > 0 else '0'
        length = self._get_movie_detail(m['movieId'])['data']['basic']['mins']
        intro = FilmIntro(global_id, name, categories, img, length, score)
        return intro

    def _create_coming_film_intro(self, movie):
        name = movie['title']
        movie_id = movie['id']
        global_id = self.id_helper.get_global_id(name, movie_id)
        categories = [x.strip() for x in movie['type'].split('/')]
        length = self._get_movie_detail(movie_id)['data']['basic']['mins']
        img = movie['image']
        return FilmIntro(global_id, name, categories, img, length, '0')

    def _get_movie_detail(self, movie_id):
        detail_url = f'https://ticket-api-m.mtime.cn/movie/detail.api'
        params = {
            'locationId': self.location_id,
            'movieId': movie_id
        }
        return get(detail_url, params)


class Singleton(type):
    """
    Define an Instance operation that lets clients access its unique
    instance.
    """

    def __init__(cls, name, bases, attrs, **kwargs):
        super().__init__(name, bases, attrs)
        cls._instance = None

    def __call__(cls, *args, **kwargs):
        if cls._instance is None:
            cls._instance = super().__call__(*args, **kwargs)
        return cls._instance


class IDHelper(metaclass=Singleton):

    def __init__(self):
        self.id_dict = {}
        self.movie_id_dict = {}
        self.count = 0

    def global_to_local(self, global_id):
        return self.id_dict[global_id]

    def get_global_id(self, movie_name, local_m_id):
        global_id = self._get_id_from_remote(movie_name)
        if local_m_id not in self.id_dict:
            self.id_dict[global_id] = local_m_id
        return global_id

    def _get_id_from_remote(self, movie_name):
        if movie_name in self.movie_id_dict:
            return self.movie_id_dict[movie_name]

        assigned_id = int(requests.get('http://127.0.0.1:8888', {'name': movie_name}).text)
        # assigned_id = self.count
        # self.count += 1
        self.movie_id_dict[movie_name] = assigned_id
        return assigned_id
