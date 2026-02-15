# Running BriskyBird on Your Android Mobile Device

## Prerequisites

1. **Android Studio** installed on your computer
2. **Android SDK** installed (comes with Android Studio)
3. **USB Cable** to connect your phone
4. **Android phone** with USB debugging enabled

## Setup Steps

### 1. Enable Developer Options on Your Phone

1. Go to **Settings** → **About Phone**
2. Tap on **Build Number** 7 times
3. You'll see a message "You are now a developer!"

### 2. Enable USB Debugging

1. Go to **Settings** → **Developer Options**
2. Enable **USB Debugging**
3. Enable **Install via USB** (if available)

### 3. Update Android SDK Path

Edit the `local.properties` file in the project root and update the path to your Android SDK:

```properties
sdk.dir=C\:/Users/YourName/AppData/Local/Android/Sdk
```

**Common SDK locations:**
- **Windows**: `C:\Users\YourName\AppData\Local\Android\Sdk`
- **Mac**: `/Users/YourName/Library/Android/sdk`
- **Linux**: `/home/YourName/Android/Sdk`

## Running the App

### Method 1: Using Android Studio (Recommended)

1. **Open the project** in Android Studio
   - File → Open → Select the BriskyBird folder

2. **Connect your phone** via USB
   - Accept the USB debugging prompt on your phone
   - Allow the computer to access your device

3. **Select the Android run configuration**
   - In the top toolbar, select "Android" from the run configurations dropdown
   - Or use the configuration I've created for you

4. **Select your device**
   - Click the device dropdown next to the run button
   - Select your connected phone

5. **Click the Run button** (green play icon) or press `Shift+F10`

6. **Wait for build and installation**
   - Gradle will build the APK
   - The app will automatically install and launch on your phone

### Method 2: Using Gradle Command Line

1. **Connect your phone** via USB

2. **Open terminal/command prompt** in the project directory

3. **Run the install command:**
   ```bash
   # On Windows
   gradlew.bat android:installDebug
   
   # On Mac/Linux
   ./gradlew android:installDebug
   ```

4. **Launch the app** manually from your phone's app drawer
   - Look for "Brisky Bird" icon

### Method 3: Build APK and Install Manually

1. **Build the APK:**
   ```bash
   # On Windows
   gradlew.bat android:assembleDebug
   
   # On Mac/Linux
   ./gradlew android:assembleDebug
   ```

2. **Find the APK:**
   - Location: `android/build/outputs/apk/debug/android-debug.apk`

3. **Transfer to phone:**
   - Via USB, email, or cloud storage
   - Install by tapping the APK file on your phone
   - You may need to enable "Install from Unknown Sources"

## Troubleshooting

### Device Not Detected

- Check USB cable (use a data cable, not just charging cable)
- Check USB debugging is enabled
- Try different USB port
- On Windows, install phone drivers
- Run `adb devices` in terminal to check connection

### Build Errors

- Make sure `local.properties` has correct SDK path
- Run `gradlew clean` then try again
- Check Java version compatibility

### App Crashes on Phone

- Check LogCat in Android Studio for error messages
- Ensure your phone meets minimum Android version (API 8+)
- Try uninstalling and reinstalling

## Quick Command Reference

```bash
# Check connected devices
adb devices

# Install and run
gradlew android:installDebug android:run

# Clean build
gradlew clean

# Build release APK (for distribution)
gradlew android:assembleRelease
```

## Notes

- The game requires internet permission for ads and Google Play Services
- Portrait orientation is locked in the app
- First launch may take longer due to asset loading

Enjoy playing BriskyBird! 🐦
