# Rainfall App

Singapore rainfall statistics app is a simple webservice built with Spring Boot.
This app displays the amount of rainfall for a station that is passed as the input along with the time in SGT.

Sample output : **Marina Gardens Drive, 09:40, 0.0mm, Not Raining**

Use **build.sh** script to build the application and once the application is running successfully, 
then it can be accessed with **http://localhost:8080**
Unit test case execution result will be logged in the build output printed on console. 

This app can also be dockerized and deployed in Kubernetes using minikube or docker desktop setup in local machine.

Run **dockerBuild.sh** to create the docker image with the latest jar of the application.

Kubernetes scripts for Deployment, Configmap and Service components can be found under K8 folder.

The **API Url** and the **Station name** parameters are configured in Configmap and passed as Environment variables in Deployment.
Station name parameter value can be updated in the config map to view the results for other stations.

Application deployed on K8 can be accessed with **http://localhost:*nodeport***
