plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.gms.google-services")
}

android {
    namespace = "com.example.e_book_libruary_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.e_book_libruary_app"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation ("androidx.activity:activity-compose:1.3.1")
    implementation ("androidx.compose.ui:ui:1.4.0")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.4.0")
    implementation ("androidx.compose.material:material:1.2.0")
    implementation(libs.androidx.material3.android)
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.4.0")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.4.0")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.4.0")

    implementation ("com.google.firebase:firebase-auth-ktx:21.1.0")
    implementation ("com.google.android.gms:play-services-auth:20.4.1")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation ("io.coil-kt:coil-compose:2.2.2")

    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
}