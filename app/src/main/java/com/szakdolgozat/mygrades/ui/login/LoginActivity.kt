package com.szakdolgozat.mygrades.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.szakdolgozat.mygrades.R
import com.szakdolgozat.mygrades.ui.main.MainActivity
import com.szakdolgozat.mygrades.ui.signup.SignUpFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {
    lateinit var logInPresenter: LoginPresenter
    lateinit var TextEmail: EditText
    lateinit var TextPassword: EditText
    lateinit var signUpFragment: SignUpFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTheme(R.style.AppTheme_NoActionBar)
        logInPresenter= LoginPresenter(this)
        signUpFragment= SignUpFragment()
    }

    fun onClickLogin(v :View){
        if(Login_Email.text.isEmpty()){
            Login_Email.setError("Email is required!")
        }
        else {
            if (Login_Passw.text.isEmpty()) {
                Login_Passw.setError("Password is required!")
            }
            else {
                logInPresenter.userLogin(Login_Email.text.toString(), Login_Passw.text.toString())
            }
        }
    }

    override fun returnFromSignup(){
        supportFragmentManager.beginTransaction().remove(signUpFragment).commit()
    }

    fun onClickSignUp(v :View){
        supportFragmentManager.beginTransaction().add(R.id.Login_Container,signUpFragment,"SignUp").commit()
    }

    override fun logInFailed(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun logInOK() {
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,MainActivity::class.java))
    }
}
