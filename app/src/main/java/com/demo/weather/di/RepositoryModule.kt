package com.demo.weather.di

import com.demo.weather.api.UrlInterceptor
import com.demo.weather.api.UrlInterceptorHolder
import com.demo.weather.data.AppDataSource
import com.demo.weather.data.AppRepository
import com.demo.weather.data.DefaultAppDataSource
import com.demo.weather.data.DefaultAppRepository
import com.demo.weather.util.*
import com.demo.weather.util.ENABLE_SSL_PINNING
import com.demo.weather.util.WEATHER_API_BASE_URL
import com.demo.weather.util.WEATHER_API_CERT_PIN
import com.demo.weather.util.WEATHER_API_DOMAIN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    private fun getUrlInterceptor(): Interceptor {
        return UrlInterceptor()
    }

    private fun getCertificatePinner() =
        CertificatePinner.Builder()
            .add(WEATHER_API_DOMAIN, WEATHER_API_CERT_PIN)
            .build()

    @Provides
    @Singleton
    fun provideUrlInterceptorHolder(): UrlInterceptorHolder {
        val urlInterceptor = getUrlInterceptor()
//        if (urlInterceptor is DebugUrlInterceptor) {
//            urlInterceptor.certificatePinner = getCertificatePinner()
//        }
        return UrlInterceptorHolder(urlInterceptor)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(urlInterceptorHolder: UrlInterceptorHolder): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        if (ENABLE_SSL_PINNING) {
            return OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .addInterceptor(urlInterceptorHolder.urlInterceptor)
                .certificatePinner(getCertificatePinner())
                .build()
        } else {
            return OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .addInterceptor(urlInterceptorHolder.urlInterceptor)
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(WEATHER_API_BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideRepository(dataSource: AppDataSource) : AppRepository = DefaultAppRepository(dataSource)

    @Provides
    @Singleton
    fun provideDataSource(retrofit: Retrofit) : AppDataSource = DefaultAppDataSource(retrofit)

}