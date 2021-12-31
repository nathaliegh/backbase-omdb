# How to Run

JDK, MYSQL and MAVEN should be installed to be able to run the project, preferable to use these versions:

```text
JDK 11
MYSQL 8
MAVEN 3.8.4
```

## Database Connection

create MYSQL schema, and change these connection settings from application.properties accordingly:

```text
spring.datasource.url
spring.datasource.username
spring.datasource.password
```

## Maven Clean

execute command to clean dependencies

```shell
mvn clean
```

## LIQUIBASE

change these db connection settings from liquibase.properties to the values used in Database Connection

```text
url
username
password
```

then execute command to create db

```shell
mvn liquibase:update -e
```

## Maven Install

execute command to repackage dependencies

```shell
mvn install
```

## Spring Boot

execute command to run the project

```shell
mvn spring-boot:run
```

