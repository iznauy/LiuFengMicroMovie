from flaskr.crawler import Crawler, WordCloudProcessor
import datetime

_crawler = Crawler()

_wd = WordCloudProcessor()


def load_film_intros():
    result = {x.global_id: x for x in _crawler.hot_movies()}
    for m in _crawler.coming_movies():
        if m.global_id not in result:
            result[m.global_id] = m

    return list(result.values())


def get_film(movie_gid):
    return _crawler.movie_detail(movie_gid)


def list_cinema(movie_gid):
    date = datetime.datetime.now().strftime('%Y%m%d')
    return _crawler.cinema_info(movie_gid, date)


def list_comments(movie_gid):
    return _crawler.movie_comment(movie_gid)


def word_cloud(movie_gid):
    return _wd.get_image(movie_gid)
