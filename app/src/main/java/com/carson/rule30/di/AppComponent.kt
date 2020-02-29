package com.carson.rule30.di

import android.app.Application
import com.carson.rule30.Rule30Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class
])
interface AppComponent {

    fun inject(app: Rule30Application)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}