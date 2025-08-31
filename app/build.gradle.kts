plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.m5"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.m5"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true;
    }
}

dependencies {
    implementation("com.google.mlkit:text-recognition:16.0.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // JSON serialization/deserialization
    implementation("com.google.code.gson:gson:2.10.1")

    // Security crypto for password hashing (recommended)
    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    // ViewBinding property delegate (optional but recommended)
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Lifecycle components (optional)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Coroutines (optional but recommended)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Input validation (optional)
    implementation("commons-validator:commons-validator:1.7")

    implementation ("androidx.security:security-crypto:1.1.0-alpha06")
    implementation ("org.bouncycastle:bcprov-jdk15on:1.70")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")// For stronger encryption

    implementation ("com.google.android.material:material:1.9.0")
}