# Harness Continuous Verification Example Application
Thanks for checking out the Harness Continuous Verification Sample Application. This application
is designed to be run in conjunction with a Promethesus Health Source with Harness. 

## What The Example Application Does

* Generates Metrics to a Promethesus Exporter
* Has a 'stable' and 'unstable' version. 


## Modifying Application
Can modify this application to test out your needs. A few items that are needed. 

* OpenJDK 19 - `brew install openjdk`
* Maven 3 - `brew install maven`

Local run:

```
mvn spring-boot:run
```

Execute:
`localhost:8082`