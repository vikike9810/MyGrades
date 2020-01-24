package com.szakdolgozat.mygrades.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: BasePresenter<V>, V: BaseView>: Fragment(), BaseView  {
    protected var presenter: T?= null
    protected var containerActivity: BaseView?=null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter=createPresenter()
        containerActivity=context as? BaseView
    }

    abstract fun createPresenter(): T

    override fun hideLoading() {
        containerActivity?.hideLoading()
    }

    override fun showLoading() {
        containerActivity?.showLoading()
    }

    override fun showMessage(message: String) {
        containerActivity?.showMessage(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroyView()
    }

}