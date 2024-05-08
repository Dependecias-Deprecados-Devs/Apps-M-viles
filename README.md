# Apps-Móviles
Repo de las vaians de apps móviles

## PERMISOS

### INTERNET

`<uses-permission android:name="android.permission.INTERNET"/>`

### Agregar otro archivo de aplicacion

```
<application
        android:name=".MyApplication"   <--- si se genera otro archivo al mismo nivel del MyApplication.kt
</application>
```

## IMPLEMENTACIONES

### retrofit

***build.gradle.kts***

`implementation(libs.retrofit)`

***libs.versions.toml***

* [versions]

  `retrofit = "2.11.0"`

* [libraries]

  `retrofit = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }`

### converter-gson

***build.gradle.kts***

`implementation(libs.converter.gson)`

***libs.versions.toml***

* [versions]

* [libraries]

  `converter-gson = { group = "com.squareup.retrofit2", name = "converter-gson", version.ref = "retrofit" }`

### landscapist-glide

***build.gradle.kts***

`implementation(libs.landscapist.glide)`

***libs.versions.toml***

* [versions]

  `landscapistGlide = "2.3.3"`

* [libraries]

  `landscapist-glide = { group = "com.github.skydoves", name = "landscapist-glide", version.ref = "landscapistGlide" }`

### navigation-compose

***build.gradle.kts***

`implementation(libs.androidx.navigation.compose)`

***libs.versions.toml***

* [versions]

  `navigationCompose = "2.7.7"`

* [libraries]

  `androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }`

### room-runtime

***build.gradle.kts***

`implementation(libs.androidx.room.runtime)`

***libs.versions.toml***

* [versions]

  `room = "2.6.1"`

* [libraries]

  `androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }`

### room-compiler

* Etablecer como 'anotationProcessor' en lugar de 'implementation'

***build.gradle.kts***

`annotationProcessor(libs.androidx.room.compiler)`

***libs.versions.toml***


* [versions]

* [libraries]

  `androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }`

  ### persistencia

***build.gradle.kts***

```
plugins {
    id("kotlin-kapt")
}
```

* Debajo de anotationProcessor

  `kapt(libs.androidx.room.compiler)`
