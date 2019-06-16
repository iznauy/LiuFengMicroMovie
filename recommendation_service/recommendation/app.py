from flask import Flask
from flask import request, jsonify
from json import JSONEncoder
from json import loads as json_loads
from movie import Movie
from data_helper import recommend_three_movies


app = Flask(__name__)


class MyEncoder(JSONEncoder):
    def default(self, o):
        return o.__dict__


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/recommend', methods=['POST'])
def recommend():
    json_data = json_loads(request.data)
    movie = Movie(json_data)
    return jsonify(recommend_three_movies(movie))


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=7777)


