# Visitor Pattern Example 3 - File System

## 1. Requirements
- **Goal**: Perform operations (Calculate Size, Search Files) on a hierarchical file system structure.
- **Structure**:
    - `File`: Has a name and size.
    - `Directory`: Has a name and a list of children (Files or Directories).
- **Operations**:
    - `SizeCalculatorVisitor`: Calculates the total size of a directory (recursive sum).
    - `FileSearchVisitor`: Finds all files matching a name pattern (recursive search).

## 2. Architecture
- **Pattern**: **Visitor** combined with **Composite**.
- **Key Idea**: The Visitor traverses the tree structure. When visiting a `Directory`, the visitor (or the directory itself) iterates over children to continue the traversal.

## 3. Class Design
```mermaid
classDiagram
    class FileSystemElement {
        <<interface>>
        +accept(visitor: FileSystemVisitor)
    }
    class File {
        +name: String
        +sizeKb: Int
        +accept(visitor)
    }
    class Directory {
        +name: String
        +children: List<FileSystemElement>
        +accept(visitor)
    }

    class FileSystemVisitor {
        <<interface>>
        +visit(File)
        +visit(Directory)
    }

    class SizeCalculatorVisitor {
        +totalSizeKb: Int
        +visit(File)
        +visit(Directory)
    }
    class FileSearchVisitor {
        +foundFiles: List<File>
        +visit(File)
        +visit(Directory)
    }

    FileSystemElement <|-- File
    FileSystemElement <|-- Directory
    FileSystemVisitor <|.. SizeCalculatorVisitor
    FileSystemVisitor <|.. FileSearchVisitor
```
