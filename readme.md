**Expense Tracker** is a Java Spring Boot application that helps users track expenses, budgets, categories, and alerts. The project uses a CQRS-style layering (command/query separation) and standard Spring technologies (Spring Data JPA, Spring Web, Spring Boot).

## Project layout (high level)

```
src/main/java/.../expensetracker
├── alert/          # alert domain + APIs + handlers + events
├── budget/         # budget domain + APIs + handlers
├── category/       # category domain + APIs + handlers
├── expense/        # expense domain + APIs + handlers
└── ...
```

## Prerequisites

- Java 17+ (project uses `maven` wrapper `mvnw`)
- Maven (or use provided `./mvnw`)
- H2 for development (check `application.yml` for data source)
- IDE: IntelliJ IDEA or VSCode (project contains `.idea` and `.vscode` configs)

## Assumptions

- Users can define custom categories for their expenses.
- Category templates are automatically created at application startup; users can base their custom categories on these templates.
- Users can define a monthly budget per category and configure alert thresholds (percentage-based) to receive notifications when spending approaches the limit.
- Alerts are generated daily and stored in the database.
- A scheduled publisher sends newly created alerts to Kafka.
- A Kafka listener consumes these alerts, updates their status, and records the trigger time.
- Users can retrieve monthly reports per category and daily summaries of their expenses.
- Users can generate comparison reports between any two months.
- The application uses Spring AOP (Aspects) to inject the current user context into relevant service methods.
For clarity, this aspect is implemented in a single location but can be applied broadly across the application.