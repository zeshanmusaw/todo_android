ToDo App

📌 Overview

This is a To-Do App built using MVVM architecture, Hilt for Dependency Injection, Retrofit for API calls, and Jetpack Compose for UI. It allows users to add, update, delete, and fetch tasks from a REST API.

🛠️ Tech Stack

Kotlin

Jetpack Compose (Modern UI)

MVVM Architecture

Hilt (Dependency Injection)

Retrofit (API calls)

ViewModel (State management)

LiveData & State Management

🎯 Features

✅ Fetch tasks from API✅ Add a new task✅ Update task status✅ Edit task title✅ Delete task✅ UI built with Jetpack Compose✅ Dependency Injection using Hilt✅ Error handling with Snackbar

🚀 Setup Instructions

1️⃣ Clone the Repository

git clone https://github.com/your-username/todo-app.git
cd todo-app

2️⃣ Open in Android Studio

Open the project in Android Studio

Ensure you have the latest Gradle and Android SDK installed

3️⃣ Update Dependencies

Sync Gradle files:

gradlew build

4️⃣ Run the App

Connect a device or open an emulator

Click Run ▶️ in Android Studio

🔧 API Configuration

This app uses a dummy API for tasks. Update the API base URL in RetrofitInstance.kt:

private const val BASE_URL = "https://dummyjson.com"

If using a different API, update endpoints accordingly in ApiService.kt.

📦 Project Structure

📂 com.example.todoapp
│── 📂 data            # API service and models
│── 📂 ui              # Jetpack Compose UI components
│── 📂 viewmodel       # ViewModel for managing UI state
│── 📜 ToDoApp.kt      # Hilt Application class
│── 📜 MainActivity.kt # Entry point with @AndroidEntryPoint

📌 Dependencies

Ensure the following dependencies are included in build.gradle.kts:

// Hilt
implementation("com.google.dagger:hilt-android:2.48")
kapt("com.google.dagger:hilt-compiler:2.48")

// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

🛠️ Troubleshooting

Hilt Plugin Error?

Add id("com.google.dagger.hilt.android") to build.gradle.kts

Run: File > Sync Project with Gradle Files

API Errors?

Check if the API is running

Ensure correct Base URL in RetrofitInstance.kt

Build Fails?

Run: File > Invalidate Caches / Restart

Run: gradlew clean && gradlew build

📝 License

This project is MIT licensed. Feel free to use and modify it as needed.

🚀 Happy Coding! 🎯
