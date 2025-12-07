# 🎓 Student Dashboard (Quizzy)

A modern, modular Android application built with **Jetpack Compose** and **Clean Architecture**. This app demonstrates a complete flow from a custom Login UI to a detailed Student Dashboard, fetching real-time data from a mock API.

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-1.9.0-purple?logo=kotlin" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-Material3-blue?logo=android" />
  <img src="https://img.shields.io/badge/Architecture-Clean%20%2B%20MVVM-green" />
  <img src="https://img.shields.io/badge/DI-Hilt-orange" />
</p>

## 🔑 Login Credentials

To test the application, use the following credentials. **Note: Inputs are case-sensitive.**

| Field | Value |
| :--- | :--- |
| **School ID** | `sggp782001` |
| **Student ID** | `sg211` |

> **Note:** The authentication logic maps these IDs to a Firebase Email/Password flow internally (`sg211@studentdashboard.app` / `sggp782001`).

## 🛠 Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Architecture:** MVVM + Clean Architecture (Multi-module)
- **Dependency Injection:** Dagger Hilt
- **Network:** Retrofit, OkHttp, GSON
- **Async:** Kotlin Coroutines & Flows
- **Authentication:** Firebase Authentication
- **Image Loading:** (Native Vectors & Drawables)
- **Local Storage:** SharedPreferences (Session Management)

## 📂 Modular Structure

The project follows a strict separation of concerns:

- **`:app` (Presentation Layer)**: Contains UI (Screens, Composables), ViewModels, and DI setup.
- **`:domain` (Business Logic)**: Pure Kotlin module. Contains UseCases, Repository Interfaces, and Models.
- **`:data` (Data Layer)**: Handles API calls, Firebase Auth, DTOs, and Repository Implementations.

## ✨ Features

1.  **Custom Authentication Flow**:
    - Pixel-perfect UI matching Figma design.
    - Custom drawn backgrounds using Canvas/Path API.
    - Handling of custom IDs mapped to backend credentials.
2.  **Rich Dashboard UI**:
    - Parsing complex nested JSON data.
    - Dynamic UI states (Loading, Error, Success).
    - Data visualization (Weekly Streak bubbles, Trend indicators).
    - Defensive UI programming (Handling missing data/nulls gracefully).
3.  **Session Management**:
    - Auto-login using SharedPreferences.
    - Persistent session handling.


## 🔗 Design Reference

The UI was built based on the following Figma design:
[Quizzy Figma Design](https://www.figma.com/design/D3oikbeJno5ewk28AaAuA7/Quizzy?node-id=7-1323&t=74EPoWkmDCvoqjGa-0)
