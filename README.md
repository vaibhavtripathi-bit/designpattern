# Design Patterns in Kotlin

A comprehensive collection of design pattern implementations in Kotlin with practical, real-world examples.

## Design Patterns Included

### Behavioral Patterns

| Pattern | Examples |
|---------|----------|
| **Mediator** | Air Traffic Control, Chat Room, Smart Home, Stock Exchange, UI Components |
| **Memento** | Browser History, Canvas Editor, Text Editor, Game Save States |
| **Chain of Responsibility** | Middleware Pipeline |

### Creational Patterns

| Pattern | Examples |
|---------|----------|
| **Builder** | Various configurations |

### Structural Patterns

| Pattern | Examples |
|---------|----------|
| **Decorator** | Coffee Shop, Data Source, Notification System, Pizza Builder |

### Other Patterns

- ATM State Machine
- Discount Calculator
- Loan Processing
- Logger System
- Payment Processing
- Parser Implementation

## Project Structure

```
designpattern/
├── app/                    # Android app module
├── design_docs/            # Design pattern documentation
│   ├── mediator_*.md       # Mediator pattern examples
│   ├── memento_*.md        # Memento pattern examples
│   ├── decorator_*.md      # Decorator pattern examples
│   └── ...
├── build.gradle.kts
└── settings.gradle.kts
```

## Tech Stack

- **Language**: Kotlin
- **Build System**: Gradle with Kotlin DSL
- **Platform**: Android

## Getting Started

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the app or explore individual pattern implementations

## Documentation

Each pattern has detailed documentation in the `design_docs/` folder explaining:
- Problem statement
- Solution approach
- UML diagrams
- Kotlin implementation
- Use cases

## License

MIT License
