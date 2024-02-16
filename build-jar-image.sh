mvn clean package

docker buildx build --platform linux/amd64 -t rinha-spring-java .

docker tag rinha-spring-java gabrielmoraes21/rinha-spring-java

docker push gabrielmoraes21/rinha-spring-java:latest