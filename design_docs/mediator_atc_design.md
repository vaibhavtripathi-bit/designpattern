# Mediator Pattern Example 2 - Air Traffic Control

## 1. Requirements
- **Goal**: Coordinate landing and takeoff of flights to prevent collisions.
- **Mediator**: `ControlTower` (Manages runway status).
- **Colleague**: `Flight` (Requests permission).

## 2. Architecture
- **Pattern**: **Mediator**.
- **Key Idea**: Flights do not communicate with each other. They ask the `ControlTower` for permission. The tower keeps track of whether the runway is free.

## 3. Class Design
```mermaid
classDiagram
    class ATCMediator {
        <<interface>>
        +registerFlight(flight: Aircraft)
        +requestLanding(flight: Aircraft): Boolean
        +requestTakeoff(flight: Aircraft): Boolean
    }

    class ControlTower {
        -runwayFree: Boolean
        -flights: List<Aircraft>
        +registerFlight(flight: Aircraft)
        +requestLanding(flight: Aircraft): Boolean
        +requestTakeoff(flight: Aircraft): Boolean
    }

    class Aircraft {
        <<abstract>>
        #mediator: ATCMediator
        +flightNumber: String
        +land()
        +takeOff()
    }

    class Flight {
        +land()
        +takeOff()
    }

    ATCMediator <|.. ControlTower
    Aircraft <|-- Flight
    ControlTower o-- Aircraft : Manages
    Aircraft --> ATCMediator : Uses
```
