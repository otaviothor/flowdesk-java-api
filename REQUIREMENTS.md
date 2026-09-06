# Flowdesk API Requirements

Backend-first issue tracking system for teams to manage projects and work items.

## 1. Project Setup

- [x] Create a Spring Boot project with Java and Maven
- [x] Add Spring Web, Spring Data JPA, Validation, PostgreSQL, and Lombok dependencies
- [x] Configure application profiles for development and test
- [X] Add a `.gitignore` for Java, Maven, IDE, and environment files
- [ ] Add a basic health endpoint
- [ ] Create the initial package structure
- [ ] Write a README with project goals and local setup instructions

## 2. Initial Architecture

- [ ] Define controller, service, repository, entity, DTO, mapper, and exception packages
- [ ] Keep API models separate from persistence entities
- [ ] Define a consistent success and error response format
- [ ] Add global exception handling with `@RestControllerAdvice`
- [ ] Add request validation with clear validation messages
- [ ] Use database migrations with Flyway or Liquibase
- [ ] Document the decision to use the chosen architecture

## 3. Core Domain

### Users

- [ ] Create the user model
- [ ] Store name, email, password hash, role, and timestamps
- [ ] Enforce unique email addresses
- [ ] Add user creation and retrieval endpoints

### Projects

- [ ] Create the project model
- [ ] Store name, key, description, owner, and timestamps
- [ ] Enforce unique project keys
- [ ] Add project creation, retrieval, update, and deletion endpoints
- [ ] Associate users with projects

### Issues

- [ ] Create the issue model
- [ ] Store title, description, status, priority, type, reporter, assignee, project, and timestamps
- [ ] Generate a readable issue key, such as `FLOW-123`
- [ ] Add issue creation, retrieval, update, and deletion endpoints
- [ ] Prevent issues from being created outside an existing project
- [ ] Validate allowed status transitions

## 4. Issue Management

- [ ] Add statuses: TODO, IN_PROGRESS, IN_REVIEW, DONE, and CANCELED
- [ ] Add priorities: LOW, MEDIUM, HIGH, and CRITICAL
- [ ] Add types: TASK, BUG, STORY, and FEATURE
- [ ] Add labels to issues
- [ ] Add comments to issues
- [ ] Add an issue activity or audit history
- [ ] Support assigning and unassigning users
- [ ] Support due dates
- [ ] Add filtering by status, priority, type, assignee, and label
- [ ] Add sorting and pagination
- [ ] Add a project endpoint that summarizes issue counts by status

## 5. Authentication and Authorization

- [ ] Add password hashing with BCrypt
- [ ] Implement registration
- [ ] Implement login
- [ ] Generate and validate JWT access tokens
- [ ] Protect private endpoints
- [ ] Add roles: ADMIN, PROJECT_MANAGER, and MEMBER
- [ ] Restrict project and issue actions according to role
- [ ] Prevent users from accessing projects they do not belong to
- [ ] Avoid returning passwords or sensitive security data in responses

## 6. Testing

- [ ] Add unit tests for services and domain rules
- [ ] Add controller tests for successful and invalid requests
- [ ] Add repository tests for important queries
- [ ] Add integration tests with a real PostgreSQL-compatible test database
- [ ] Test authentication and authorization rules
- [ ] Test invalid issue status transitions
- [ ] Test pagination and filtering
- [ ] Test global error responses
- [ ] Keep the main branch passing all tests

## 7. API Documentation and Developer Experience

- [ ] Add OpenAPI documentation with Springdoc
- [ ] Document authentication in Swagger UI
- [ ] Add example requests and responses
- [ ] Add a Postman or Bruno collection
- [ ] Add Docker Compose for the application and PostgreSQL
- [ ] Add environment-based configuration for secrets
- [ ] Add useful application logging
- [ ] Add database seed data for local development

## 8. Reliability and Quality

- [ ] Add database indexes for issue key, project key, status, and assignee
- [ ] Define transaction boundaries in the service layer
- [ ] Handle concurrent updates to issues
- [ ] Add optimistic locking where appropriate
- [ ] Add consistent date and time handling in UTC
- [ ] Add request correlation IDs to logs
- [ ] Review API responses for accidental data exposure
- [ ] Run formatting and static analysis checks

## 9. Optional Backend Features

- [ ] Add email or in-app notifications for assignments and comments
- [ ] Publish domain events for important issue changes
- [ ] Add an outbox pattern for reliable event publishing
- [ ] Add file attachments with storage outside the database
- [ ] Add full-text issue search
- [ ] Add scheduled reminders for overdue issues
- [ ] Add API rate limiting

## 10. Minimal Frontend

- [ ] Choose a minimal client approach after the API is stable
- [ ] Add login and logout
- [ ] Add project list and project details
- [ ] Add issue list with filters
- [ ] Add issue creation and editing
- [ ] Add issue detail with comments and status changes
- [ ] Keep the frontend limited to validating the backend workflow

## 11. Delivery Milestones

- [ ] Milestone 1: Application starts and health endpoint works
- [ ] Milestone 2: Projects and issues can be managed through the API
- [ ] Milestone 3: Users can authenticate and access is restricted
- [ ] Milestone 4: API is tested, documented, and containerized
- [ ] Milestone 5: Minimal frontend demonstrates the main workflow
- [ ] Milestone 6: Optional advanced features are selected based on learning goals

## Definition of Done

- [ ] The application can be started locally with documented commands
- [ ] A user can log in, access a project, create an issue, assign it, comment on it, and move it through its workflow
- [ ] Unauthorized users cannot access protected project data
- [ ] The API has automated tests for its core behavior
- [ ] The API is documented through OpenAPI
- [ ] The repository contains no secrets
- [ ] The README explains the architecture, tradeoffs, and next steps
