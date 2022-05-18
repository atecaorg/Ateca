package com.ateca.domain.datasource

/**
 * Created by dronpascal on 18.05.2022.
 */
data class BuildConfigProvider(
    val debug: Boolean,
    val appId: String,
    val buildType: String,
    val versionCode: Int,
    val versionName: String,
)