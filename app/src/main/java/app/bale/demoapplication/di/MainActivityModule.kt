package app.bale.demoapplication.di

import app.bale.demoapplication.repository.RetrofitService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): RetrofitService {
        return RetrofitService.getInstance()
    }

}