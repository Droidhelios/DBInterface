# DBInterface
Show button with progress bar
  
## Setup

Add this to your project build.gradle
``` gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
#### Normal
version:<b>1.0</b>
```gradle
dependencies {
    implementation 'com.github.Droidhelios:DBInterface:x.y'
}
```

#### AndroidX
[![](https://jitpack.io/v/Droidhelios/DBInterface.svg)](https://jitpack.io/#Droidhelios/DBInterface)
```gradle
dependencies {
    implementation 'com.github.Droidhelios:DBInterface:x.y'
}
```   

In your <b>activity</b> class:
#### Usage method
```java 
      DBManager.with(dbHelper)
                .openDatabaseInterface(context);
```
