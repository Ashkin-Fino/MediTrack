# 1. Class Loader

## What is Class Loader?

Class Loader is responsible for loading `.class` files into memory.

## Types of Class Loaders

1. Bootstrap ClassLoader
   - Loads core Java classes (java.lang, java.util)

2. Extension ClassLoader
   - Loads extension libraries

3. Application ClassLoader
   - Loads classes from classpath

## Class Loading Process

1. Loading
2. Linking
   - Verification
   - Preparation
   - Resolution
3. Initialization

---

# 2. Runtime Data Areas

When JVM runs a program, it creates several memory areas.

## 2.1 Heap

- Stores objects and instance variables
- Shared among all threads
- Managed by Garbage Collector
- Largest memory area

Example:
    Student s = new student();

Object is stored in Heap.

---

## 2.2 Stack

- Each thread has its own stack
- Stores:
  - Method calls
  - Local variables
  - References to objects

Example:
    int x = 9;

Stored in stack (if local variable).

When method finishes → stack frame removed.

---

## 2.3 Method Area

- Stores:
  - Class metadata
  - Static variables
  - Method bytecode
- Shared among all threads

In modern JVM, it is called **Metaspace**.

---

## 2.4 PC Register (Program Counter Register)

- Each thread has its own PC register
- Stores the address of the currently executing instruction
- Helps JVM track execution flow

---

# 3. Execution Engine

Execution Engine executes bytecode loaded into memory.

It consists of:

1. Interpreter
2. JIT Compiler
3. Garbage Collector

---

# 4. JIT Compiler vs Interpreter

## Interpreter

- Reads bytecode line by line
- Translates to machine code
- Executes immediately
- Slower

## JIT (Just-In-Time) Compiler

- Compiles frequently used bytecode into native machine code
- Stores optimized version
- Faster execution

### Comparison

| Feature        | Interpreter | JIT Compiler |
|---------------|------------|--------------|
| Speed         | Slower    | Faster       |
| Compilation   | Line-by-line | Entire block |
| Optimization  | Minimal   | High         |

JVM uses both:
- Interpreter for first execution
- JIT for frequently used methods

### Garbage Collector

- Automatically frees unused objects
- Prevents memory leaks
- Works mainly in Heap area

---

# 5. Write Once, Run Anywhere (WORA)

Java follows:

> Write Once, Run Anywhere

Why?

Because:
1. Java code → compiled to bytecode
2. Bytecode runs on JVM
3. JVM exists for:
   - Windows
   - macOS
   - Linux
   - Android

So same `.class` file runs on any OS with JVM.

Example:
    javac Program.java

Produces:
    Program.class

That file runs anywhere with JVM.

---

# 6. Summary Diagram (Conceptual)

Java Source Code
        ↓
Compiler (javac)
        ↓
Bytecode (.class)
        ↓
JVM
   ├── Class Loader
   ├── Runtime Data Areas
   └── Execution Engine
        ↓
Machine Code
        ↓
Output

---

# 7. Conclusion

JVM provides:

- Platform independence
- Memory management
- Automatic garbage collection
- Performance optimization using JIT

This is why Java is powerful, portable, and widely used.