## Reply Service - Beta

### Prerequisites

The project can be run using docker or standalone java installation.(This project has been developed using openjdk-11)

#### Run with docker
```shell
docker-compose build
docker-compose up -d
```

#### Or run with Java local installation.
```shell
./gradlew build
java -jar build/libs/rest-service-0.0.1-SNAPSHOT.jar
```

After running please access the APIs via host `http://localhost:8080`.

### APIs

1. **GET** `${HOST:PORT}/api/v2/rules` - List down available rules for text transformation.
2. **GET** `${HOST:PORT}/api/v2/reply/<stringInput>` - Transform message, using regex format `\d{2}-[a-z0-9]*`.
3. **GET** `${HOST:PORT}/api/v1/reply/<stringInput>` - Legacy transform message API.

### Adding a new transformation rule.

1. Add a rule record in `rule` table in database first. Please find table attributes below.
```sql
TABLE rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    index INT UNIQUE NOT NULL,
    operation VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);
```
Example:
```sql
INSERT INTO rule (id, index, operation, description) VALUES (1, 1, 'REVERSE', 'Reverse the given string.')
```

2. Implement the operation method in `com/beta/replyservice.rule.operation.Operations` class.
3. Update the switching condition in `applyOperation` method of, `com/beta/replyservice.rule.operation.RuleFacadeImpl`.

### Configurations

Related configurations can be found in `resources/application.properties`.
- Application is currently using h2 in memory database and that can be changed as the per requirement.
- Each time app will restart database will be reset. That behaviour also can be changed.
- H2 console is available at `${HOST:PORT}/h2-console/`


### License

MIT, 2024, Lahiru Pathirage.