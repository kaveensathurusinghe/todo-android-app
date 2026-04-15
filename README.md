# ToDo Android App

A simple, modern Android to-do application built as a job assessment. It focuses on clean architecture, responsive UI (light/dark themes), and a smooth user experience.

## Features

- User registration and login with secure password hashing
- Per-user todo lists (each user sees only their own tasks)
- Add, edit, delete, and mark todos as completed
- Local data persistence using Room (no backend required)
- Simple prioritization and date-based sorting with chips on the home screen
- System-following light and dark themes using a strict palette:
  - `#FFFFFF` (background)
  - `#E9FBFF` (accent light)
  - `#115166` (primary blue)
- Haptic feedback on key interactions (buttons, toggles, actions)
- Polished UI with animations and empty-state messaging

## Tech Stack

- Language: Java
- Minimum Android SDK: (set in `app/build.gradle.kts`)
- Build system: Gradle (Kotlin DSL)
- Architecture: MVVM-style with distinct data, domain, and presentation layers
- Persistence: Room database + SharedPreferences
- UI: AndroidX, Material Components, RecyclerView, ConstraintLayout

## Project Structure

- `app/src/main/java/com/example/todo`
  - `data/` – Room entities, DAOs, database, preferences
  - `domain/` – Models and use cases
  - `repository/` – Abstractions over data sources
  - `presentation/` – Activities, view models, adapters
  - `utils/` – Validation, encryption, date utilities, constants
- `app/src/main/res` – Layouts, themes, colors, drawables, navigation

## Getting Started

### Prerequisites

- Android Studio (Arctic Fox or newer recommended)
- Java 11 JDK
- Android SDK and emulator/device set up

### Running the App (Debug)

1. Clone the repository:
   ```bash
   git clone <your-repo-url>.git
   cd ToDoApp
   ```
2. Open the project in Android Studio.
3. Let Gradle sync finish.
4. Run the `app` module on an emulator or a connected device.

Alternatively, from the command line:

```bash
./gradlew assembleDebug
```

Then install the generated APK from `app/build/outputs/apk/debug/` onto a device or emulator.

## Release APK

A signed release APK can be generated via **Build > Generate Signed Bundle / APK** in Android Studio.

If you already generated one, it will typically be located at:

- `app/release/app-release.apk`

You can attach this file to a GitHub Release so others can download and install the app easily.

## Notes

- This project is intentionally local-only: there is no remote backend.
- Passwords are never stored in plain text; a hashing utility is used before persistence.
- The UI is tuned for both light and dark modes and uses haptic feedback to make interactions feel more responsive.
