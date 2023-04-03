val composeVersion = "1.4.0"

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    //id("de.mannodermaus.android-junit5")
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.1")
    }
}

android {
    namespace = "com.app.memo"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.app.memo"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testApplicationId = "com.example.test"
        testHandleProfiling = true
        testFunctionalTest = true
        testInstrumentationRunner = "com.app.memo.TestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            applicationIdSuffix = ".release"
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            isEmbedMicroApp = false
            isProfileable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = ".debug"
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            isEmbedMicroApp = false
            isProfileable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
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
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "META-INF/LICENSE*"
            //excludes += "META-INF/DEPENDENCIES.txt"
            //excludes += "META-INF/NOTICE"
            //excludes += "META-INF/NOTICE.txt"
            //excludes += "META-INF/LICENSE"
            //excludes += "META-INF/LICENSE.txt"
        }
    }

}

dependencies {

    implementation("com.google.ar:core:1.35.0")
    val roomVersion = "2.4.3"
    val junitVersion = "5.9.2"
    val mockitoKotlinVersion = "4.10.0"

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")

    // Junit
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    //androidTestImplementation("com.android.support.test:runner:1.0.2")

    //
    //androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // AppCompat
    implementation("androidx.appcompat:appcompat:1.7.0-alpha02")

    // Compose
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material3:material3:1.1.0-beta01")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")

    // Compose: Testing
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation("androidx.navigation:navigation-testing:2.5.3")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    // Hilt: For UI tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.44.2")
    kaptTest("com.google.dagger:hilt-android-compiler:2.44")

    // Hilt: For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44.2")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")

    // Mockito
    testImplementation("org.mockito:mockito-core:4.10.0")
    testImplementation("org.mockito:mockito-inline:2.21.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")

    // Json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0-RC")

    // Other
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.navigation:navigation-runtime-ktx:2.5.3")
    implementation("androidx.core:core-ktx:1.9.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}