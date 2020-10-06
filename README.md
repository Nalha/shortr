# ShortR

An url shortener app made by Jesper Håsteen as a demo project.

## Build

Maven is used to build this project, you don't have to have maven installed since it's included by spring boot.

#### Simple build
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

### Run the application
```shell script
$ ./mvnw spring-boot:run
``` 
This starts the application itself on port 8080 and an embedded mongodb on port 12345. 
The same mongodb port is also used by integration test so do not run the application and tests at same time.
Replace URL/port in `src/main/resources/application.yml` if you wish to use another db server.
