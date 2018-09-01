# Voting system for Restaurant

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

-----------------------------
P.S.: Make sure everything works with latest version that is on github :)

P.P.S.: Asume that your API will be used by a frontend developer to build frontend on top of that.

### How to use:
* Database properties is in file called **postgres.properties**
* project uses **Java 8** (you can use later versions too)
* You can authorize by followed users:
> User:password  
> Admin:admin

## CURL commands:

### Vote

* Vote for Restaurant with id 100002  
```curl -s -X POST http://localhost:8080/vote/100002 --user User:password```
* Getting Vote  
```curl -s http://localhost:8080/vote/get/100012 --user User:password```
* Getting all Votes  
```curl -s http://localhost:8080/vote/getAll --user Admin:admin```
* Update Vote  
```curl -s -X PUT -d '{"id":100012, "user_id":100001, "restaurant_id":100002, "voted":"2017-03-20"}' -H 'Content-Type: application/json' http://localhost:8080/vote/update --user Admin:admin```

* Delete Vote  
```curl -s -X DELETE http://localhost:8080/vote/delete/100012 --user Admin:admin```
* Get Votes between  
```curl -s "http://localhost:8080/vote/list?startDate=2015-02-02&endDate=2018-03-20" --user Admin:admin```
* Get Votes between dates with restaurant id  
```curl -s "http://localhost:8080/vote/list/100003?startDate=2015-02-02&endDate=2018-03-20" --user Admin:admin```
* Get Votes with exact restaurant id  
```curl -s "http://localhost:8080/vote/list_rest/100003" --user Admin:admin```

### Dish

* Add dish to Restaurant  
```curl -s -X POST -d '{"name":"new Dish", "day":"2018-08-30", "price":300}' -H 'Content-Type: application/json' http://localhost:8080/dish/add/100003 --user Admin:admin```
* Get All Dishes  
```curl -s "http://localhost:8080/dish/getAll" --user Admin:admin ```
* Get Dish  
```curl -s "http://localhost:8080/dish/100008" --user Admin:admin```
* Update Dish  
```curl -s -X PUT -d '{"id":100004, "name":"Updated Dish", "day":"2018-08-30", "price":300}' -H 'Content-Type: application/json' http://localhost:8080/dish/update/100002 --user Admin:admin```
* Delete Dish  
```curl -s -X DELETE http://localhost:8080/dish/100004 --user Admin:admin```
* Get Dishes between dates  
```curl -s "http://localhost:8080/dish/list?startDate=2015-05-31&endDate=2018-08-31" --user Admin:admin```
* Get Dishes with Restaurant id  
```curl -s "http://localhost:8080/dish/list/100003" --user Admin:admin```

### Restaurant

* Create new Restaurant  
```curl -s -X POST -d '{"name":"new Russian Restaurant"}' -H 'Content-Type: application/json' http://localhost:8080/rest/add --user Admin:admin```
* Update Restaurant  
```curl -s -X PUT -d '{"id":100003, "name":"Updated Restaurant"}' -H 'Content-Type: application/json' http://localhost:8080/rest/update --user Admin:admin```
* Delete Restaurant  
```curl -s -X DELETE http://localhost:8080/rest/100002 --user Admin:admin```
* Get Restaurant  
```curl -s "http://localhost:8080/rest/100003" --user User:password```
* Get All Restaurants  
```curl -s "http://localhost:8080/rest/getAll" --user User:password```

### User

* Get all users  
```curl -s "http://localhost:8080/admin/getAll" --user Admin:admin```
* Get user  
```curl -s "http://localhost:8080/admin/100000" --user Admin:admin```
* Create new User  
```curl -s -X POST -d '{"login":"new User", "password":"12345", "roles":["ROLE_USER"]}' -H 'Content-Type: application/json' http://localhost:8080/admin/create --user Admin:admin```
* Update User  
```curl -s -X PUT -d '{"id":100000, "login":"updated User", "password":"12345", "roles":["ROLE_USER"]}' -H 'Content-Type: application/json' http://localhost:8080/admin/update --user Admin:admin```
* Delete User  
```curl -s -X DELETE http://localhost:8080/admin/100000 --user Admin:admin```
