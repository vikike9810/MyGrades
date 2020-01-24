package com.szakdolgozat.mygrades.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.base.BaseActivity
import com.szakdolgozat.mygrades.ui.main.MainActivity
import com.szakdolgozat.mygrades.ui.signup.SignUpFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter, LoginView>(), LoginView {


    fun onClickLogin(v: View) {
        if (Login_Email.text.isEmpty()) {
            Login_Email.setError("Email is required!")
        } else {
            if (Login_Passw.text.isEmpty()) {
                Login_Passw.setError("Password is required!")
            } else {
                showLoading()
                Butt_Login.isClickable = false
                presenter?.userLogin(Login_Email.text.toString(), Login_Passw.text.toString())
            }
        }
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_login
    }


    override fun returnFromSignup() {
        hideFragment()
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun getProgressBarScreen(): View {
        return progress_bar_screen
    }

    override fun getFragmentContainerId(): Int {
        return R.id.Login_Container
    }

    fun onClickSignUp(v: View) {
        showFragment(SignUpFragment())
    }

    override fun logInFailed(message: String?) {
        hideLoading()
        showMessage(message ?: getString(R.string.login_failed))
        Butt_Login.isClickable = true
    }

    override fun logInOK() {
        hideLoading()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun getContext(): Context {
        return this
    }
}
