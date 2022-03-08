package app.bale.demoapplication.dependencyinjection.module

import android.app.Application
import android.content.Context
import app.bale.demoapplication.data.ApiConstants
import app.bale.demoapplication.data.repository.DealsRepository
import app.bale.demoapplication.data.repository.RequestInterceptor
import app.bale.demoapplication.data.repository.RetrofitService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
internal class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    internal fun provideGson(): Gson = Gson()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.addInterceptor(RequestInterceptor())
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        okHttpClient.addInterceptor(httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        })
        return okHttpClient.build()
    }

    @Provides
    internal fun provideRetrofitService(okHttpClient: OkHttpClient): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RetrofitService::class.java)
    }

    @Provides
    internal fun provideRepository(retrofitService: RetrofitService): DealsRepository = DealsRepository(retrofitService)
}