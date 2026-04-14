// 1. WICHTIG: Füge diese Imports ganz oben in der Datei hinzu
import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "at.ac.htlinn.stud.htlinn_flutter_app"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "at.ac.htlinn.stud.stratos2026"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    // 2. Den signingConfigs Block anpassen
    signingConfigs {
        create("release") {
            // WICHTIG: Hier muss jetzt "key.properties" stehen!
            val keyPropertiesFile = rootProject.file("key.properties")
            
            if (keyPropertiesFile.exists()) {
                val properties = Properties()
                properties.load(FileInputStream(keyPropertiesFile))

                // Gradle liest die Werte aus deiner Datei
                storeFile = file(properties.getProperty("storeFile")) // app/upload-keystore.jks
                storePassword = properties.getProperty("storePassword")
                keyAlias = properties.getProperty("keyAlias")
                keyPassword = properties.getProperty("keyPassword")
            } else {
                logger.warn("Warnung: key.properties nicht gefunden. Release-Builds werden nicht signiert.")
            }
        }
    }

    buildTypes {
        getByName("release") {
            // 3. Hier die Signatur verknüpfen
            signingConfig = signingConfigs.getByName("release")
            
            // Standard Flutter-Einstellungen (shrinkResources, minify)
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

flutter {
    source = "../.."
}
