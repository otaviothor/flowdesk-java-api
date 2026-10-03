# Flowdesk

Flowdesk is a backend-first issue-tracking system for teams. The planned REST API will let users manage projects and project members, create and work on issues, and collaborate through comments and labels. A future frontend can use the API for project views, issue lists or boards, and issue details; the backend will enforce validation, workflow rules, and access permissions.

The application is currently at the foundation stage. It has a Spring Boot application, PostgreSQL configuration, a health endpoint, and an initial database schema. User authentication and the project and issue APIs are planned, but are not implemented yet.

## Technology

- Java 21
- Spring Boot and Maven
- Spring Web MVC, Spring Data JPA, and Jakarta Validation
- PostgreSQL
- Springdoc OpenAPI
- Docker Compose for the PostgreSQL development service

## Run locally

Prerequisites: Java 21, Docker with the Compose plugin, and a running Docker daemon.

1. Start the PostgreSQL service:

   ```bash
   docker compose up -d db
   ```

2. Start the API from the repository root:

   ```bash
   ./mvnw spring-boot:run
   ```

The API uses the default local database settings from `application.yaml` (`flowdesk` on `localhost:5432`, user `postgres`). The Compose database service creates that database and runs the SQL scripts in `db/init` when its data volume is first initialized.

Check application health at <http://localhost:8080/actuator/health>. OpenAPI UI is available at <http://localhost:8080/swagger-ui/index.html>; as API endpoints are added, they will appear there.

To stop the database service:

```bash
docker compose down
```

The named PostgreSQL volume is preserved by this command. The Compose file currently references a `Dockerfile` that is not present yet, so the full application-plus-database Compose startup is not available. Run the API with Maven as shown above while using Compose for PostgreSQL.

## Configuration

The application reads database settings from `POSTGRES_URL`, `POSTGRES_USER`, and `POSTGRES_PASSWORD`. For example, to use a non-default local database:

```bash
POSTGRES_URL=jdbc:postgresql://localhost:5432/flowdesk \
POSTGRES_USER=postgres \
POSTGRES_PASSWORD=your-local-password \
./mvnw spring-boot:run
```

The credentials in the Compose file are for local development only; do not use them in a deployed environment. The current default JPA configuration uses Hibernate schema updates. Database migration tooling is planned but has not been selected or added yet.

## Project plan

See [REQUIREMENTS.md](REQUIREMENTS.md) for the phased implementation plan, planned user workflows, and definition of done.
