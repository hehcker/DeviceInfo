plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.hehcker.deviceinfo.hidden_api"
    compileSdk = 36

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    compileOnly(libs.androidx.annotation)
}