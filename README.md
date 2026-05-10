# BriskyBird

BriskyBird is an Android mobile game developed using the [LibGDX](https://libgdx.com/) framework. It features classic 2D gameplay with an animated flying bird, including an interactive main menu, Google Play Services integration, and AdMob support.

## Features

- **Classic 2D Gameplay**: Built with the robust LibGDX cross-platform game development framework.
- **Animated Main Menu**: Features a primary bird alongside an independently bouncing, 8-frame animated secondary bird on the main menu.
- **Google Play Services Integration**: Support for leaderboards and achievements (via Play Services 11.6.0).
- **AdMob Integration**: Support for mobile advertisements.
- **Modern Build System**: Configured with Gradle 8.5 and Android Gradle Plugin 8.3.0, compatible with Java 21.

## Project Structure

This is a multi-project Gradle build. Currently, the active modules are:

- **`core/`**: Contains the main game logic and cross-platform Java code.
- **`android/`**: Contains the Android-specific launcher and build configuration.

*(Note: `desktop`, `ios`, and `html` modules are present in the repository but currently excluded from the build settings to isolate Android development.)*

## Prerequisites

To build and run this project, you will need:

- **Java Development Kit (JDK)**: Java 8 (1.8) or higher (project target compatibility is 1.8).
- **Android Studio**: Recommended for managing the Android SDK and running the project easily.
- **Android SDK**: With required build tools. Ensure your `local.properties` file points to your SDK directory.

## Running the App

For detailed instructions on how to build, install, and run the app on an actual Android device, please refer to the [README_RUN_ON_MOBILE.md](README_RUN_ON_MOBILE.md) guide.

### Quick Start (Command Line)

To build the debug APK via Gradle:

```bash
# On Windows
gradlew.bat android:assembleDebug

# On Mac/Linux
./gradlew android:assembleDebug
```

To install directly to a connected Android device:

```bash
# On Windows
gradlew.bat android:installDebug

# On Mac/Linux
./gradlew android:installDebug
```

## Version History

See the [VERSION_HISTORY.md](VERSION_HISTORY.md) file for a detailed log of updates and changes made to the project.
