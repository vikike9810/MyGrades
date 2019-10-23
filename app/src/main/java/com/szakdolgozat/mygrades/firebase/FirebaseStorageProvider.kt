package com.szakdolgozat.mygrades.firebase

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.szakdolgozat.mygrades.model.User
import java.io.File


object FirebaseStorageProvider {

    var storageReference: StorageReference = FirebaseStorage.getInstance().reference

    fun uploadImage(success:() -> Unit, error:(String?) -> Unit){
        val ref= storageReference.child("Avatars/"+ User.userId+"profil.png")
        val uploadTask= User.avatarPath?.let { ref.putFile(it) }

        uploadTask?.addOnSuccessListener {
            success()
        }
        uploadTask?.addOnFailureListener{
            error(it.message)
        }
    }

    fun downloadImage(success:(file: File) -> Unit, error:() -> Unit){
        val ref= storageReference.child("Avatars/"+ User.userId+"profil.png")
        val localFile = File.createTempFile("images", "png")
        ref.getFile(localFile)
            .addOnSuccessListener {
            success(localFile)
        }
            .addOnFailureListener{
            error()
        }
    }



}