plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    //  alias(libs.plugins.google.services)
    //  alias(libs.plugins.firebase.crashlytics)
}


android {
    namespace = "com.tech.bbvachallenge"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.tech.bbvachallenge"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "CLIENT_ID", "\"${project.findProperty("CLIENT_ID") ?: ""}\"")
        buildConfigField("String", "CLIENT_SECRET", "\"${project.findProperty("CLIENT_SECRET") ?: ""}\"")

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.process)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.androidx.room.testing)
    //Nav Controller
    implementation(libs.navigation.compose)
    implementation(libs.material.icons.extended)
    //implementation(libs.coil.compose)
    //implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil3.svg)
    implementation(libs.coil.network.okhttp)

    implementation(libs.androidx.core.splashscreen)

    //DataStore
    implementation(libs.datastore.preferences)

    //Dagger-hilt
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.serialization)
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt)
    ksp(libs.androidx.hilt.compiler) // androidX hilt compiler para workers

    implementation(libs.lottie.compose)

    //Glance & WorkManager
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material3)

    implementation(project(":design_system"))
    implementation(project(":core"))
    implementation(project(":domain"))

}