/**
 * Created by dronpascal on 26.04.2022.
 */
object Retrofit {
        /**
     * @see <a href="https://github.com/square/retrofit">Retrofit Doc</a>
     */
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

    private const val retrofitLoggingInterceptorVersion = "4.9.1"
    const val retrofitLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:$retrofitLoggingInterceptorVersion"

    private const val retrofitConverterSerializationVersion = "0.8.0"
    const val retrofitConverterSerialization =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$retrofitConverterSerializationVersion"
}