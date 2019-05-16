package crawler

import "github.com/robfig/cron"

var cronTab = cron.New()

func init() {

	err := cronTab.AddFunc("0 10 0 * * *", craw)
	if err != nil {
		panic(err)
	}
	go craw()
	cronTab.Start()

}
