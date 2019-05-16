package entity

type Film struct {
//	gorm.Model
	OverAllId int64
	Name string
	EnName string
	Categories string
	Len	string
	ReleaseTime string
	Score string
	ScoreCount string
	HasScored bool
	TicketOffice string
	PicUrl string
	Description string `gorm:"size:2047"`
	Directors string
	Comments []Comment `gorm:"ForeignKey:FilmID;"`
	CinemaInfos []CinemaInfo `gorm:"ForeignKey:FilmID;"`
}

func (f * Film) GetIntro() * FilmIntro {
	return &FilmIntro{
		OverAllId: f.OverAllId,
		Name: f.Name,
		Categories: f.Categories,
		PicUrl: f.PicUrl,
		Len: f.Len,
		Score: f.Score,
	}
}

type FilmIntro struct {
	OverAllId int64
	Name string
	Categories string
	PicUrl string
	Len	string
	Score string
}


type Comment struct {
//	ID int32 `gorm:"primary_key"`
	FilmID int64
	Content string
	Time string
	UserName string
	Score float64
}

type CinemaInfo struct {
//	ID int32 `gorm:"primary_key"`
	FilmID int64
	Name string
	Position string
	Price string
	Url string
}