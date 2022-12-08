### second microservice

### How To run with IDEA
 
#### CLI
```shell
 ./mvnw install spring-boot:repackage -DskipTests &&
 docker-compose up --build --no-deps --force-recreate  -Vd  --no-log-prefix secondmicroservice 
```
#### IDEA

Add New Run Configuration:
- `install spring-boot:repackage  -DskipTests`
![docker_compose_with_idea](/README_IMAGE/docker_compose_with_idea.png)
