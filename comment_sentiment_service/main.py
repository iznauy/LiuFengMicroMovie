import json

from flask import Flask, request

from sentiment import sentiment

app = Flask(__name__)
default_name = 'Comment Sentiment Service'

@app.route('/', methods=['POST'])
def get_comment_sentiments():
    comments = json.loads(request.form['comments'])
    tags = []
    for comment in comments:
        tag = sentiment(comment)
        tags.append(tag)
    return json.dumps(tags)


if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)
