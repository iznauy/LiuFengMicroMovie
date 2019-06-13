FROM python:2.7

RUN pip install Flask  snownlp

WORKDIR /app
COPY main.py /app/
COPY sentiment.py /app/

CMD ["python", "main.py"]
