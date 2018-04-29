#docker build -t filter-backend:1.0 .
FROM openjdk:latest

ADD filter-1.0.tar /app
WORKDIR /app/filter-1.0

EXPOSE 8080

CMD java -jar filter-1.0.jar >execution.log 2>execution.err


