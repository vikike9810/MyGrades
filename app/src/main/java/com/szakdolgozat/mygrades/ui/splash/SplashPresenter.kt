package com.szakdolgozat.mygrades.ui.splash

import com.google.firebase.auth.FirebaseAuth
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.firebase.FirebaseFunctionHelper
import com.szakdolgozat.mygrades.firebase.FirebaseStorageProvider
import java.io.File
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.util.BitmapTransformations
import com.szakdolgozat.mygrades.util.ImageProvider


class SplashPresenter(var view: SplashView) {

    private lateinit var auth: FirebaseAuth

    fun loadUser(){
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if(currentUser==null){
            view.loginUser()
        }
        else{
            User.setUser(currentUser)
            getProfile()
        }
    }

    fun getProfile(){
        FirebaseFunctionHelper.getProfile(auth.currentUser?.uid)
            .addOnCompleteListener { result ->

                if (result.isSuccessful && !(result?.result?.get("name").equals("Error")) ) {

                    User.Name=result.result?.get("name")
                    User.birthday=result.result?.get("birthday")
                    User.address.city=result.result?.get("city")
                    User.address.zip=result.result?.get("zip")
                    User.address.street=result.result?.get("street")
                    User.address.number=result.result?.get("number")
                    User.type=result.result?.get("type")
                    FirebaseStorageProvider.downloadImage({
                        imageDownloaded(it)
                    },{ downloadingDone()})
                } else {
                    view.downloadError("error in downloading")
                }
            }
    }

    fun downloadingDone(){
        DatabaseHandler.getDatas()
        view.splashDone()
    }

    fun imageDownloaded(image: File){
        ImageProvider.formatImage(image)
        downloadingDone()
    }

}