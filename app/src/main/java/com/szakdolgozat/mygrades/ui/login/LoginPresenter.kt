package com.szakdolgozat.mygrades.ui.login

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.firebase.FirebaseFunctionHelper
import com.szakdolgozat.mygrades.firebase.FirebaseStorageProvider
import com.szakdolgozat.mygrades.util.ImageProvider
import java.io.File

class LoginPresenter(private var view: LoginView) {
    companion object{ var auth = FirebaseAuth.getInstance()}


    fun userLogin(email:String, passw:String){
        auth.signInWithEmailAndPassword(email, passw)
            .addOnCompleteListener(view as Activity) { task ->
            if (task.isSuccessful) {
                auth.currentUser?.let { User.setUser(it) }
                    getProfile()

                } else {
                    view.logInFailed(task.exception?.message)
                }
            }
    }

    fun getProfile(){
        FirebaseFunctionHelper.getProfile(auth.currentUser?.uid)
            .addOnCompleteListener { result ->

                if (result.isSuccessful && !(result?.result?.get("name").equals("Error")) ) {

                    User.Name=result.result?.get("name")
                    User.birthday=result.result?.get("birthday")
                    User.address.city=result.result?.get("city")
                    User.address.zip=Integer.parseInt(result.result?.get("zip"))
                    User.address.street=result.result?.get("street")
                    User.address.number=result.result?.get("number")
                    User.type=result.result?.get("type")
                    FirebaseStorageProvider.downloadImage({
                        imageDownloaded(it)
                    },{ view.logInOK()})
                } else {
                    view.logInFailed(result.exception?.message)
                }
            }
    }

    fun imageDownloaded(image: File){
        ImageProvider.formatImage(image)
        view.logInOK()
    }



}