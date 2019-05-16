package main

import (
	_ "MaoyanService/crawler"
	_ "MaoyanService/dao"
	"MaoyanService/web"
	"net/http"
)

func main() {

	http.HandleFunc("/list", web.FilmListHandler)
	http.HandleFunc("/film", web.FilmInfoHandler)
	http.HandleFunc("/comment", web.FilmCommentsHandler)
	http.HandleFunc("/cinema", web.FilmCinemaHandler)
	http.ListenAndServe("127.0.0.1:8000", nil)

}
