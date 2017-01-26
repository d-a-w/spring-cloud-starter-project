call mvn package
cd auth-server
call mvn docker:build  -DskipTests
cd ..\catalog-service
call mvn docker:build  -DskipTests
cd ..\eureka-server
call mvn docker:build  -DskipTests
cd ..\order-service
call mvn docker:build  -DskipTests
cd ..\turbine
call mvn docker:build  -DskipTests
cd ..\zuul-gateway
call mvn docker:build  -DskipTests

pause