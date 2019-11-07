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

    fun downloadImage(success:(file: File, userId: String) -> Unit, error:(userId: String) -> Unit, userId: String){
        val ref= storageReference.child("Avatars/"+ userId+"profil.png")
        val localFile = File.createTempFile("images", "png")
        ref.getFile(localFile)
            .addOnSuccessListener {
                if(it.bytesTransferred>10){
            success(localFile, userId)}
                else{
                    error(userId)
                }
        }
            .addOnFailureListener{
            error(userId)
        }
    }



}