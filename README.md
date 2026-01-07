# Spring Boot Workspace

This workspace contains a minimal Spring Boot project (Maven).

Build and run using Maven (from the workspace root):

```bash
mvn clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

Run directly with the Spring Boot plugin:

```bash
mvn spring-boot:run
```

After starting, open http://localhost:8080/ to see the sample endpoint.

Notes:
- The Spring Boot sources are under `src/main/java/com/example/demo`.
- A simple controller responds with "Hello, Spring!" at `/`.

Legacy: `src/Main.java` (a plain Java example) remains in the workspace.
