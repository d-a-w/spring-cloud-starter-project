# spring-cloud-starter-project
Microservice Architecture with Spring Cloud

This project aims to create a basic spring cloud starter project, based on Netflix stack
 
### This project covers the following topics:


- Eureka
- OAuth2
- Hystrix
- Turbine
- Zuul

### Microservices:

- auth-server: OAuth2 server 
- catalog-service: Microservice without authentication
- eureka-server: Eureka discovery service
- order-service: Microservice with OAuth2 authentication
- turbine: Turbine stream aggregator with Hystrix dashboard
- zuul-gateway: Zuul proxy 

### Test URLs

 - Catalog service with exception: ```curl http://localhost:8091/catalog/1```
 - Catalog service without exception (Hystrix circuit breaker): ```curl http://localhost:8091/catalog/11```
 - Hystrix stream of catalog: ```curl http://localhost:8091/hystrix.stream ```
 - Turbine stream of catalog and order:  ```http://localhost:8082/turbine.stream```
 - Turbine dashboard of catalog and order: ```http://localhost:8082/hystrix/monitor?stream=http://localhost:8082/turbine.stream```
 - Get token from auth-server as writer :  ```curl -XPOST "client_id:@localhost:9999/oauth/token" -d "grant_type=password&username=writer&password=writer"```
 - Get token from auth-server as reader :  ```curl -XPOST "client_id:@localhost:9999/oauth/token" -d "grant_type=password&username=reader&password=reader"``` 
 - Get order from order-service:<br />
  ``` set read_token = "put here access_token"     curl -H "Authorization: Bearer %read_token%" "localhost:8083/order/1"   ```
 - Create order:<br />
   ``` set write_token = "put here access_token" curl -i -H "Authorization: bearer %write_token%"  -H "Content-Type: application/json" -X POST  http://localhost:8083/order -d "{\"id\": \"1\", \"name\": \"order\"}" ```
 - Zuul proxy: ```curl http://localhost:8080/catalog/catalog/11```
 
 
 ### Generate public certificate 
 
 ```keytool -genkeypair -alias jwt -keyalg RSA -dname "CN=jwt, L=Verona, S=Verona, C=IT" -keypass mySecretKey -keystore jwt.jks -storepass mySecretKey``` <br />
 ```keytool -export -keystore jwt.jks -alias jwt -file example.cer ``` then prompt mySecretKey  <br />
``` openssl x509 -inform der -in example.cer -pubkey -noout``` and copy the public key in public.cert
 