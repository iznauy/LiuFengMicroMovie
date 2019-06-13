FROM golang:latest

RUN mkdir /go/src/main
ADD main.go /go/src/main
WORKDIR /go/src/main
RUN go build

EXPOSE 8888
