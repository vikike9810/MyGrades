package com.szakdolgozat.mygrades.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.ui.login.LoginActivity
import com.szakdolgozat.mygrades.ui.main.MainActivity

class SplashActivity : AppCompatActivity(), SplashView {

    lateinit var splashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashPresenter= SplashPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        splashPresenter.loadUser()
    }

    override fun splashDone() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun loginUser() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun downloadError(message: String) {
       Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }


}
