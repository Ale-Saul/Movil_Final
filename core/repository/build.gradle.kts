plugins {
    alias(libs.plugins.jetbrains.kotlin.android) //kotlin
    alias(libs.plugins.ucb.android.library)
}
android {
    namespace="com.example.repository"
}
dependencies{
    implementation(project(":core:data"))
}