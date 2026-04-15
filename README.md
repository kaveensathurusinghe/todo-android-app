# ToDo Android App

This is an Android to‑do app I built for a job assessment. My goal was to keep the architecture clean and the UI simple, modern, and pleasant to use.

## What the app does

- Lets a user register and log in (passwords are stored securely using hashing)
- Keeps a separate todo list per user
- Add, edit, delete, and mark todos as completed
- Stores data locally using Room, so it works completely offline (no backend)
- Offers simple prioritization and date-based sorting using chips on the home screen
- Supports light and dark mode following the system theme
- Uses a strict 3‑color palette:
  - `#FFFFFF` – main background
  - `#E9FBFF` – light accent
  - `#115166` – primary blue
- Adds haptic feedback on important actions and small animations for a smoother feel

## How I built it

- Language: Java
- Build: Gradle (Kotlin DSL)
- Architecture: MVVM‑style separation between data, domain, and presentation
- Persistence: Room database + SharedPreferences
- UI: AndroidX, Material Components, RecyclerView, ConstraintLayout

Project layout (high level):

- `app/src/main/java/com/example/todo`
  - `data/` – entities, DAOs, database, preferences
  - `domain/` – models and use cases
  - `repository/` – bridges between data layer and use cases
  - `presentation/` – activities, view models, adapters
  - `utils/` – validation, encryption, date helpers, constants
- `app/src/main/res` – layouts, themes, colors, drawables, navigation graph

## How to run it

### Requirements

- Android Studio (Arctic Fox or newer)
- Java 11 JDK
- Android SDK with an emulator or a physical device

### From Android Studio

1. Clone this repository:
   ```bash
   git clone <your-repo-url>.git
   cd ToDoApp
   ```
2. Open the project in Android Studio.
3. Wait for Gradle sync to finish.
4. Run the `app` module on an emulator or device.

### From the command line

```bash
./gradlew assembleDebug
```

Then install the debug APK from:

- `app/build/outputs/apk/debug/`

## Release build

To generate a signed release APK, use in Android Studio:

- **Build > Generate Signed Bundle / APK**

By default the release APK is written to:

- `app/release/app-release.apk`

You can upload that file to a GitHub Release or distribute it by any other channel you prefer.

## Extra notes

- The app is intentionally local‑only; it does not call any remote APIs.
- Passwords are hashed before being stored.
- The UI is tuned for both light and dark modes and uses haptics to make key actions feel more responsive.
