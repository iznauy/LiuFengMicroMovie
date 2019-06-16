import json
from movie import Movie


def map_method(item):
    record = item[0].record
    data = {}
    data['name'] = record['name']
    data['pic'] = record['image_url']
    data['url'] = 'https://movie.douban.com/subject/' + record['id'] + '/'
    return data


def recommend_three_movies(movie):
    three_recommended = []
    json_file = open('data.json', 'r', encoding='utf-8')

    for line in json_file:
        data = json.loads(line)
        record = Movie(data)
        rank = movie.get_recommend_rank(record)
        three_recommended.append((record, rank))
        three_recommended = sorted(three_recommended, key=lambda x: x[1], reverse=True)[:3]

    return list(map(map_method, three_recommended))


