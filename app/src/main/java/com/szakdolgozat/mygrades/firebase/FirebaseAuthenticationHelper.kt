package com.szakdolgozat.mygrades.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.model.Student
import com.szakdolgozat.mygrades.model.Teacher
import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.model.UserType
import com.szakdolgozat.mygrades.util.ImageProvider
import java.io.File

object FirebaseAuthenticationHelper {

    var auth = FirebaseAuth.getInstance()
    private var messaging = FirebaseMessaging.getInstance()


    fun subscribe() {
        messaging.subscribeToTopic(User.userId)
    }

    fun unSubscribe() {
        messaging.unsubscribeFromTopic(User.userId)
    }

    fun userLogin(email: String, passw: String, function: (Task<AuthResult>) -> Unit) {
        FirebaseAuthenticationHelper.auth.signInWithEmailAndPassword(email, passw)
            .addOnCompleteListener { task ->
                function(task)
            }
    }

    fun getProfile(error: (String) -> Unit) {
        FirebaseFunctionHelper.getProfile(auth.currentUser?.uid)
            .addOnCompleteListener { result ->
                profileDownloaded(result, error)
            }
    }

    fun logOut() {
        unSubscribe()
        auth.signOut()
    }

    fun profileDownloaded(result: Task<HashMap<String, String>>, error: (String) -> Unit) {
        if (result.isSuccessful && !(result.result?.get("name").equals("Error"))) {

            User.Name = result.result?.get("name")
            User.birthday = result.result?.get("birthday")
            User.address.city = result.result?.get("city")
            User.address.zip = result.result?.get("zip")
            User.address.street = result.result?.get("street")
            User.address.number = result.result?.get("number")

            if (result.result?.get("type").equals("Student")) {
                User.type = UserType.Student
                User.person = Student(User.Name!!, User.userId)
            } else {
                User.type = UserType.Teacher
                User.person = Teacher(User.Name!!, User.userId)
            }

            FirebaseStorageProvider.downloadImage({ it, userId ->
                imageDownloaded(it, userId)
            }, { userId -> loginOk(userId) }, User.userId)
        } else {
            error("Error in downloading datas")
        }
    }

    fun loginOk(userId: String) {
        subscribe()
        DatabaseHandler.getDatas()
    }

    fun imageDownloaded(image: File, userId: String) {
        val avatar = ImageProvider.formatImage(image)
        User.avatar = avatar
        loginOk(userId)
    }
}