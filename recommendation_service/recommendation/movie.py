from functools import reduce
from typing import List
from math import sqrt


class Movie:

    def __init__(self, record):
        self.record = record

    def get_recommend_rank(self, movie):
        R = movie.record['score']
        v = movie.record['scoreCount']
        m = 25000
        C = 7.0
        weighted_rank = v / (v + m) * R + m / (v + m) * C
        return weighted_rank * self.get_similarity(movie)

    def get_similarity(self, movie):
        return self.get_name_similarity(movie) + 4 * self.get_cos_similarity(movie)

    def get_name_similarity(self, movie):
        distance = self.edit_distance(self.record['name'], movie.record['name'])
        return 1 - distance / max(len(self.record['name']), len(movie.record['name']))

    def get_cos_similarity(self, movie):
        vector1 = []
        vector2 = []
        v1, v2 = self.get_vector(self.record['directors'], movie.record['directors'])
        vector1 += v1
        vector2 += v2
        v1, v2 = self.get_vector(self.record['categories'], movie.record['categories'])
        vector1 += v1
        vector2 += v2

        numerator = 0
        for i in range(len(vector1)):
            numerator += vector1[i] * vector2[i]
        return numerator / sqrt(reduce(lambda x, y: x + y ** 2, vector1) * reduce(lambda x, y: x + y ** 2, vector2))

    def get_vector(self, list1: List[str], list2: List[str]):
        name_set = list(set(list1 + list2))
        v1 = []
        v2 = []
        for element in name_set:
            v1.append(1 if element in list1 else 0)
            v2.append(1 if element in list2 else 0)
        return v1, v2

    def edit_distance(self, word1, word2):
        """
        :categorie word1: str
        :categorie word2: str
        :rcategorie: int
        """
        d = {(0, 0): 0}
        for a in range(len(word1)):
            d[a + 1, 0] = a + 1
        for b in range(len(word2)):
            d[0, b + 1] = b + 1

        for i in range(len(word1)):
            for j in range(len(word2)):
                temp = 0 if word1[i] == word2[j] else 1
                m1 = d[i, j + 1] + 1
                m2 = d[i + 1, j] + 1
                m3 = d[i, j] + temp
                d[i + 1, j + 1] = min(m1, m2, m3)

        return d[len(word1), len(word2)]

