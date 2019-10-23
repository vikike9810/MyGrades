package com.szakdolgozat.mygrades.ui.signup

import android.app.Activity
import com.szakdolgozat.mygrades.ui.login.LoginPresenter
import com.szakdolgozat.mygrades.firebase.FirebaseFunctionHelper

class SignUpPresenter(val view: SignUpView) {

    var auth = LoginPresenter.auth

    fun Registration(email: String, password: String, name: String, type : String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(view.getFragmentActivity() as Activity) { task ->
                if (task.isSuccessful) {
                    FirebaseFunctionHelper.register(name, type, auth.currentUser?.uid)
                        .addOnCompleteListener { result ->

                            if (result.isSuccessful && result.result.equals("OK")) {
                                view.signUpOk()
                            } else {
                                view.signUpError(result.exception?.message ?: "hiba")
                            }
                        }
                } else {
                    view.signUpError(task.exception?.message ?: "hiba")
                }

            }
    }
}