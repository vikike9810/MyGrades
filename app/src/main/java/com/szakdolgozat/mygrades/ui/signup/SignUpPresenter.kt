package com.szakdolgozat.mygrades.ui.signup

import android.app.Activity
import com.szakdolgozat.mygrades.base.BaseFragment
import com.szakdolgozat.mygrades.base.BasePresenter
import com.szakdolgozat.mygrades.firebase.FirebaseAuthenticationHelper
import com.szakdolgozat.mygrades.ui.login.LoginPresenter
import com.szakdolgozat.mygrades.firebase.FirebaseFunctionHelper

class SignUpPresenter(view: SignUpView): BasePresenter<SignUpView>(view) {

    var auth = FirebaseAuthenticationHelper.auth

    fun Registration(email: String, password: String, name: String, type : String) {
        view?.showLoading()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(view?.getFragmentActivity() as Activity) { task ->
                if (task.isSuccessful) {
                    FirebaseFunctionHelper.register(name, type, auth.currentUser?.uid)
                        .addOnCompleteListener { result ->

                            if (result.isSuccessful && result.result.equals("OK")) {
                                view?.signUpOk()
                            } else {
                                view?.signUpError(result.exception?.message ?: "error")
                            }
                        }
                } else {
                    view?.signUpError(task.exception?.message ?: "hiba")
                }
             view?.hideLoading()
            }

    }
}