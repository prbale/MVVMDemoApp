package app.bale.demoapplication.dependencyinjection.module

import android.app.Application
import android.content.Context
import app.bale.demoapplication.repository.DealsRepository
import app.bale.demoapplication.repository.RetrofitService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    internal fun provideGson(): Gson = Gson()

    @Provides
    internal fun provideRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    @Provides
    internal fun provideRepository(retrofitService: RetrofitService): DealsRepository = DealsRepository(retrofitService)

}