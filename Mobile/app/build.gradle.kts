plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.toaudio"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.toaudio"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.media3:media3-session:1.2.0")
    val okHttp_version = "4.8.0"
    val retrofit_version =  "2.9.0"
    val nav_version = "2.7.6"
    val lifecycle_version = "2.1.0"
    val compose_version = "0.1.0-dev02"
    val media3_version = "1.2.0"
    val coil_version = "2.5.0"
    val material3_version = "1.1.2"

    implementation("androidx.core:core-ktx:1.8.10")
    implementation ("androidx.compose.material:material:$compose_version")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // ViewModel and LiveData
    implementation("androidx.compose.runtime:runtime-livedata:$compose_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycle_version")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
    androidTestImplementation("androidx.arch.core:core-testing:$lifecycle_version")

    implementation("androidx.compose:compose-runtime:$compose_version")
    kapt("androidx.compose:compose-compiler:$compose_version")

    //Navigation
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    // Internet:
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation ("com.squareup.okhttp3:okhttp:$okHttp_version")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okHttp_version")

    //ExoPlayer
    implementation ("androidx.media3:media3-exoplayer:$media3_version")
    implementation ("androidx.media3:media3-exoplayer-dash:$media3_version")
    implementation ("androidx.media3:media3-ui:$media3_version")
    implementation ("androidx.media:media:$media3_version")

    //Coil
    implementation("io.coil-kt:coil:$coil_version")
}