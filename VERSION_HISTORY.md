# Version History

## [Ver 1.12] - 2026-06-06

### Added
- **Choose Bird Screen**:
    - Created `ChooseBirdScreen.java` containing UI grid layout to display eight playable birds: Classic Bird, Red Bird, Blue Bird, Green Bird, Black Bird, Light Blue Bird, Pink Bird, and Yellow Bird.
    - Implemented a tap-to-select interaction where tapping any bird in the grid selects it (removing the redundant "Select" buttons), highlighted with a gold color and a "(Selected)" tag.
    - Displayed a styled `"Tap on Bird to Select"` message at the bottom of the screen utilizing the `fontChillar` font and `Color.WHITE` color to match the design of the in-game `"Tap on Bird to Begin"` indicator.
    - Added "Choose Bird" navigation button on `BBMainMenuScreen.java`.
    - Integrated selected bird persistence utilizing LibGDX `Preferences`.
- **Selection Confirmation Flash Screen**:
    - Created `BirdSelectedSplashScreen.java` showing selection confirmation and playing the chosen bird's flapping animation for 2 seconds before returning to the main menu.
- **Haptic Feedback & Screen Shake**:
    - Added Android VIBRATE permission and integrated 500ms haptic feedback vibration whenever the bird is killed in-game.
    - Added a visual camera shake effect upon death to give a strong haptic/visual impact response to the collision.
- **Dynamic Death Falling Animation**:
    - Refactored death sequences in `PlayClassicLevelScreen.java` and `PlayEasyLevelScreen.java` to remove thread blocking (`Thread.sleep()`) and dynamically animate the bird falling to the ground under gravity.
- **Custom Animation Flexibility**:
    - Added a new constructor in `Animation.java` supporting list-of-frames constructor (`Array<TextureRegion>`), allowing custom single-frame or multi-frame textures to be animated together.
    - Overloaded `Bird.java` constructor to support initialization with a list of textures for more flexible animations.

- **Interactive Control Buttons & Audio Toggle**:
    - Integrated play, mute, and unmute buttons alongside the pause button in classic and easy modes.
    - Added background music support inside gameplay screens with volume control (mute sets volume to 0, unmute sets to 0.03f).
    - Persisted mute selection using Preferences (`isMuted`).
    - Filtered out button areas from tap-to-jump gestures to prevent the bird from jumping when hitting buttons.

### Fixed
- **Pause Button Distortion & Asset Update**:
    - Replaced the low-resolution `pauseButton40.png` asset with the high-resolution `pause.png` from the root `images/` directory (copied to the assets directory as `common/pause.png`).
    - Applied linear texture filtering to the new icon and standardized its rendering and touch-hit bounds to exactly 40x40 to ensure correct sizing.
    - Rounded drawing coordinates to integer boundaries to prevent sub-pixel snapping jitter and pixel distortion during camera movement.

### Changed
- **Selected Bird Sizing & Persistence**:
    - Standardized in-game bounding boxes and rendering bounds for Classic/Red birds to match the standard spritesheet bird width and height.
    - Integrated preference loading into gameplay screens.
    - Updated version name to `1.12` and code to `18` in `android/AndroidManifest.xml` and `GameInfo.java`.

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
