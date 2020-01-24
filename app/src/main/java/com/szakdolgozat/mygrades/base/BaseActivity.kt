package com.szakdolgozat.mygrades.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.szakdolgozat.mygrades.R

abstract class BaseActivity<T: BasePresenter<V>, V: BaseView>: AppCompatActivity(), BaseView {
    protected var presenter: T? =null
    protected var progressBar: View?=null
    protected var actualFragment: Fragment?=null
    protected var fragmentContainerId : Int?=null
    protected var drawerLayout: DrawerLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
        presenter=createPresenter()
        progressBar=getProgressBarScreen()
        fragmentContainerId=getFragmentContainerId()
    }

    abstract fun createPresenter():T
    abstract  fun getProgressBarScreen():View
    abstract fun getFragmentContainerId(): Int
    abstract  fun getContentViewId():Int

    override fun hideLoading() {
        progressBar?.visibility=View.GONE
    }

    override fun showLoading() {
        progressBar?.visibility=View.VISIBLE
    }

    fun showFragment(fragment : Fragment){
        actualFragment=fragment
        fragmentContainerId?.let{
            supportFragmentManager.beginTransaction().replace(it,fragment).commit()
        }
    }

    fun hideFragment(){
        actualFragment?.let { supportFragmentManager.beginTransaction().remove(it).commit() }
        actualFragment=null
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroyView()
    }

}