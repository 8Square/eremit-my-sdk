# Eremit Sdk (Malaysia)
This is a EremitSdk(Malaysia) sample application. 

Requirements
============

* Minimum Android SDK Version 21
* Working front and back camera in device

Integration Steps
=================

1. Add it in your root build.gradle at the end of repositories
  ```gradle
  allprojects {
    repositories {
        maven {
            url "http://maven.eightsquare.co:8081/artifactory/libs-release-local"
            credentials {
                username = "" //todo: add username 
                password = "" //todo: add password
            }
        }
    }
}
```
2. Add the dependency in build.gradle(app)
```gradle
  dependencies {
   implementation('com.eightsquarei.eremit:eremitsdk:0.0.1-alpha1') {
       exclude group: 'com.google.zxing'
       exclude group: 'org.apache.commons'
    }
  }
```

3. Add supported ndk architecture in build.gradle(app) and sync project
```gradle
  android {
    defaultConfig {
      ndk {
            abiFilters 'armeabi-v7a', 'arm64-v8a'
      }
    }
  }
  ```

4. Integrate firebase in your application, reference: [https://firebase.google.com/docs/android/setup](https://firebase.google.com/docs/android/setup) or follow #5 from below Notes.

5. Start SDK by
```kotlin
   EremitSdk.Builder()
            .apiKey("api_key_here")
            .envType(EnvType.SIT)
            .build().start(this)
```
  **Parameters**  
  * `apiKey` - Get valid license key from support team
  * `envType`
    1. `EnvType.SIT`
    2. `EnvType.UAT`
    3. `EnvType.PREPROD`
    4. `EnvType.PROD`
6. Extend 'EremitApplication' for project application as below. 
```kotlin
    class YourApplication : EremitApplication() {}
```
EremitApplication is multidex application.

  
Notes
=======

1. Migrate to AndroidX, if current application is in support library. 
   Use the link below to migrate.
     [https://developer.android.com/jetpack/androidx/migrate](https://developer.android.com/jetpack/androidx/migrate)

2. Add the code below in project's gradle.properties to enable 
```gradle
  android.useAndroidX=true
  android.enableJetifier=true
```

3. Add below inside android block if Java 8 incompatible error
```gradle
   compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
   }
```
4. If you receive “Manifest merger failed” error during application build, add the code below in application block.
```xml
 tools:replace="android:icon,android:roundIcon,android:name"
```

5. Use below steps to integrate firebase in your application
 ```
 1. Add below inside your project's build.gradle
     buildscript < dependencies
     classpath 'com.google.gms:google-services:4.3.3'

 2. Add below at bottom of your application's build.gradle
     apply plugin: 'com.google.gms.google-services'
     
 3. Add google-services.json in your application module which you will get from firebase console.
 ```
