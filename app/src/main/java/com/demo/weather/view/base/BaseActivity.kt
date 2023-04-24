package com.demo.weather.view.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
//    public val viewModel: BaseViewModel? = null
    protected lateinit var context: Context
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        context = this
        binding = DataBindingUtil.setContentView(this, getContentView())
        binding.lifecycleOwner = this
        binding.executePendingBindings()

//        setupBinding()
//        setupViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * Get ContentView id
     * @return
     */
    @LayoutRes
    protected abstract fun getContentView(): Int

    protected abstract fun setupBinding()

    protected abstract fun setupViewModel()
}
