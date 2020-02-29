package com.carson.rule30.di

import android.app.Application
import android.content.Context
import com.carson.rule30.data.DataManager
import com.carson.rule30.data.DataManagerImpl
import com.carson.rule30.utils.SchedulerProvider
import com.carson.rule30.utils.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDataManager(dataManager: DataManagerImpl): DataManager {
        return dataManager
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProviderImpl()
    }

}