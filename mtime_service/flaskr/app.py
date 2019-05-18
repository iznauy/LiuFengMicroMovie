from flask import Flask
import flaskr.dao as dao
from flask import request, jsonify
from json import JSONEncoder
import dataclasses

app = Flask(__name__)


class MyEncoder(JSONEncoder):
    def default(self, o):
        return o.__dict__


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/listFilms', methods=['GET'])
def list_films():
    # return jsonify(dao.load_film_intros())
    intros = dao.load_film_intros()
    return jsonify([dataclasses.asdict(o) for o in intros])


@app.route('/detail', methods=['GET'])
def movie_detail():
    movie_gid = request.args.get('id')
    return jsonify(dataclasses.asdict(dao.get_film(movie_gid)))


@app.route('/comments', methods=['GET'])
def movie_comments():
    movie_gid = request.args.get('id')
    return jsonify([dataclasses.asdict(o) for o in dao.list_comments(movie_gid)])


@app.route('/cinemas', methods=['GET'])
def movie_cinemas():
    movie_gid = request.args.get('id')
    return jsonify([dataclasses.asdict(o) for o in dao.list_cinema(movie_gid)])


if __name__ == '__main__':
    app.run()
