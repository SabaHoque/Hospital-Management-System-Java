# Hospital Management System - HealthMate Project
This is a Java based Hospital Management System project.
This project was refactored for the Software Architecture (CSE423) course by the students of **East West University.** 

Original Team members - **@UDDIN, MD.ALFAZ @SHAMIM, MD. SHAFAYAT HOSSAIN @RIYAD, KAZI FARDIN RAHMAN @DAS, ANIQUE KUMAR**

Updated & Maintained by - **@HOQUE, SABA** and team (fork contributors)

# Current version
    V1.0.1   released on   28-12-2024   by   Alfaz
  Check releases https://github.com/alfaaz-uddin/Hospital-Management-Project/releases/
# In this  project -
📌The admin can login and control the management system.<br>
📌By default the username and password is "admin"<br>
📌Admin can add new patient, update the patient information, search and discharge patients from the dashboard.<br>
📌Doctors can Register by filling the required informations.<br>
📌Doctors can login to their dashboard and can see the patients assigned to them.<br>
📌Doctors can download their patient list in .doc file.<br>
     and the upadated release.

# 🔧 Updates by Our Team

Our team performed a comprehensive refactoring and software engineering improvement of the codebase. The following major areas were addressed:

## 1. ✅ SOLID Principles Implementation

The codebase was refactored to align with all five SOLID principles:

- **Single Responsibility Principle (SRP):** Classes were restructured so that each class handles only one concern. For example, patient data handling, UI rendering, and business logic were separated into distinct classes.
- **Open/Closed Principle (OCP):** Core components were made extensible via abstraction, allowing new features to be added without modifying existing stable code.
- **Liskov Substitution Principle (LSP):** Subclass hierarchies (e.g., different user roles like Admin and Doctor) were ensured to be interchangeable through proper inheritance.
- **Interface Segregation Principle (ISP):** Large, monolithic interfaces were broken down into smaller, role-specific interfaces so that implementing classes are not forced to depend on methods they don't use.
- **Dependency Inversion Principle (DIP):** High-level modules were decoupled from low-level modules by introducing abstractions, improving testability and flexibility.

---

## 2. 🧹 Code Smell Removal

The following common code smells were identified and removed:

- **Long Methods:** Large methods were broken into smaller, focused helper methods.
- **Duplicate Code:** Repeated logic across classes was extracted into shared utility methods or base classes.
- **Large Classes (God Classes):** Oversized classes were split based on responsibility.
- **Magic Numbers/Strings:** Hardcoded literals were replaced with named constants.
- **Inappropriate Intimacy:** Classes that were overly dependent on each other's internals were decoupled using proper encapsulation.
- **Dead Code:** Unused variables, methods, and imports were removed.

---

## 3. 🏗️ Design Patterns Implemented

The following design patterns were applied where appropriate to improve the system's structure and maintainability:

| Pattern | Category | Usage |
|---|---|---|
| **Singleton** | Creational | Ensures a single shared instance for database/config management |
| **Factory Method** | Creational | Creates different user types (Admin, Doctor) without exposing instantiation logic |
| **Observer** | Behavioral | Notifies relevant components when patient status or assignments change |
| **Strategy** | Behavioral | Allows interchangeable behavior for patient search and sorting logic |
| **Facade** | Structural | Provides a simplified interface to complex subsystems (e.g., patient management workflow) |

---

## 4. 📐 Architectural Diagrams

Architectural diagrams were built to document and visualize the system structure. These include:

- **Class Diagram** — Shows relationships between all major classes (Admin, Doctor, Patient, etc.)
- **Use Case Diagram** — Illustrates interactions between system actors (Admin, Doctor) and system features
- **Sequence Diagram** — Depicts the flow of key operations such as patient admission, doctor login, and report generation
- **Component Diagram** — Shows how modules are organized and interact at a high level
- **Package Diagram** — Represents the logical grouping of classes into packages

> Diagrams are included in the `/diagrams` folder of this repository


