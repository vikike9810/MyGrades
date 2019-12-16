package com.szakdolgozat.mygrades.ui.splash

import com.google.firebase.auth.FirebaseAuth
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.firebase.FirebaseFunctionHelper
import com.szakdolgozat.mygrades.firebase.FirebaseStorageProvider
import java.io.File
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.events.DatabaseReadDoneEvent
import com.szakdolgozat.mygrades.internetconnection.InternetConnectionChecker
import com.szakdolgozat.mygrades.model.Person
import com.szakdolgozat.mygrades.model.Student
import com.szakdolgozat.mygrades.model.Teacher
import com.szakdolgozat.mygrades.util.BitmapTransformations
import com.szakdolgozat.mygrades.util.ImageProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SplashPresenter(var view: SplashView) {

    private lateinit var auth: FirebaseAuth

    init {

        DatabaseReadDoneEvent.event+={
            view.splashDone()
        }

    }

    fun loadUser(){
        DatabaseHandler.getDataBase(view.getSplashActivity())
        if(InternetConnectionChecker.internetIsAvailable(view.getSplashActivity() )) {
            auth = FirebaseAuth.getInstance()
            println("VAn net")
            val currentUser = auth.currentUser
            if (currentUser == null) {
                view.loginUser()
            } else {
                User.setUser(currentUser)
                getProfile()
            }
        }
        else{
            println("Nincs net")
            getOfflineDatas()
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
                    if(User.type.equals("Teacher")){
                        User.person=Teacher(User.Name!!, User.userId)
                    }
                    else{
                        User.person= Student(User.Name!!, User.userId)
                    }
                    FirebaseStorageProvider.downloadImage({it, userId->
                        imageDownloaded(it, userId)
                    },{userId -> getDatasFromDatabase(userId)}, User.userId)
                } else {
                    view.downloadError("error in downloading")
                }
            }
    }

    fun getDatasFromDatabase(userId:String){
        DatabaseHandler.getDatas()
    }

      fun  getOfflineDatas(){
        User.type="Student"
        User.person=Student("Offline User", "offline")
        GlobalScope.launch {
        DatabaseHandler.getOfflineDatas()}
    }


    fun imageDownloaded(image: File, userId:String){
        var avatar=ImageProvider.formatImage(image)
        User.avatar=avatar
        getDatasFromDatabase(userId)
    }

}