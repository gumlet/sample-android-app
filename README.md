# Gumlet Video Player - Sample Android App

This is a sample Android application demonstrating the implementation and usage of the **Gumlet Video Player SDK** for Android. This app serves as a reference implementation for clients who want to integrate the Gumlet Video Player into their Android applications.

## Overview

The sample app showcases how to:
- Integrate the Gumlet Video Player SDK
- Play HLS (`.m3u8`) videos
- Play DASH (`.mpd`) videos
- Play DRM-protected videos with Widevine
- Handle player lifecycle events
- Implement error handling and player state monitoring

## Features Demonstrated

✅ **Basic Video Playback** - Simple integration with HLS and DASH streaming  
✅ **DRM Support** - Widevine DRM protected content playback  
✅ **Lifecycle Management** - Proper handling of Android lifecycle events  
✅ **Error Handling** - Player error callbacks and status updates  
✅ **State Monitoring** - Track play/pause state changes  

## Prerequisites

- **Android Studio** (latest version recommended)
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 36 (Android 14/15)
- **Kotlin** support enabled
- **Internet connection** (for streaming videos)

## Project Setup

### 1. Clone or Download the Project

```bash
git clone <repository-url>
cd sample-android-app
```

### 2. Open in Android Studio

1. Open Android Studio
2. Select **File → Open**
3. Navigate to the `sample-android-app` directory
4. Click **OK**

### 3. Sync Gradle

Android Studio will automatically sync the Gradle files. If not:
- Click **File → Sync Project with Gradle Files**
- Wait for the sync to complete

### 4. Build and Run

1. Connect an Android device or start an emulator (API 24+)
2. Click the **Run** button (▶️) or press `Shift + F10`
3. The app will build and launch on your device/emulator

## Project Structure

```
sample-android-app/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/gumlet/myapplication/
│   │       │   └── VideoPlayerActivity.kt    # Main sample activity
│   │       ├── res/
│   │       │   └── layout/
│   │       │       └── activity_video_player.xml  # UI layout
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts                      # App dependencies
├── settings.gradle.kts                        # Repository configuration
└── README.md                                  # This file
```

## Key Implementation Details

### 1. Dependencies Configuration

The app includes the Gumlet Video Player SDK and required Media3 dependencies:

```kotlin
// Gumlet Video Player SDK
implementation("com.gumlet.video:player:1.0.0")

// Required Media3 Dependencies
implementation("androidx.media3:media3-exoplayer:1.5.0")
implementation("androidx.media3:media3-exoplayer-dash:1.5.0")
implementation("androidx.media3:media3-exoplayer-hls:1.5.0")
implementation("androidx.media3:media3-ui:1.5.0")
```

### 2. Manifest Configuration

Required permissions and attribution tag:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<attribution android:tag="gumlet_player" android:label="@string/gumlet_player_attribution_label" />
```

### 3. Basic Video Playback

```kotlin
val params = GumletInitParams.Builder()
    .setVideoUrl("https://example.com/video.m3u8")
    .setAutoPlay(true)
    .build()

playerView.load(params)
```

### 4. DRM Protected Playback

```kotlin
val params = GumletInitParams.Builder()
    .setVideoUrl("https://example.com/protected.mpd")
    .setDrmLicenseUrl("https://license-server.com/widevine")
    .setAutoPlay(true)
    .build()

playerView.load(params)
```

### 5. Lifecycle Management

**⚠️ CRITICAL**: Always forward lifecycle events to prevent memory leaks:

```kotlin
override fun onPause() {
    super.onPause()
    playerView.onPause()
}

override fun onResume() {
    super.onResume()
    playerView.onResume()
}

override fun onDestroy() {
    super.onDestroy()
    playerView.onDestroy()
}
```

### 6. Error Handling and State Monitoring

```kotlin
playerView.setPlayerListener(object : GumletPlayerListener {
    override fun onPlayerError(error: String) {
        Log.e("GumletPlayer", "Playback Error: $error")
        // Handle error
    }

    override fun onPlayerStateChanged(isPlaying: Boolean) {
        // Handle play/pause state changes
    }
})
```

## Using the Sample App

1. **Launch the App**: The `VideoPlayerActivity` opens automatically when the app starts.

2. **Test Different Video Types**:
   - **Play HLS Video**: Click the "Play HLS Video (.m3u8)" button
   - **Play DASH Video**: Click the "Play DASH Video (.mpd)" button
   - **Play DRM Video**: Click the "Play DRM Protected Video" button

3. **Monitor Status**: Watch the status text view at the bottom for player state updates and errors.

4. **Customize**: Replace the sample video URLs in `VideoPlayerActivity.kt` with your own video URLs.

## Customization Guide

### Replace Sample Video URLs

Edit `VideoPlayerActivity.kt` and update the URLs in these methods:

- `playHlsVideo()` - Replace with your HLS video URL
- `playDashVideo()` - Replace with your DASH video URL
- `playDrmVideo()` - Replace with your DRM video URL and license server URL

### Example:

```kotlin
private fun playHlsVideo() {
    val params = GumletInitParams.Builder()
        .setVideoUrl("YOUR_HLS_VIDEO_URL_HERE")
        .setAutoPlay(true)
        .build()
    playerView.load(params)
}
```

## Troubleshooting

### Build Errors

- **Repository not found**: Ensure the Gumlet Maven repository is added in `settings.gradle.kts`
- **Dependency resolution failed**: Check your internet connection and sync Gradle again
- **SDK version mismatch**: Ensure your Android SDK is up to date

### Runtime Issues

- **Video won't play**: 
  - Check internet connection
  - Verify video URL is accessible
  - Check device logs for error messages
- **DRM video fails**: 
  - Verify DRM license server URL is correct
  - Ensure device supports Widevine DRM
  - Check license server accessibility

### Common Issues

- **Memory leaks**: Ensure lifecycle methods (`onPause`, `onResume`, `onDestroy`) are properly implemented
- **Playback stops**: Check if the activity is being destroyed or paused unexpectedly

## Additional Resources

- **Library Documentation**: See the main library README at `/Users/prashantpandey/AndroidStudioProjects/GumletVideoPlayer/README.md`
- **Media3 Documentation**: [AndroidX Media3](https://developer.android.com/media/media3)
- **ExoPlayer Guide**: [ExoPlayer Developer Guide](https://developer.android.com/guide/topics/media/exoplayer)

## Support

For issues or questions about:
- **This sample app**: Check the code comments in `VideoPlayerActivity.kt`
- **Gumlet Video Player SDK**: Refer to the main library documentation
- **Integration help**: Review the implementation examples in this sample app

## License

Copyright © 2025 Gumlet. All rights reserved.

---

**Note**: This is a sample application for demonstration purposes. Replace sample URLs and customize the implementation according to your specific requirements.
