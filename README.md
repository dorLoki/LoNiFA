# LoNiFa Website Project

*Note: This project ended and the website is now offline.*

## Overview

The LoNiFa Website project was developed as a comprehensive platform integrating various functionalities for a gaming and role-playing community. Built on Spring Boot, the website combined server management tools, user administration, and an experimental RPG module inspired by Dungeons & Dragons. 

## Key Features

### Multi-Role User Management
- **Role-Based Access:**  
  The system supported a robust user management framework with distinct roles such as Admin and Moderators. Each role was granted specific permissions to manage various sections of the website.
  
- **Security and Administration:**  
  Admin users had full control over the site’s functionalities, while moderators were assigned to manage designated areas such as Minecraft server operations and the Palworl module.

### Minecraft Integration
- **Console Monitoring & Command Execution:**  
  Moderators could view the live server console through a user-friendly GUI. This feature allowed them to monitor server activities in real time.
  
- **Server Control via RCON:**  
  The integration enabled mods to restart the Minecraft server and send in-game commands directly. All these operations were handled internally using the RCON protocol, ensuring secure and efficient command execution.

### Palworl Module
- **User List and Server Management:**  
  Similar to the Minecraft integration, the Palworl module provided moderators with a graphical interface to view the active user list and perform server restarts. This feature also utilized RCON to manage server operations.

### Dungeons & Dragons (DnD) Module – Work In Progress
- **Character Creation:**  
  Users could create their own characters by entering details such as name, class, and various attributes. This laid the groundwork for a role-playing game experience within the platform.
  
- **Inventory Management:**  
  The DnD module featured a basic inventory system that allowed users to manage and rearrange items. Although functional, this aspect was limited in scope.
  
- **3D Renderable Dice:**  
  An experimental feature included interactive 3D dice that users could roll, adding a tactile element to the gameplay.
  
- **Status:**  
  The DnD module was never fully completed and has been put on hold. Its inclusion in the project served as a proof-of-concept for integrating RPG mechanics into a web-based platform.

## Technical Architecture

### Framework and Platform
- **Spring Boot:**  
  The entire website was built on Spring Boot, providing a solid foundation for building RESTful services and managing backend operations.
  
- **Java-Based Implementation:**  
  All components, from server management to the experimental RPG module, were implemented in Java, ensuring compatibility and ease of integration.

### Communication and Integration
- **RCON Protocol:**  
  Both the Minecraft and Palworl modules relied on the RCON protocol to execute server commands securely and efficiently. This integration was crucial for real-time server management.
  
- **User Interface:**  
  The website featured a modern, responsive GUI that facilitated easy navigation and control over various functionalities. While the Minecraft and Palworl modules were fully integrated, the DnD module’s interface remains incomplete.

## Setup and Deployment

### Development Environment
- **Maven Build System:**  
  The project was structured using Maven, which managed dependencies and streamlined the build process.
  
- **Configuration:**  
  Application properties and environment settings were defined in configuration files, allowing for flexible deployment across different environments.

### Deployment Process
1. **Building the Project:**  
   - Use Maven to clean, compile, and package the application.
   - Example command:  
     ```bash
     mvn clean package
     ```
   
2. **Server Deployment:**  
   - The packaged application was deployed on a server environment where Spring Boot managed the runtime.
   - Appropriate configurations ensured that both the web interface and internal RCON integrations operated smoothly.

3. **Accessing the Website:**  
   - Once deployed, users could access the website via a standard web browser. The multi-role user system allowed for differentiated access to various modules (e.g., Minecraft console, Palworl controls).

## Project Status and Future Considerations

- **Project Completion:**  
  The LoNiFa Website project has been successfully completed. All primary functionalities for server management (Minecraft and Palworl) are operational.
  
- **Experimental Modules:**  
  The DnD module was developed as an experimental feature. Despite initial efforts in character creation, inventory management, and 3D dice rendering, the module remains incomplete and is currently on hold.
  
- **Website Offline:**  
  The live website is no longer active. However, the project continues to serve as a valuable reference for integrating multiple systems within a single Spring Boot application.

## Conclusion

The LoNiFa Website project represents a multifaceted approach to web-based server management and interactive gaming. It successfully integrated role-based user management, real-time server control via RCON, and an innovative (though unfinished) RPG module. Despite the website being offline, the project remains a significant learning resource in the fields of web development, server administration, and interactive application design.