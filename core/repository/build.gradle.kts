plugins {
    alias(libs.plugins.jetbrains.kotlin.android) //kotlin
    alias(libs.plugins.ucb.android.library)
    alias(libs.plugins.ksp)
}
android {
    namespace="com.example.repository"
}
dependencies{
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)
    // optional - RxJava2 support for Room
    implementation(libs.androidx.room.rxjava2)
    // optional - RxJava3 support for Room
    implementation(libs.androidx.room.rxjava3)
    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation(libs.androidx.room.guava)
    // optional - Test helpers
    testImplementation(libs.androidx.room.testing)
    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))

}