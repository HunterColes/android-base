@file:Suppress("UnstableApiUsage")

include(":app")
include(":baseline-profiles")
include(":basic-feature")
include(":portfolio-feature")
include(":settings-feature")
include(":core")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

buildCache {
    local {
        isEnabled = true
        directory = File(rootDir, "build-cache")
    }
}