# 🔗Project web -
https://alfaaz-uddin.github.io/HealthMate/ 
<!-- # Demo⬇️
![image](https://github.com/user-attachments/assets/c4f2b061-cce5-45f6-9b24-97f5cf4615c0)
![image](https://github.com/user-attachments/assets/30f4ed47-7901-4444-9b50-139c9f41d26e)
![image](https://github.com/user-attachments/assets/ec629a4a-fea8-4154-b2c1-eed234728c49)
![image](https://github.com/user-attachments/assets/ccbcda62-433d-4e83-9eb6-c21243fa8074)
![image](https://github.com/user-attachments/assets/50d11af5-f51e-4a1d-90d3-26a3d4487070)
![image](https://github.com/user-attachments/assets/43b5ba2d-1088-42a5-9e25-eabc7411a3d2)
![image](https://github.com/user-attachments/assets/2af0a285-0857-4aeb-b50e-1cb046a80351)
![image](https://github.com/user-attachments/assets/4c09304c-6c05-4ac7-ac0c-979094b10494)
![image](https://github.com/user-attachments/assets/c31ffa08-a131-4230-93a8-499070254a7a)

# Updates on version v1.0.1 ⬇️
![image](https://github.com/user-attachments/assets/9164aa74-6cb0-4165-85f4-838b7984a512)
![image](https://github.com/user-attachments/assets/e6ce2144-9cbb-4f40-ac7c-83043d9d838b)
![image](https://github.com/user-attachments/assets/465f00cc-2d27-4eb5-80dc-af068cfd7645)
![image](https://github.com/user-attachments/assets/92287eaf-34ed-4503-94a5-8eb7609f0d37)
![image](https://github.com/user-attachments/assets/2d164b0f-7866-438a-9bbb-b2cd12442926)



# Demo⬇️
<img src="https://github.com/user-attachments/assets/c4f2b061-cce5-45f6-9b24-97f5cf4615c0" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/30f4ed47-7901-4444-9b50-139c9f41d26e" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/ec629a4a-fea8-4154-b2c1-eed234728c49" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/ccbcda62-433d-4e83-9eb6-c21243fa8074" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/50d11af5-f51e-4a1d-90d3-26a3d4487070" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/43b5ba2d-1088-42a5-9e25-eabc7411a3d2" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/2af0a285-0857-4aeb-b50e-1cb046a80351" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/4c09304c-6c05-4ac7-ac0c-979094b10494" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/c31ffa08-a131-4230-93a8-499070254a7a" alt="image" width="300" />

# Updates on version v1.0.1 ⬇️
<img src="https://github.com/user-attachments/assets/9164aa74-6cb0-4165-85f4-838b7984a512" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/e6ce2144-9cbb-4f40-ac7c-83043d9d838b" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/465f00cc-2d27-4eb5-80dc-af068cfd7645" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/92287eaf-34ed-4503-94a5-8eb7609f0d37" alt="image" width="300" />
<img src="https://github.com/user-attachments/assets/2d164b0f-7866-438a-9bbb-b2cd12442926" alt="image" width="300" />
         -->
# Demo⬇️
<img src="https://github.com/user-attachments/assets/c4f2b061-cce5-45f6-9b24-97f5cf4615c0" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/30f4ed47-7901-4444-9b50-139c9f41d26e" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/ec629a4a-fea8-4154-b2c1-eed234728c49" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/ccbcda62-433d-4e83-9eb6-c21243fa8074" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/50d11af5-f51e-4a1d-90d3-26a3d4487070" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/43b5ba2d-1088-42a5-9e25-eabc7411a3d2" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/2af0a285-0857-4aeb-b50e-1cb046a80351" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/4c09304c-6c05-4ac7-ac0c-979094b10494" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/c31ffa08-a131-4230-93a8-499070254a7a" alt="image" width="400" />

# Updates on version v1.0.1 🆕
<img src="https://github.com/user-attachments/assets/9164aa74-6cb0-4165-85f4-838b7984a512" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/e6ce2144-9cbb-4f40-ac7c-83043d9d838b" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/465f00cc-2d27-4eb5-80dc-af068cfd7645" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/92287eaf-34ed-4503-94a5-8eb7609f0d37" alt="image" width="400" />
<img src="https://github.com/user-attachments/assets/2d164b0f-7866-438a-9bbb-b2cd12442926" alt="image" width="400" />

