EXCHANGE RATE TEST MICRO SERVICE
==========================================

This is  service for a task. 


Quick launch guide
------------------

* jdk 11. See
* maven.  See https://maven.apache.org/install.html for details.

What's Here
-----------

* pom.xml - this file is the Maven Project Object Model for the web service

Getting Started
-----------
* Install JDK 11 and setup java home
* Install Maven 3.6
* in-memory H2 database is used for local cache database. SO no need to install.

BUILDING UP
-----------
    mvn clean
    mvn install
    java -jar  "jar file name"

ENDPOINTS
----------

* Get sell exchange rate : checks if data for given currency code and date is available or not in the 
cache inmemory H2 database. If there is no data then fetches from nbp api. Then stores that data into db and responds.
-----------
    http://localhost/api/sell-exchange-rate/USD/2023-01-25 [GET]

* Get total cost 
-----------
    localhost:8086/api/calculate/middle/exchange-rate [POST]
    {
        "amount" : 10,
        "date" : "2023-01-25",
        "currencyCode" : ["THB", "USD"]
    }

