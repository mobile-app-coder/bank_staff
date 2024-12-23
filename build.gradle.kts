import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "team14"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    //viewmodel
    //implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    //navigation
    //implementation("org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha10")
    val voyagerVersion = "1.1.0-beta02"
    // Multiplatform
    // Navigator
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
    // Screen Model
    implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")
    // BottomSheetNavigator
    implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")
    // TabNavigator
    implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")
    // Transitions
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")
    // Koin integration
    implementation("cafe.adriel.voyager:voyager-koin:$voyagerVersion")
    // Kodein integration
    implementation("cafe.adriel.voyager:voyager-kodein:$voyagerVersion")
    implementation("org.jetbrains.compose.desktop:desktop-jvm:1.6.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    implementation("org.jetbrains.compose.material3:material3-desktop:1.2.1")

    implementation(compose.materialIconsExtended)
    //converter gson
    implementation("com.google.code.gson:gson:2.8.9")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "bank_staff"
            packageVersion = "1.0.0"
        }
    }
}
