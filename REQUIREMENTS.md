# Flowdesk Requirements and Implementation Plan

Flowdesk is a backend-first issue-tracking system for teams. Its REST API will let a future frontend manage users, projects, and issues while the backend enforces validation, workflow rules, and access permissions.

The phases below are ordered so each can be implemented and exercised before building on it. Complete the tasks within a phase in order; defer optional features until the core workflow is working.

## Phase 0: Project Foundation

- [x] 0.1 Create a Spring Boot project with Java and Maven
- [x] 0.2 Add Spring Web, Spring Data JPA, Validation, PostgreSQL, and Lombok dependencies
- [x] 0.3 Configure application profiles for development and test
- [x] 0.4 Add a `.gitignore` for Java, Maven, IDE, and environment files
- [x] 0.5 Add a basic health endpoint
- [x] 0.6 Create the initial package structure
- [x] 0.7 Write a README with project goals and local setup instructions
## Phase 1: Agree on the API and Data Foundations

- [ ] 1.1 Define controller, service, repository, entity, DTO, mapper, and exception responsibilities
- [ ] 1.2 Keep API request/response models separate from persistence entities
- [ ] 1.3 Define a consistent success and error response format
- [ ] 1.4 Add global exception handling with `@RestControllerAdvice`
- [ ] 1.5 Add request validation with clear validation messages
- [ ] 1.6 Choose and document database migration tooling (Flyway or Liquibase)
- [ ] 1.7 Add the initial database migration for users, projects, project membership, issues, comments, and labels
- [ ] 1.8 Decide whether roles are global or project-specific, and avoid conflicting role sources
- [ ] 1.9 Define shared conventions for timestamps, identifiers, and API versioning

## Phase 2: Register and Authenticate Users

**User flow:** A person registers, logs in, and receives credentials they can use to call protected endpoints.

- [ ] 2.1 Create the user model with name, unique email, password hash, role, and timestamps
- [ ] 2.2 Hash passwords with BCrypt; never store or return plaintext passwords
- [ ] 2.3 Add registration and current-user retrieval endpoints
- [ ] 2.4 Implement login and JWT access-token generation and validation
- [ ] 2.5 Protect private endpoints and define the ADMIN, PROJECT_MANAGER, and MEMBER permissions
- [ ] 2.6 Return safe user DTOs that exclude password hashes and other security data
- [ ] Test registration, login, invalid credentials, token handling, and authorization

## Phase 3: Create Projects and Manage Membership

**User flow:** An authenticated user creates a project, receives a unique project key (for example, `FLOW`), and adds teammates.

- [ ] 3.1 Create the project model with name, unique key, description, owner, and timestamps
- [ ] 3.2 Create project membership with a role or permission model consistent with Phase 1
- [ ] 3.3 Add endpoints to create, list accessible projects, retrieve, update, and delete projects
- [ ] 3.4 Add endpoints to add, list, change, and remove project members
- [ ] 3.5 Enforce project access and project-management permissions on every relevant endpoint
- [ ] 3.6 Decide and enforce whether project owners are automatically members
- [ ] 3.7 Test project CRUD, membership changes, and attempts to access projects as a non-member

## Phase 4: Create and Work on Issues

**User flow:** A project member creates an issue, assigns it to an eligible teammate, and moves it through the team's workflow.

- [ ] 4.1 Create the issue model with project, key, title, description, status, priority, type, reporter, assignee, due date, and timestamps
- [ ] 4.2 Generate unique, readable issue keys scoped to a project (for example, `FLOW-123`), safely under concurrent creation
- [ ] 4.3 Add endpoints to create, retrieve, update, and delete issues within accessible projects
- [ ] 4.4 Define statuses: `TODO`, `IN_PROGRESS`, `IN_REVIEW`, `DONE`, and `CANCELED`
- [ ] 4.5 Define priorities: `LOW`, `MEDIUM`, `HIGH`, and `CRITICAL`
- [ ] 4.6 Define types: `TASK`, `BUG`, `STORY`, and `FEATURE`
- [ ] 4.7 Validate allowed values and status transitions
- [ ] 4.8 Allow assigning and unassigning users; only allow assignees who belong to the issue's project
- [ ] 4.9 Enforce project membership and role permissions for issue actions
- [ ] 4.10 Test issue lifecycle, invalid transitions, invalid project references, and assignment rules

## Phase 5: Collaborate and Find Work

**User flow:** Team members discuss issues, label them, and find work using filters and project summaries.

- [ ] 5.1 Add and remove labels on issues
- [ ] 5.2 Add and retrieve issue comments, recording their authors and creation times
- [ ] 5.3 Add issue activity history for important changes, including actor, action, and time
- [ ] 5.4 Add filtering by status, priority, type, assignee, and label
- [ ] 5.5 Add sorting and pagination to issue lists
- [ ] 5.6 Add a project endpoint summarizing issue counts by status
- [ ] 5.7 Test comments, labels, activity history, filtering, pagination, and summaries

## Phase 6: Make the API Reliable and Ready to Use

- [ ] 6.1 Define service-layer transaction boundaries
- [ ] 6.2 Add optimistic locking for issues where appropriate and handle conflicting updates clearly
- [ ] 6.3 Ensure `updated_at` is refreshed consistently on updates
- [ ] 6.4 Add indexes for common lookups and filters, including project key, issue key, status, assignee, and labels as needed
- [ ] 6.5 Use UTC consistently for date/time values
- [ ] 6.6 Add request correlation IDs and useful application logging
- [ ] 6.7 Add unit, controller, repository, and PostgreSQL-compatible integration tests for core behavior
- [ ] 6.8 Review API responses and authorization rules for accidental data exposure
- [ ] 6.9 Add OpenAPI documentation, JWT authentication details, and example requests/responses
- [ ] 6.10 Add a Postman or Bruno collection
- [ ] 6.11 Complete Docker Compose setup for the API and PostgreSQL, with configuration for secrets supplied through the environment
- [ ] Add safe local development seed data if useful
- [ ] Document architecture, tradeoffs, and local setup in the README

## Phase 7: Build a Minimal Frontend

Start this phase after the API workflow is stable and documented.

- [ ] 7.1 Choose a minimal client approach
- [ ] 7.2 Add login and logout
- [ ] 7.3 Add a project list and project details
- [ ] 7.4 Add an issue list or board with filters
- [ ] 7.5 Add issue creation and editing
- [ ] 7.6 Add issue details with comments, assignment, and status changes
- [ ] 7.7 Use the frontend to exercise the same permissions and workflow exposed by the API

## Optional Features

Select these only after the core workflow is complete, based on project goals.

- [ ] Add email or in-app notifications for assignments and comments
- [ ] Publish domain events and consider an outbox for reliable event delivery
- [ ] Add file attachments using storage outside the database
- [ ] Add full-text issue search
- [ ] Add scheduled reminders for overdue issues
- [ ] Add API rate limiting

## End-to-End Definition of Done

- [ ] The application can be started locally using documented commands
- [ ] A person can register and log in
- [ ] An authorized user can create a project and manage its members
- [ ] A project member can create an issue, assign it to a project member, comment on it, and move it through valid workflow states
- [ ] Users cannot access project data or perform actions they are not authorized for
- [ ] Issue lists can be filtered, sorted, and paginated
- [ ] Core behavior is covered by automated tests and documented through OpenAPI
- [ ] The repository contains no secrets, and the README explains architecture, tradeoffs, and next steps
