package main

import (
	"fmt"
	"net/http"
	"strconv"
	"sync"
)

var nextId int64 = 1
var idMap = make(map[string]int64)
var m sync.Mutex

func getId(name string) int64 {
	m.Lock()
	defer m.Unlock()
	if idMap[name] == 0 {
		idMap[name] = nextId
		fmt.Println("assign", name, "with id", nextId)
		nextId++
	}
	return idMap[name]
}

func IdRequestHandler(w http.ResponseWriter, r *http.Request) {
	r.ParseForm()
	name := r.Form.Get("name")
	if name == "" {
		w.WriteHeader(504)
		return
	}
	w.Write([]byte(strconv.FormatInt(getId(name), 10)))
}


func main() {
	http.HandleFunc("/", IdRequestHandler)
	http.ListenAndServe("127.0.0.1:8888", nil)
}
