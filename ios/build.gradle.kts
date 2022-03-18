plugins {
    id("org.jetbrains.gradle.apple.applePlugin") version "212.4638.14-0.15"
//    kotlin("multiplatform")
//    kotlin("native.cocoapods")
}

version = "1.0"
//kotlin {
//    ios()
//    cocoapods {
//        summary = "Kotlin sample project with CocoaPods dependencies"
//        homepage = "https://github.com/Kotlin/kotlin-with-cocoapods-sample"
//        ios.deploymentTarget = "14.1"
//        pod("common") {
//            version = "1.0"
//            source = path(project.file("../common"))
//        }
//    }
//}

apple {
    iosApp {
        productName = "zero"

        // Necessary to have app launch in full screen
        launchStoryboard = ""

        //productInfo["NSAppTransportSecurity"] = mapOf("NSAllowsArbitraryLoads" to true)
        //buildSettings.OTHER_LDFLAGS("")

        dependencies {
            implementation(project(":common"))
        }
    }
}
