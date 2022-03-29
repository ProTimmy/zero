# Setup

## Cloning this project
1. Make sure that you have [git installed](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
2. Inside the directory where you would like the project to reside, type in `git clone git@github.com:ProTimmy/zero.git`

## Android Studio

The first step to running the zero project is to download Android Studio, which can be done so at [https://developer.android.com/studio](https://developer.android.com/studio). Please download the latest *Android Studio Bumblebee* version appropriate for your operating system.

### Plugins

There are some plugins that you will be required to download in order to run the project. To access the plugins, navigate through **Android Studio** -> **Preferences** -> **Plugins**, or go into the `Plugins` tab in the Android Studio opening popup. The required plugins are the following:
* [Compose Multiplatform IDE Support](https://plugins.jetbrains.com/plugin/16541-compose-multiplatform-ide-support) - for working with the desktop project
* [Detekt](https://plugins.jetbrains.com/plugin/10761-detekt) - for code style and linting
* [kdoc-generator](https://plugins.jetbrains.com/plugin/10389-kdoc-generator) - for easy documentation generation
* [Kotlin](https://plugins.jetbrains.com/plugin/6954-kotlin) - should already be installed
* [Kotlin Multiplatform Mobile](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) - key plugin for working with Kotlin Multiplatform
* [SQLDelight](https://plugins.jetbrains.com/plugin/8191-sqldelight) - for working with the database

### Opening the Project

To open the project, open Android Studio, select `Open`, and navigate to this project. Upon first opening the project, Android Studio will download all of the necessary Gradle files needed to run the project. This may take a while, up to 10 mins depending on your machine. Thankfully, Gradle is much faster after first setup. You should see a message on the bottom left of your editor that says `Gradle sync finished` upon completion.

### Creating an Android Emulator

The recommended way to test out the Android project is through an Android phone, as this allows us to use real hardware to test the project and is generally much smoother. However, if a phone is not on hand, you can use the official Android emulator to run the project. You can build one by:
1. Click on the top-right dropdown marked `No Devices` -> `Device Manager`
2. Click on `Create Device`
3. Select the device hardware profile. This can be anything from the Pixel 3 and up.
4. Select the Android OS to install. You can install the latest one at the top, or a specific version.
5. After the OS finishes downloading, click `Next` to review the emulator you are creating.
6. Click `Finish`

### SDK Manager
There are some additional files that need to be downloaded in order to run the project. To do so, please go to `Tools` -> `SDK Manager` and download the following packages:
* 

### Running the Project - Android

### Running the Project - Desktop

## XCode

In order to run the iOS project, you need to have XCode installed. With that in mind, it is required to have a computer running macOS in order to run this project. Once you do, please download the latest version of XCode from the Mac App Store.

### (Optional) Download Homebrew
I'll be referencing Homebrew and `brew install` commands throughout this portion. If you haven't done so already, please go ahead and [download Homebrew here](https://brew.sh/).

### Installing Cocoapods

Before installing Cocoapods, you must install Ruby. The easiest way to do this is by running `brew install ruby`.

To install all of the necessary Cocoapods dependencies, please run: `brew install cocoapods cocoapods-generate`

You don't need to do anything else special to install the Pods, since they should be bundled with this project in Git.

**If you need to install or modify a Pod:**
The Android Studio project should take care of installing all new Pods locally. In case it doesn't, do be sure to run `pod install` inside the `ios` folder.

### Running the Project - iOS
