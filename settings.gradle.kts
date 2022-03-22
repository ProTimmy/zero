pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "zero"

include(":common")
include(":components")
include(":models")

include(":android")
include(":ios")
include(":desktop")
include(":web")
