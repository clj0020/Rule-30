package com.carson.rule30.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.carson.rule30.data.DataManager
import com.carson.rule30.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<N>(val dataManager: DataManager,
                                val schedulerProvider: SchedulerProvider) : ViewModel() {

    val isLoading = ObservableBoolean(false)

    val compositeDisposable: CompositeDisposable

    var navigator: N ?= null

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }
}