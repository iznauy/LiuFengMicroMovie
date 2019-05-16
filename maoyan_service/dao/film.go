package dao

import (
	"MaoyanService/entity"
//	"time"
)

var globalFilms []entity.Film

//func loadFilms(preLoad bool) []entity.Film {
//	t := time.Now()
//	begin := time.Date(t.Year(), t.Month(), t.Day(), 0, 0, 0, 0, t.Location())
//	end := begin.AddDate(0, 0, 1)
//	films := make([]entity.Film, 0)
//	localDB := db
//	if preLoad {
//		localDB = db.Preload("Comments").Preload("CinemaInfos")
//	}
//	localDB.Where("created_at between ? and ?", begin, end).Find(&films)
//	return films
//}

func loadFilms(preLoad bool) []entity.Film {
	return globalFilms
}

func LoadFilms() []entity.Film {
	return loadFilms(true)
}

func LoadFilmByGlobalId(id int64) entity.Film {
	for _, film := range globalFilms {
		if film.OverAllId == id {
			return film
		}
	}
	return entity.Film{}
}

func LoadTicketInfoByGlobalId(id int64) []entity.CinemaInfo {
	film := LoadFilmByGlobalId(id)
	return film.CinemaInfos
}

func LoadCommentsByGlobalId(id int64) []entity.Comment {
	film := LoadFilmByGlobalId(id)
	return film.Comments
}

func LoadFilmIntros() []entity.FilmIntro {
	films := loadFilms(false)
	filmIntros := make([]entity.FilmIntro, 0)
	for _, film := range films {
		filmIntros = append(filmIntros, *film.GetIntro())
	}
	return filmIntros
}

//func SaveFilms(films []entity.Film) {
//	for i := 0; i < len(films); i++ {
//		db.Create(&films[i])
//	}
//}

func SaveFilms(films []entity.Film) {
	globalFilms = films
}