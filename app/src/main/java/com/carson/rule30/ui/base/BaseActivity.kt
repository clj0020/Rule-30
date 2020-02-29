package com.carson.rule30.ui.base

import android.annotation.TargetApi
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection

abstract class BaseActivity<T: ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    var viewDataBinding: T ?= null
        private set
    private var mViewModel: V ?= null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding?.setVariable(bindingVariable, mViewModel)
        viewDataBinding?.executePendingBindings()
    }
}