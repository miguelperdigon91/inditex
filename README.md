
 
``` sh
$ git clone git@github.com:miguelperdigon91/farmatodo-test.git
```
 
 
#  Overview
Technical test applied by Miguel Perdigón
 
# Requirements
 
* java 8
* Spring Framework & Spring Boot 2
* mongodb
* Maven

# Build
* Install dependencies: `$ mvn clean install`
* Run test: `mvn clean test`

* For IntelliJ:
    1. Go ton Run -> Edit Configurations
    2. Click (+) Add configuration -> Application
    3. Set a Name
    4. Set Main_class: com.farmatodo.test.Application
    5. Set environment variables
    6. Save changes and Run
 
# Enviroments
#### required enviroment variables
 
* `MONGO_DB_URL_CONNECTION`: mongodb connection string
* `MONGO_DB_NAME`: mongodb database name

# Swagger
/api/farmatodo-test/internal-ms/swagger-ui/index.html#
 