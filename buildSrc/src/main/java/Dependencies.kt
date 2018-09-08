import sun.misc.Version

object ApplicationId {
    val application_id = "io.droidplate.wundertest"
}

object Urls {
    val BASE_URL = ""
}

object Releases {
    val version_code = 1
    val version = "1.0"
}

object Modules {
    val domain = ":domain"
    val data = ":data"
}

object Versions {
    val gradle = "3.1.3"

    val versions = "0.17.0"

    val compile_sdk = 28
    val min_sdk = 17
    val target_sdk = 28

    val inject = "1"

    val support = "28.0.0-alpha3"
    val maps = "12.0.0"
    val constrainst_layout = "1.1.2"

    val kotlin = "1.2.51"
    val anko = "0.10.4"
    val timber = "4.7.0"
    val rxandroid = "2.0.2"
    val rxkotlin = "2.2.0"
    val retrofit = "2.4.0"
    val logging_interceptor = "3.10.0"
    val dagger = "2.15"
    val glide = "4.6.1"
    val rxpaper = "1.2.0"
    val moshi = "1.4.0"
    val lifecycle = "1.1.1"
    val room = "1.1.1"
    val rxroom = "1.0.0"

    val junit = "4.12"
    val assertj_core = "3.9.1"
    val mockito_kotlin = "1.5.0"
    val runner = "1.0.2"
    val espresso = "3.0.2"
    val crashlytics = "2.9.3"

    val gsonVersion = "2.8.2"
}

object Libraries {
    val inject = "javax.inject:javax.inject:${Versions.inject}"

    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val anko_commons = "org.jetbrains.anko:anko-commons:${Versions.anko}"

    val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"
    val location = "com.google.android.gms:play-services-location:${Versions.maps}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val lifecycle_extensions = "android.arch.lifecycle:extensions:${Versions.lifecycle}"
    val lifecycle_compiler = "android.arch.lifecycle:compiler:${Versions.lifecycle}"

    val room_runtime = "android.arch.persistence.room:runtime:${Versions.room}"
    val room_complier = "android.arch.persistence.room:compiler:${Versions.room}"
    val rxroom = "android.arch.persistence.room:rxjava2:${Versions.rxroom}"

    val firebase_crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"


}

object SupportLibraries {
    val appcompat_v7 = "com.android.support:appcompat-v7:${Versions.support}"
    val design = "com.android.support:design:${Versions.support}"
    val cardview_v7 = "com.android.support:cardview-v7:${Versions.support}"
    val recyclerview_v7 = "com.android.support:recyclerview-v7:${Versions.support}"
    val constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constrainst_layout}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertj_core = "org.assertj:assertj-core:${Versions.assertj_core}"
    val runner = "com.android.support.test:runner:${Versions.runner}"
    val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    val mockito_kotlin = "com.nhaarman:mockito-kotlin:${Versions.mockito_kotlin}"
    val lifecycle_testing = "android.arch.core:core-testing:${Versions.lifecycle}"
    val room = "android.arch.persistence.room:testing:${Versions.room}"
}

object Config {
    val testRunner = "android.support.test.runner.AndroidJUnitRunner"
}