package web

import (
	"MaoyanService/dao"
	"encoding/json"
	"net/http"
	"strconv"
)

func FilmListHandler(w http.ResponseWriter, r *http.Request) {
	data, err := json.Marshal(dao.LoadFilmIntros())
	if err != nil {
		w.WriteHeader(503)
		return
	}
	w.Write(data)
}

func parseId(r *http.Request) int64 {
	idStr := r.Form.Get("id")
	if idStr == "" {
		return 0
	}
	id, err := strconv.ParseInt(idStr, 10, 64)
	if err != nil {
		return 0
	}
	return id
}

func FilmInfoHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	id := parseId(r)
	if id == 0 {
		w.WriteHeader(504)
		return
	}
	film := dao.LoadFilmByGlobalId(id)
	film.Comments = nil
	film.CinemaInfos = nil
	data, _ := json.Marshal(film)
	w.Write(data)
}

func FilmCommentsHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	id := parseId(r)
	if id == 0 {
		w.WriteHeader(504)
		return
	}
	comments := dao.LoadCommentsByGlobalId(id)
	data, _ := json.Marshal(comments)
	w.Write(data)
}

func FilmCinemaHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	id := parseId(r)
	if id == 0 {
		w.WriteHeader(504)
		return
	}
	cinemaInfos := dao.LoadTicketInfoByGlobalId(id)
	data, _ := json.Marshal(cinemaInfos)
	w.Write(data)
}