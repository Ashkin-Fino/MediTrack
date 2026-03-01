# Java Installation & Configuration Guide

## 1. Introduction

This document explains how to install and configure Java (JDK/JRE) on Windows, macOS, and Linux systems.

---

# 2. JDK vs JRE

- **JDK (Java Development Kit)**  
  Used for developing Java applications.  
  Includes:
  - JRE
  - Compiler (javac)
  - Debugging tools

- **JRE (Java Runtime Environment)**  
  Used only to run Java applications.

👉 If you are a developer, install **JDK**.

---

# 3. Downloading Java (JDK)

Recommended options:

- Oracle JDK (https://www.oracle.com/java/technologies/downloads/)
- OpenJDK (https://jdk.java.net/)
- Adoptium (https://adoptium.net/) (Recommended for most users)

Download the latest LTS version (e.g., Java 17 or Java 21).

---

# 4. Installation Steps

---

### Step 1: Download JDK
Download `.exe` installer from Adoptium or Oracle.

### Step 2: Run Installer
- Double click installer
- Follow setup wizard
- Install to default directory (e.g., `C:\Program Files\Java\jdk-21`)

### Step 3: Set Environment Variables

1. Open:
   Control Panel → System → Advanced System Settings → Environment Variables

2. Under System Variables:
   - Click **New**
     - Variable name: `JAVA_HOME`
     - Variable value: `C:\Program Files\Java\jdk-21`

3. Edit `Path`
   - Add: `%JAVA_HOME%\bin`

### Step 4: Verify Installation

Open Command Prompt

Run:
    java -version
    javac -version

If installed correctly, version information will be displayed.

---

# 5. Running Your First Java Program

---

Create file:

HelloWorld.java

    '''java
    public class HelloWorld {
        public static void main(String[] args) {
            System.out.println("Hello, Java!");
        }
    }'''

Compile:
    - javac HelloWorld.java

Run:
    - java HelloWorld

---

# 6. Checking JAVA_HOME

---

Run:
    - echo %JAVA_HOME%
