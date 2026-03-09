# SafetyDrones - Mobile Application for Unmanned Aircraft

**SafetyDrones** is an Android application developed as a Final Year Project for the Computer Science Engineering degree. The platform centralizes critical information for drone operators, promoting safer, more regulated, and better-informed flights.

## Key Features

* **Restricted Zones Visualization**: Interactive map (Google Maps SDK) displaying prohibited or restricted flight areas in Portugal, based on ANAC (National Civil Aviation Authority) geographic data.
* **Drone Management (CRUD)**: Full management of drone fleets, allowing users to register, view, edit, and delete aircraft with automatic categorization.
* **Weather Forecasting**: Real-time weather data integration via Visual Crossing Weather API for flight planning.
* **Educational Content**: Dedicated section with videos and legal information categorized by drone type and flight class.
* **User Management**: Secure authentication system (Email/Google) and personalized profiles using Firebase.

## Architecture & Technologies

The project follows a **Client-Server** model with an **MVC** (Model-View-Controller) architectural pattern.

* **Language**: Java (Native Android).
* **Back-end (BaaS)**: **Firebase** (Authentication, Realtime Database, and Cloud Storage).
* **External APIs**:
    * Google Maps SDK (Geographic visualization).
    * Visual Crossing Weather (Meteorological data).
* **Networking**: Retrofit for API communication and JSON data parsing.

##  Development Methodology

The project was managed using the **Scrum** framework to ensure continuous improvement and alignment with project goals:

* **Sprints**: Work was organized into iterative cycles, allowing for regular feature increments.
* **Supervisor Interaction**: Frequent feedback loops with the project supervisor acted as "Stakeholder Reviews," ensuring the technical requirements and project vision were consistently met.
* **Phased Delivery**:
    1.  **Phase I**: Backlog definition, UI/UX prototyping, and core architecture setup.
    2.  **Phase II**: Sprint-based implementation of features, continuous testing (unit and functional), and final validation.

##  Validation & Results

The application was validated with real users and technical tests:
* **User Satisfaction**: Average score of 4.6 / 5.
* **Performance**: Fast loading times (1-3 seconds) and responsive interface.
* **Reliability**: 100% success rate in functional requirement fulfillment.

## Project Structure

Some parts of the project contain additional documentation:

- `res/layout` → UI structure documentation
- `res/drawable` → graphical resources documentation
- `assets/animations` → animation system documentation

Each directory contains its own README with detailed explanations.

---
**Author:** João Fernando da Silva Ferreira  
**Supervisor:** José Braga de Vasconcelos  
**Institution:** Universidade Lusófona do Porto (ULP)
