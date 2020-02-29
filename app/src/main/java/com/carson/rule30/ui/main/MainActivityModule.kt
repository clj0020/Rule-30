package com.carson.rule30.ui.main

import com.carson.rule30.data.DataManager
import com.carson.rule30.utils.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider): MainViewModel {
        return MainViewModel(
            dataManager,
            schedulerProvider
        )
    }

}