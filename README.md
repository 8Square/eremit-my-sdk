# Eremit Sdk (Malaysia)
This is a EremitSdk(Malaysia) sample application. 

Integration Steps
=================

1. Add it in your root build.gradle at the start of repositories
  ```gradle
  buildscript {
    dependencies {
        classpath 'com.google.gms:google-services:4.3.3'
    }
  }
  ```
2. Add it in your root build.gradle at the end of repositories
  ```gradle
  allprojects {
    repositories {
        maven { url "https://github.com/omadahealth/omada-nexus/raw/master/release" }
        maven { url "https://jitpack.io" }
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
3. Add the dependency in build.gradle(app)
```gradle
  dependencies {
   implementation('com.app.mtaeremit:eremitsdk:0.0.1-alpha12') {
       exclude group: 'com.google.zxing'
       exclude group: 'org.apache.commons'
    }
  }
```
4. Apply google service plugin at the end of build.gradle(app)
```gradle
apply plugin: 'com.google.gms.google-services'
```
5. Add supported ndk architecture in build.gradle(app) and sync project
```gradle
  android {
    defaultConfig {
      ndk {
            abiFilters 'armeabi-v7a', 'arm64-v8a'
      }
    }
  }
```

6. Start SDK by
```kotlin
   EremitSdk.Builder()
            .apiKey("api_key_here")
            .envType(EnvType.SIT)
            .build().start(this)
```
7. Extend 'EremitApplication' for project application as below. 
```kotlin
    class YourApplication : EremitApplication() {}
```
EremitApplication is multidex application.


  Parameters
  ----------
  * `apiKey` - Get valid license key from support team
  * `envType`
    1. `EnvType.SIT`
    2. `EnvType.UAT`
    3. `EnvType.PREPROD`
    4. `EnvType.PROD`
  
Notes
=======

1. Migrate to AndroidX, if current application is in support library. 
   Use the link below to migrate.
     	https://developer.android.com/jetpack/androidx/migrate

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