# Harness Continuous Verification Example Application
Thanks for checking out the Harness Continuous Verification Sample Application. This application
is designed to be run in conjunction with a Prometheus Health Source with Harness. 

## What The Example Application Does

* Generates Metrics to a Prometheus Exporter
* Has a 'stable' and 'unstable' version. Can be triggered via a Docker Tag e.g during a deployment
or on-the-fly via the REST endpoints. 


## Modifying Application
Can modify this application to test out your needs. A few items that are needed. 

* OpenJDK 19 - `brew install openjdk`
* Maven 3 - `brew install maven`

Local run:

```
mvn spring-boot:run
```

Execute:
* `localhost:8082`
* `localhost:8082/actuator`
* `localhost:8082/actuator/prometheus`
* `localhost:8082/run-unstable`
* `localhost:8082/run-stable`

## Docker Build
Can have two tags, for stable and unstable. Can change the entry point [comment/un-comment] which
first metric to use -> https://github.com/harness-apps/cv-example-app/blob/main/src/main/java/io/harness/cv/example/app/CvExampleApp.java

```
docker build --platform linux/amd64 -t rlachhman/cv-example-app:stable .
docker push rlachhman/cv-example-app:stable  

docker build --platform linux/amd64 -t rlachhman/cv-example-app:unstable .
docker push rlachhman/cv-example-app:unstable  
```
