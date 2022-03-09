package app.bale.demoapplication.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<V: ViewModel, D: ViewDataBinding>(private val mViewModelClass: Class<V>)
        : AppCompatActivity() {


    @get:LayoutRes
    protected abstract val layoutRes: Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutRes) as D
    }

    val viewModel by lazy {
        ViewModelProviders.of(this)[mViewModelClass]
    }

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */
    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {

        initViewModel(viewModel)

        super.onCreate(savedInstanceState)

        onInject()
    }

    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */
    abstract fun initViewModel(viewModel: V)
}