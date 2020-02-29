package com.carson.rule30.di

import com.carson.rule30.ui.main.MainActivity
import com.carson.rule30.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    internal abstract fun bindMainActivity(): MainActivity

}