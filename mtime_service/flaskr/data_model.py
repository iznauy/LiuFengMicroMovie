from dataclasses import dataclass
from typing import List


@dataclass
class Comment:
    movie_gid: int
    content: str
    time: str
    username: str
    score: float


@dataclass
class Film:
    global_id: int
    name: str
    en_name: str
    categories: List[str]
    length: str
    release_date: str
    score: float
    score_count: int
    has_scored: bool
    ticket_office: str
    img: str
    description: str
    directors: str


@dataclass
class FilmIntro:
    global_id: int
    name: str
    categories: List[str]
    img: str
    length: str
    score: str


@dataclass
class CinemaInfo:
    movie_gid: int
    name: str
    position: str
    price: float
    url: str
