# Flowdesk Requirements and Implementation Plan

Flowdesk is a backend-first issue-tracking system for teams. Its REST API will let a future frontend manage users, projects, and issues while the backend enforces validation, workflow rules, and access permissions.

The phases below are ordered so each can be implemented and exercised before building on it. Complete the tasks within a phase in order; defer optional features until the core workflow is working.

## Phase 0: Project Foundation

- [x] Create a Spring Boot project with Java and Maven
- [x] Add Spring Web, Spring Data JPA, Validation, PostgreSQL, and Lombok dependencies
- [x] Configure application profiles for development and test
- [x] Add a `.gitignore` for Java, Maven, IDE, and environment files
- [x] Add a basic health endpoint
- [x] Create the initial package structure
- [x] Write a README with project goals and local setup instructions

## Phase 1: Agree on the API and Data Foundations

- [ ] Define controller, service, repository, entity, DTO, mapper, and exception responsibilities
- [ ] Keep API request/response models separate from persistence entities
- [ ] Define a consistent success and error response format
- [ ] Add global exception handling with `@RestControllerAdvice`
- [ ] Add request validation with clear validation messages
- [ ] Choose and document database migration tooling (Flyway or Liquibase)
- [ ] Add the initial database migration for users, projects, project membership, issues, comments, and labels
- [ ] Decide whether roles are global or project-specific, and avoid conflicting role sources
- [ ] Define shared conventions for timestamps, identifiers, and API versioning

## Phase 2: Register and Authenticate Users

**User flow:** A person registers, logs in, and receives credentials they can use to call protected endpoints.

- [ ] Create the user model with name, unique email, password hash, role, and timestamps
- [ ] Hash passwords with BCrypt; never store or return plaintext passwords
- [ ] Add registration and current-user retrieval endpoints
- [ ] Implement login and JWT access-token generation and validation
- [ ] Protect private endpoints and define the ADMIN, PROJECT_MANAGER, and MEMBER permissions
- [ ] Return safe user DTOs that exclude password hashes and other security data
- [ ] Test registration, login, invalid credentials, token handling, and authorization

## Phase 3: Create Projects and Manage Membership

**User flow:** An authenticated user creates a project, receives a unique project key (for example, `FLOW`), and adds teammates.

- [ ] Create the project model with name, unique key, description, owner, and timestamps
- [ ] Create project membership with a role or permission model consistent with Phase 1
- [ ] Add endpoints to create, list accessible projects, retrieve, update, and delete projects
- [ ] Add endpoints to add, list, change, and remove project members
- [ ] Enforce project access and project-management permissions on every relevant endpoint
- [ ] Decide and enforce whether project owners are automatically members
- [ ] Test project CRUD, membership changes, and attempts to access projects as a non-member

## Phase 4: Create and Work on Issues

**User flow:** A project member creates an issue, assigns it to an eligible teammate, and moves it through the team's workflow.

- [ ] Create the issue model with project, key, title, description, status, priority, type, reporter, assignee, due date, and timestamps
- [ ] Generate unique, readable issue keys scoped to a project (for example, `FLOW-123`), safely under concurrent creation
- [ ] Add endpoints to create, retrieve, update, and delete issues within accessible projects
- [ ] Define statuses: `TODO`, `IN_PROGRESS`, `IN_REVIEW`, `DONE`, and `CANCELED`
- [ ] Define priorities: `LOW`, `MEDIUM`, `HIGH`, and `CRITICAL`
- [ ] Define types: `TASK`, `BUG`, `STORY`, and `FEATURE`
- [ ] Validate allowed values and status transitions
- [ ] Allow assigning and unassigning users; only allow assignees who belong to the issue's project
- [ ] Enforce project membership and role permissions for issue actions
- [ ] Test issue lifecycle, invalid transitions, invalid project references, and assignment rules

## Phase 5: Collaborate and Find Work

**User flow:** Team members discuss issues, label them, and find work using filters and project summaries.

- [ ] Add and remove labels on issues
- [ ] Add and retrieve issue comments, recording their authors and creation times
- [ ] Add issue activity history for important changes, including actor, action, and time
- [ ] Add filtering by status, priority, type, assignee, and label
- [ ] Add sorting and pagination to issue lists
- [ ] Add a project endpoint summarizing issue counts by status
- [ ] Test comments, labels, activity history, filtering, pagination, and summaries

## Phase 6: Make the API Reliable and Ready to Use

- [ ] Define service-layer transaction boundaries
- [ ] Add optimistic locking for issues where appropriate and handle conflicting updates clearly
- [ ] Ensure `updated_at` is refreshed consistently on updates
- [ ] Add indexes for common lookups and filters, including project key, issue key, status, assignee, and labels as needed
- [ ] Use UTC consistently for date/time values
- [ ] Add request correlation IDs and useful application logging
- [ ] Add unit, controller, repository, and PostgreSQL-compatible integration tests for core behavior
- [ ] Review API responses and authorization rules for accidental data exposure
- [ ] Add OpenAPI documentation, JWT authentication details, and example requests/responses
- [ ] Add a Postman or Bruno collection
- [ ] Complete Docker Compose setup for the API and PostgreSQL, with configuration for secrets supplied through the environment
- [ ] Add safe local development seed data if useful
- [ ] Document architecture, tradeoffs, and local setup in the README

## Phase 7: Build a Minimal Frontend

Start this phase after the API workflow is stable and documented.

- [ ] Choose a minimal client approach
- [ ] Add login and logout
- [ ] Add a project list and project details
- [ ] Add an issue list or board with filters
- [ ] Add issue creation and editing
- [ ] Add issue details with comments, assignment, and status changes
- [ ] Use the frontend to exercise the same permissions and workflow exposed by the API

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
