# Support Ticket Resolution - Design Document

## 1. Requirements

### Functional Requirements
- **Escalation Chain**: Tickets must be processed in this order: **Bot → Help Desk → Tech Support → Manager**.
- **Routing Logic**:
    - **Bot**: Handles `LOW` priority, `GENERAL` questions.
    - **Help Desk**: Handles `MEDIUM` priority, `BILLING` issues.
    - **Tech Support**: Handles `HIGH` priority, `BUG` reports.
    - **Manager**: Handles `CRITICAL` priority, `LEGAL` issues.
- **Fallback**: If a handler cannot resolve the ticket (criteria not met), it passes it to the next handler.

### Non-Functional Requirements
- **Clear Separation**: Each handler has a single responsibility (Single Responsibility Principle).
- **Open/Closed**: Easy to add new levels (e.g., "Level 2 Tech Support") without changing existing code.

## 2. High-Level Architecture

### Handler Chain Structure
```mermaid
graph LR
    Client --> BotHandler
    BotHandler -->|Cannot Resolve| HelpDeskHandler
    HelpDeskHandler -->|Cannot Resolve| TechSupportHandler
    TechSupportHandler -->|Cannot Resolve| ManagerHandler
    ManagerHandler -->|Cannot Resolve| Unresolved[Ticket Unresolved]
    
    BotHandler -->|Resolved| Done[Ticket Closed]
    HelpDeskHandler -->|Resolved| Done
    TechSupportHandler -->|Resolved| Done
    ManagerHandler -->|Resolved| Done
```

## 3. Class Design

### Class Diagram
```mermaid
classDiagram
    class Ticket {
        +String id
        +String message
        +Priority priority
        +Type type
    }

    class SupportHandler {
        #SupportHandler nextHandler
        +setNext(SupportHandler handler) SupportHandler
        +handle(Ticket ticket)
    }

    class BotHandler {
        +handle(Ticket ticket)
    }
    class HelpDeskHandler {
        +handle(Ticket ticket)
    }
    class TechSupportHandler {
        +handle(Ticket ticket)
    }
    class ManagerHandler {
        +handle(Ticket ticket)
    }

    SupportHandler <|-- BotHandler
    SupportHandler <|-- HelpDeskHandler
    SupportHandler <|-- TechSupportHandler
    SupportHandler <|-- ManagerHandler
```

## 4. Sequence Flow

1.  **Client** creates a `Ticket` (e.g., Priority: HIGH, Type: BUG).
2.  **Client** sends to `BotHandler`.
3.  **BotHandler** checks: Is Priority LOW? No. -> **Pass**.
4.  **HelpDeskHandler** checks: Is Priority MEDIUM? No. -> **Pass**.
5.  **TechSupportHandler** checks: Is Priority HIGH? Yes. -> **Resolve**.
