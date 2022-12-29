# Harness Continuous Verification Example Application
Thanks for checking out the Harness Continuous Verification Sample Application. This application
is designed to be run in conjunction with a Prometheus Health Source with Harness. 

## What The Example Application Does

* Generates Metrics to default Micrometer [Spring Boot] Prometheus Registry.
* Has a 'stable' and 'unstable' version. Can be triggered via a Docker Tag e.g during a deployment
or on-the-fly via the REST endpoints. 


## Modifying Application
Can modify this application to test out your needs. A few items that are needed. 

* OpenJDK 19 - `brew install openjdk`
* Maven 3 - `brew install maven`

### Running Locally

```
mvn spring-boot:run
```

Can toggle explore the endpoints:

* `localhost:8082`
* `localhost:8082/actuator`
* `localhost:8082/actuator/prometheus`
* `localhost:8082/run-unstable`
* `localhost:8082/run-stable`

## Docker Build
Can have two tags, for stable and unstable. Can change the entry point [comment/un-comment] which
first metric to use -> `main/src/main/java/io/harness/cv/example/app/CvExampleApp.java` and re-build and re-push
to a Docker Registry of your choice. 

* Docker Install - `brew install docker`

```
docker build --platform linux/amd64 -t rlachhman/cv-example-app:stable .
docker push rlachhman/cv-example-app:stable  

docker build --platform linux/amd64 -t rlachhman/cv-example-app:unstable .
docker push rlachhman/cv-example-app:unstable  
```

## Deploying to Harness
Can leverage the `harness-cv-example-deployment.yaml` and `values.yaml` to deploy in Harness to pick the specific `stable` or `unstable` tag. 

## Installing Prometheus 
Installing Prometheus from Helm is easy.

```
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts

helm repo update

helm upgrade --install prometheus prometheus-community/prometheus \
--namespace prometheus --create-namespace
```

For an example, you can also expose over NodePort.

```
kubectl expose deployment -n prometheus prometheus-server --type=NodePort --name=prometheus-service
```

Then browse to node_public_ip:nodeport.

## Metrics
There are two metrics that can be modified. 

* CV_Counter_Example_total
* CV_Gauge_Example{}

In the `stable` version these are flat. In the `un-stable` versions these metrics increment up every 15 seconds. Can modify 
`src/main/java/io/harness/cv/example/app/GenerateUnstableMetrics.java` and change the amount, time, or add another Actuator Metric
which will be wired to Prometheus for you. 
