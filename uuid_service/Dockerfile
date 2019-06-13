FROM golang:latest

ADD . /go/src/
WORKDIR /go/src/MaoyanService/main
RUN go build

EXPOSE 8000

ENTRYPOINT ["./main"]