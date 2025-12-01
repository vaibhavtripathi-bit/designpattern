# Mediator Pattern Example 3 - Smart Home Hub

## 1. Requirements
- **Goal**: Automate interactions between smart devices based on events.
- **Mediator**: `HomeHub` (Central controller).
- **Colleagues**: `Alarm`, `Light`, `CoffeePot`.
- **Scenario**: When `Alarm` triggers, `HomeHub` should turn on `Light` and start `CoffeePot`.

## 2. Architecture
- **Pattern**: **Mediator**.
- **Key Idea**: Devices send events to the `HomeHub` via `notify()`. The Hub contains the business logic to decide what other devices should react to that event.

## 3. Class Design
```mermaid
classDiagram
    class SmartHomeMediator {
        <<interface>>
        +notify(sender: SmartDevice, event: String)
    }

    class HomeHub {
        -light: Light
        -coffeePot: CoffeePot
        -alarm: Alarm
        +setDevices(light: Light, coffeePot: CoffeePot, alarm: Alarm)
        +notify(sender: SmartDevice, event: String)
    }

    class SmartDevice {
        <<abstract>>
        #mediator: SmartHomeMediator
        +name: String
    }

    class Alarm {
        +trigger()
    }
    class Light {
        +turnOn()
        +turnOff()
        +isOn(): Boolean
    }
    class CoffeePot {
        +startBrewing()
        +isBrewing(): Boolean
    }

    SmartHomeMediator <|.. HomeHub
    SmartDevice <|-- Alarm
    SmartDevice <|-- Light
    SmartDevice <|-- CoffeePot
    HomeHub o-- SmartDevice : Controls
    SmartDevice --> SmartHomeMediator : Notifies
```
