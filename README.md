# SkillForge 🚀
### An Android Learning Platform

SkillForge is an Android application designed to help users explore technology courses and manage their learning journey. The project is being developed as part of my journey toward becoming an Android developer.

**Project Status:** 🚧 Work in Progress

Some features are implemented, while others are still being developed and tested.

## ✨ Features

- **User Authentication:** User registration and login using Firebase Authentication.
- **User Data Storage:** Store user profile information using Cloud Firestore.
- **Course Catalog:** Fetch course information from a remote API using Retrofit.
- **Course Details:** Display information about individual courses.
- **Course Enrollment:** Enroll in a course and manage the current enrollment using Room Database.
- **Learning Progress:** A progress screen for the enrolled course, currently under development.
- **Modern UI:** Built with Jetpack Compose and Material 3.
- **AI Chatbot:** AI chat interface integration, currently under development.

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Primary programming language |
| Jetpack Compose | Declarative Android UI |
| Material 3 | UI components and styling |
| MVVM | Application architecture |
| Navigation Compose | Screen navigation |
| Retrofit | API communication |
| Room Database | Local data persistence |
| Kotlin Coroutines & Flow | Asynchronous operations and reactive data |
| Firebase Authentication | User authentication |
| Cloud Firestore | Cloud user data storage |
| Gradle | Build and dependency management |

## 📱 Application Screens

The application includes screens for:

- Welcome and authentication
- Home and course discovery
- Course details
- Course enrollment
- My Progress
- User profile
- AI chat interface

Screens and features are at different stages of development. Screenshots and a demonstration will be added as the application progresses.

## 🏗️ Architecture

SkillForge follows the MVVM architecture approach to separate UI logic from application and data logic.

- **UI Layer:** Jetpack Compose screens
- **ViewModel Layer:** UI state and screen-related logic
- **Data Layer:** Repositories and data sources
- **Remote Data:** Course information retrieved through Retrofit
- **Local Data:** Enrollment information stored using Room
- **Cloud Services:** Firebase Authentication and Cloud Firestore

## ⚙️ Setup

### Prerequisites

- Android Studio
- Android SDK compatible with the project configuration
- JDK compatible with the Gradle setup
- A Firebase project for authentication and Firestore features

### Run the Project

1. Clone the repository:

   ```bash
   git clone https://github.com/dipayanari72/SkillForge.git
   ```

2. Open the project in Android Studio.
3. Configure your own Firebase project and add the required `google-services.json` file to the `app/` directory.
4. Sync the project with Gradle.
5. Run the app on an emulator or a compatible Android device.

Some features may require additional configuration or further development.

## 🗺️ Future Improvements

- Complete and test the course enrollment workflow.
- Connect the enrolled course to the learning progress screen.
- Complete AI chatbot integration.
- Improve loading, error, and empty states for API requests.
- Add better error handling and user feedback.
- Improve UI consistency and accessibility.
- Add automated tests and application documentation.

## 🎯 Project Goal

The goal of SkillForge is to build a practical Android application while learning modern Android development, API integration, local data persistence, authentication, and AI-powered features.

## 👨‍💻 Author

**Dipayan Ari**

GitHub: [@dipayanari72](https://github.com/dipayanari72)

---

*SkillForge is a learning and portfolio project. It is actively being developed, and not all features are complete or fully tested.*
