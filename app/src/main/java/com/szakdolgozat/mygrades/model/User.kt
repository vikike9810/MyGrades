package com.szakdolgozat.mygrades.model

import android.graphics.Bitmap
import android.net.Uri
import com.google.firebase.auth.FirebaseUser

object User {
    var Name: String? = ""
    var email: String? = ""
    var avatar: Bitmap? = null
    var avatarPath: Uri? = null
    var loggedIn: Boolean = false
    var userId: String = ""
    var address: Address = Address
    var type: UserType = UserType.Offline
    var birthday: String? = ""
    var person: Person? = null


    fun setUser(user: FirebaseUser) {
        userId = user.uid
        email = user.email
        loggedIn = true
    }


    fun LogOut() {
        loggedIn = false
    }

    fun getSubjects(): ArrayList<Subject> {
        return person!!.Subjects
    }

    fun getSubjectList(): ArrayList<String> {
        val subjects = ArrayList<String>()
        subjects.add("")
        if (person?.Subjects != null) {
            for (subject: Subject in person!!.Subjects) {
                subjects.add(subject.Name)
            }
        }
        return subjects
    }

    fun clearUser() {
        Name = ""
        email = ""
        avatar = null
        avatarPath = null
        loggedIn = false
        userId = ""
        address.clearAddres()
        type = UserType.Offline
        birthday = ""
        person = null
    }

}
