# Mediator Pattern Example 1 - Chat Room

## 1. Requirements
- **Goal**: Enable users to send messages to each other without having direct references to one another.
- **Mediator**: `ChatRoom` (Handles message broadcasting).
- **Colleague**: `ChatUser` (Sends/Receives messages).

## 2. Architecture
- **Pattern**: **Mediator**.
- **Key Idea**: Users send messages to the `ChatRoom`. The `ChatRoom` iterates through its list of registered users and calls their `receive` method (excluding the sender).

## 3. Class Design
```mermaid
classDiagram
    class ChatMediator {
        <<interface>>
        +sendMessage(msg: String, user: User)
        +addUser(user: User)
    }

    class ChatRoom {
        -users: List<User>
        +sendMessage(msg: String, user: User)
        +addUser(user: User)
    }

    class User {
        <<abstract>>
        #mediator: ChatMediator
        +name: String
        +send(msg: String)
        +receive(msg: String)
    }

    class ChatUser {
        +send(msg: String)
        +receive(msg: String)
    }

    ChatMediator <|.. ChatRoom
    User <|-- ChatUser
    ChatRoom o-- User : Contains
    User --> ChatMediator : Uses
```
