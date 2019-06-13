# coding=utf-8
from snownlp import SnowNLP

positive_tag = "好评"
neutral_tag = "中评"
negative_tag = "差评"


def sentiment(comment):
    if len(comment) == 0:
        return neutral_tag

    s = SnowNLP(comment)
    avg = 0.0
    min_val = 1.0
    max_val = 0.0
    for sentence in s.sentences:
        local_sentiment =  SnowNLP(sentence).sentiments
        avg += local_sentiment
        min_val = min(min_val, local_sentiment)
        max_val = max(max_val, local_sentiment)

    if min_val > 0.5 and avg > 0.8:
        return positive_tag
    elif min_val > 0.4 and avg >= 0.5:
        return neutral_tag
    else:
        return negative_tag