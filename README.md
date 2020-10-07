# ShortR

An url shortener app made by Jesper Håsteen as a demo project.

### Build & run the application
```shell script
$ ./mvnw spring-boot:run
``` 
This builds and starts the application on port 8080 and an embedded mongoDB on port 12345. 
Replace URL/port in `src/main/resources/application.yml` and change scope of the `de.flapdoodle.embed.mongo` dependency 
in `pom.xml` if you wish to use another mongoDB server.

#### Build and test
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

