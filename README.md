# ShortR

An url shortener app made by Jesper Håsteen as a demo project.

### Quick start
Requires Java 11+.
```shell script
$ ./mvnw spring-boot:run
``` 
Visit http://localhost:8080 for app.

#### Run tests 
```shell script
$ ./mvnw verify
```

#### Build docker image
Requires docker.
```shell script
$ ./mvnw verify -P docker
``` 
Creates a local docker image simply called `shortr` with no tags, run with:
```shell script
$ docker run -p 8080:8080 shortr
``` 

#### Run OWASP dependency check
```shell script
$ ./mvnw verify -P dependencyCheck
``` 
This will download CVE databases and check for known vulnerabilities in included dependencies.

### Configure mongoDB
To run multiple instances of the application you need to configure it to use another mongoDB, 
simply replace `spring.data.mongodb.host` and/or `spring.data.mongodb.port` in `src/main/resources/application.yml` 
and add any required username and password. 
