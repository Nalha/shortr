# ShortR

An url shortener app made by Jesper Håsteen as a demo project.

### Build & Run the application
```shell script
$ ./mvnw spring-boot:run
``` 
Requires port 8080 to be free, uses embedded mongodb to run standalone without any additional configuration.

#### Run tests
This will build and run all unit and integration tests. 
```shell script
$ ./mvnw clean verify
```

#### Build docker image
```shell script
$ ./mvnw clean verify -P docker
``` 
This will create a local docker image simply called "shortr" with no tags.

#### Run OWASP dependency check
```shell script
$ ./mvnw clean verify -P dependencyCheck
``` 
This will download CVE databases and check for known vulnerabilities in included dependencies.

### Configure mongoDB
To run multiple instances of the application you need to configure it to use another mongoDB, 
simply replace `spring.data.mongodb.host` and/or `spring.data.mongodb.port` in `src/main/resources/application.yml` 
and add any required username and password. 
