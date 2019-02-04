# DBInterface
SQLite databases is tough while developing. You cannot view the tables, you don't know what is getting inserted into the tables and you can't update the data and see how your application responds to it.
What if you had a database manager like oracle sql developr, mysql work bench for your application's SQLite database? This library gives you that.
With this library you can manage the database of your android app from the app itself.
You can view, insert, delete, update the tables of your app's SQLite database from your app.

<b>Also Support Android Room Library</b>
  
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

For gatting SupportSQLiteOpenHelper refrence from Room database
#### Usage method
```java 
    public SupportSQLiteOpenHelper getDBHelper() {
        return mRoomDatabase.getOpenHelper();
    }
```

For gatting SQLiteOpenHelper refrence from SQLite database
#### Usage method
```java 
    public SQLiteOpenHelper getDbHelper() {
        return dbHelper;
    }
```
