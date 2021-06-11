## Partner Registry app readme

### Install steps
1. Deploy application to docker  

    In order to deploy the application you need to have Docker installed.  
    If you have the prerequisites, run the following command in root folder

    ```bash
    docker-compose up -d
    ```
    
    Docker starts 3 containers:
    * postgres database
    * spring boot backend
    * angular frontend

2. Postgresql data init

    Database url: postgresql://db:5432/partnerregistry  
    user: postgres  
    pw: admin1234  
    
    Run the following script:
    
    ```sql
    INSERT INTO users (username, password, enabled)
      values ('user', '$2a$10$G7u0FbfLwcEWV3/QV5zXKOYTdsyLUzigQwLXEdSh1WPA9vzjOvPNy', 1);
    
    INSERT INTO authorities (username, authority)
      values ('user', 'ROLE_USER');
    ```
         
3. Upload test data

    Test data can be found in ./sample folder  
    First upload partners, then address csv via swagger ui (url is in the next chapter)

    To login use credentials:  
    user: user  
    password: password

### Endpoints
Frontend url: [http://localhost:8081]

All backend functionality can be tested in swagger: [http://localhost:8080/swagger-ui.html]


### Not yet implemented
These are the functionalities that could only be tested in swagger:
* upload file
* login
* edit / create entities

### Backlog
| function                       | frontend | backend |    db   |
|--------------------------------|--------- |---------|---------|
| paging and sorting for lists   |    x     |    x    |         |
| exception handling             |    x     |    x    |         |
| login form                     |    x     |         |         |
| authentication and JWT tokens  |    x     |         |         |
| upload file                    |    x     |         |         |
| edit partner and address       |    x     |         |         |
| init db script                 |          |         |    x    |

and many other things..
