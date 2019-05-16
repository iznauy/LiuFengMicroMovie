package dao

//import (
//	"github.com/jinzhu/gorm"
//	_ "github.com/jinzhu/gorm/dialects/mysql"
//)
//
//var db *gorm.DB
//
//const (
//	userName = "root"
//	password = "iznauy.top"
//	dbName = "mao_yan"
//	host = "localhost"
//	port = 3306
//)

func init() {
	//localDb, err := gorm.Open("mysql", fmt.Sprintf("%s:%s@(%s:%d)/%s?charset=utf8&parseTime=True&loc=Local",
	//	userName, password, host, port, dbName))
	//if err != nil {
	//	panic(err)
	//}
	//db = localDb
	//db.DropTableIfExists(&entity.Film{}, &entity.CinemaInfo{}, &entity.Comment{})
	//db.Set("gorm:table_options", "ENGINE=InnoDB DEFAULT CHARSET=utf8").CreateTable(&entity.Film{}, &entity.CinemaInfo{}, &entity.Comment{})

}

//func test() {
//
//	comments := make([]entity.Comment, 0)
//	comments = append(comments, entity.Comment{ Content: "666", Time: "2333", UserName: "66", Score: "66" },
//		entity.Comment{ Content: "6668", Time: "2333", UserName: "66", Score: "66" })
//	cinemaInfos := make([]entity.CinemaInfo, 0)
//	cinemaInfos = append(cinemaInfos, entity.CinemaInfo{ Name: "name1", Position: "pos1", Price: "price1", Url: "1"})
//	film := entity.Film{
//		OverAllId: 2,
//		Comments: comments,
//		CinemaInfos: cinemaInfos,
//	}
//	db.Create(&film)
//	fmt.Println(film)
//	var films []entity.Film
//	db.Preload("Comments").Preload("CinemaInfos").Find(&films)
//	fmt.Println(films)
//	t := time.Now()
//	tm1 := time.Date(t.Year(), t.Month(), t.Day(), 0, 0, 0, 0, t.Location())
//	tm2 := tm1.AddDate(0, 0, 1)
//	db.Where("created_at between ? and ?", tm1, tm2).Find(&films)
//	fmt.Println(films)
//
//}
