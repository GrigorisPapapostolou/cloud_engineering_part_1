## System Architecture 

 * The purpose of this coursework is to create microservices which will simulate the a government page named gov, to which every registered user will be able to download in pdf format a responsible statement or certificate of marital status. Following is a high-level architecture of our system:
 
![image](https://user-images.githubusercontent.com/20031140/230903515-5bca7c93-2808-4c8a-9853-284110d665b8.png)

 The system consists of the following major components - microservices:
* **API Gateway**: An API Gateway acts as a single entry point for a collection of microservices. Any external client cannot access the microservices directly but can access them only through the application gateway. So, this improves the security of the microservices as we limit the access of external calls to all our services. Also concerns like authentication, monitoring/metrics will be needed to be implemented only in the API Gateway as all our calls will be routed through it. So, API Gateway acts as a reverse proxy built before the API. Spring Cloud Gateway is a non blocking API which means a thread is always available to process the incoming request. These request are then processed asynchronously in the background and once completed the response is returned.

 ![image](https://user-images.githubusercontent.com/20031140/230903654-74bbb284-cf13-4c8b-b15e-895f2adef578.png)

Clients make requests to Spring Cloud Gateway. If the Gateway Handler Mapping determines that a request matches a route, it is sent to the Gateway Web Handler. This handler runs the request through a filter chain that is specific to the request. In our app, the api gateway communicates directly with GOV microservice which handles all the incoming requests. 

* **Registry Service**: Netflix Eureka is a lookup server (also called a registry). All micro-services (Eureka clients) in the cluster register themselves to this server.

![image](https://user-images.githubusercontent.com/20031140/230903711-9356ba4c-71d7-4ba0-ace7-e06f130e8063.png)

* **GOV Microservice**: is the major microservice is our application which performs the following operations:
  - registration of a user
  - login of a user
  - download a declaration statement in pdf format
  - download a marital status certificate in pdf format

Note that each user needs to register and then login in the application to download the above documents. The user data is recorded on a postgres database. Also, the GOV microservice calls the STATEMENT and MARITAL microservice to get extra information that is required to download a declaration statement and marital status certificate respectively.  

*	**STATEMENT Microservice**: is a microservice that provides additional information for the declaration statement.

* **MARITAL Microservice**: is a microservice that provides additional information for the marital status certificate.

* **Zipkin**: is a Java-based distributed tracing system to collect and look up data from distributed systems.
 
 ![image](https://user-images.githubusercontent.com/20031140/230903870-0cc2fd96-8805-44a0-b9ba-6baa876d2353.png)

## Set up
  
For ease of the application setup process and avoid possible errors that may occur due to different specifications of the running environment, I created the required images of the microservices of our system and stored the in the docker hub.  So in order to run the application, the user has to go to the root directory of the application and run the command:
  
```
docker compose up -d
```
which it will download the images of the docker compose file and then launch the containers.
