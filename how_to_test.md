# How to Test

## SWAGGER
check APIs documentation with [swagger](http://localhost:8080/swagger-ui/index.html?url=/v2/api-docs), where you will be able to run tests for all available APIs.
1. call login API with username and password.
2. copy the value of login response header "token".
3. scroll to top and click on "Authorization" button.
4. type in "Bearer " then paste the token.
5. Submit and test other APIs.

## UI
1. go to [login page](http://localhost:8080/login) and login with any username and password (if username isn't exist, a new user will be created with the selected username)
2. you will be redirected to a page that contains a table with only the movies that won the **Best Picture** award.
3. you will be able to search a movie by title.
4. you will be able to rate a movie in the list (rates are saved in the DB with the user you logged in with)

