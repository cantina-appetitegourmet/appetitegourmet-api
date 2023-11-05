# Appetite Gourmet: Rede de Cantinas Escolares
## Alimentação saudável e saborosa faz a diferença!

# Ferramenta para o backend
* Spring Boot 3.1.0
* Java 17
* Maven 3.9.2
* PostgreSQL 14.7
* Lombok
* JPA / Hibernate
* Bean Validation
* Flyway
* SpringDoc OpenAPI / Swagger UI

# Ambiente
`sudo apt install openjdk-17-jdk`
`sudo update-alternatives --config java`
`nano ~/.bashrc`

    export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
    export PATH=$PATH:$JAVA_HOME/bin

`sudo service postgresql start`

# Instalar dependências
`mvn install`

# Rodar projeto
`mvn spring-boot:run`

# Documentação da API
https://appetitegourmetapi.azurewebsites.net/swagger-ui/index.html
http://localhost:8080/swagger-ui/index.html