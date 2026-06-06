# Version History

## [Ver 1.11] - 2026-02-15

### Added
- **Animated Main Menu Bird**:
    - Added a secondary bird to the main menu that flaps using an 8-frame animation (`frame-1.png` to `frame-8.png`).
    - Implemented `frameBirdAnimation` in `BBMainMenuScreen.java`.
    - Added rendering logic to draw the animated bird alongside the existing bird.
    - Implemented independent movement logic for the animated bird, bouncing off screen edges.
- **Configuration**:
    - Added `ANIMATED_BIRD_WIDTH`, `ANIMATED_BIRD_HEIGHT`, and `ANIMATED_BIRD_SPEED` to `GameInfo.java` for easy customization of the animated bird.

### Changed
- **Gradle Upgrade**:
    - Upgraded Gradle Wrapper to 8.5.
    - Upgraded Android Gradle Plugin to 8.3.0 for Java 21 compatibility.
- **Build Configuration**:
    - Replaced deprecated `compile` dependencies with `implementation` and `api`.
    - Updated `sourceCompatibility` and `targetCompatibility` to 1.8 (Java 8) in `core`, `desktop`, and `android` modules.
    - Added `namespace` declarations to `android` and `BaseGameUtils` modules.
    - Optimized `copyAndroidNatives` task in `android/build.gradle`.
- **Project Structure**:
    - Temporarily isolated Android build by excluding `desktop`, `ios`, and `html` modules from `settings.gradle`.
    - Updated `appVersion` string to "Ver 1.11".

### Fixed
- **Build Errors**:
    - Fixed `java.util.zip.ZipException` by replacing corrupted Gradle wrapper.
    - Fixed `instrumentTest` deprecation error.
    - Fixed `Animation` class compilation error by maintaining compatibility with LibGDX 1.9.2 (removed generics).
