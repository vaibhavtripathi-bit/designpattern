# Visitor Pattern Example 5 - Shape Geometry

## 1. Requirements
- **Goal**: Perform geometric calculations and serialization on different shapes.
- **Shapes**:
    - `Circle`: Radius.
    - `Rectangle`: Width, Height.
    - `Triangle`: Sides a, b, c.
- **Operations**:
    - `AreaVisitor`: Calculates area (using standard formulas).
    - `PerimeterVisitor`: Calculates perimeter.
    - `JsonExportVisitor`: Exports shape properties to JSON format.

## 2. Architecture
- **Pattern**: **Visitor**.
- **Key Idea**: Decouple the operations (Area, Perimeter, JSON) from the Shape classes.

## 3. Class Design
```mermaid
classDiagram
    class Shape {
        <<interface>>
        +accept(visitor: ShapeVisitor)
    }
    class Circle {
        +radius: Double
        +accept(visitor)
    }
    class Rectangle {
        +width: Double
        +height: Double
        +accept(visitor)
    }
    class Triangle {
        +a: Double
        +b: Double
        +c: Double
        +accept(visitor)
    }

    class ShapeVisitor {
        <<interface>>
        +visit(Circle)
        +visit(Rectangle)
        +visit(Triangle)
    }

    class AreaVisitor {
        +totalArea: Double
        +visit(Circle)
        +visit(Rectangle)
        +visit(Triangle)
    }
    class PerimeterVisitor {
        +totalPerimeter: Double
        +visit(Circle)
        +visit(Rectangle)
        +visit(Triangle)
    }
    class JsonExportVisitor {
        +json: String
        +visit(Circle)
        +visit(Rectangle)
        +visit(Triangle)
    }

    Shape <|-- Circle
    Shape <|-- Rectangle
    Shape <|-- Triangle
    ShapeVisitor <|.. AreaVisitor
    ShapeVisitor <|.. PerimeterVisitor
    ShapeVisitor <|.. JsonExportVisitor
```
