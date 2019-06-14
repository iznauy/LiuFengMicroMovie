FROM python:3.7

ENV PYTHONPATH /app

RUN pip install requests Flask
COPY . /app
WORKDIR /app/recommendation

EXPOSE 7777
ENTRYPOINT [ "python", "app.py"]
