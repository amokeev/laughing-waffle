## Sample Filter backend code

###BUILD

Use one of the below commands, for your preference.

* *./gradlew distZip* #The distribution will be created at *build/distributions/filter-1.0.zip*
* *./gradlew docker* #The docker image *filter-backend:1.0* will be created

###RUN

#### From filter-1.0.zip
1. unpack *filter-1.0.zip*
2. start from command line by
*cd filter-1.0/ && java -jar filter-1.0.jar*

#### From docker image
Docker image with the backend can be started by one of the following ways:
* ./gradlew dockerRun

or
* docker run -p 8080:8080 -d filter-backend:1.0

This expose REST API on the localhost, port 8080. The only endpoint is GET /people?<...filter parameters...>. 
To verify that server is up: 
* From command line: *curl http://localhost:8080/people/?contact=false* 
* From browser: <http://localhost:8080/people/?contact=false>

###SOURCES

The project sources are split, check them out for details. 

* [src/main](src/main)
* [src/test](src/test)
* [src/integTest](src/integTest)

###DEVELOPMENT

* ./gradlew test
* ./gradlew integTest

