#TODO

##Unit Testing
Add more unit test to reach at least 80% code coverage.

##Deployment
deploy the project online.

###Kubernetes
use Kubernetes to package the project as containers, taking into consideration the configuration settings exist for each environment
###Environments
create multiple environments: dev, staging and production.

##Data Integration
Get all movies from OMDB instead of only those who won the "Best Picture" awards,
then create a subscription event has purpose to synchronize data between our project and the third party.
(we were restricted by only 1000 API call as we are using free subscription.)

##Logging
Save project Logs into files named by pattern using log4j2 dependency.
