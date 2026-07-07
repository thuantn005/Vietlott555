plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.thuan.vietlott535sniper"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.thuan.vietlott535sniper"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "2.0-fixed"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}
dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("org.jsoup:jsoup:1.17.2")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
}