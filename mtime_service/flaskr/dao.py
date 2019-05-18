from flaskr.crawler import Crawler
import datetime

_crawler = Crawler()


def load_film_intros():
    result = _crawler.hot_movies()
    result.extend(_crawler.coming_movies())

    return result


def get_film(movie_gid):
    return _crawler.movie_detail(movie_gid)


def list_cinema(movie_gid):
    date = datetime.datetime.now().strftime('%Y%m%d')
    return _crawler.cinema_info(movie_gid, date)


def list_comments(movie_gid):
    return _crawler.movie_comment(movie_gid)
