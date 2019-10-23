package com.szakdolgozat.mygrades.ui.profil

import com.szakdolgozat.mygrades.events.ImagePickedEvent
import com.szakdolgozat.mygrades.firebase.FirebaseFunctionHelper
import com.szakdolgozat.mygrades.firebase.FirebaseStorageProvider
import com.szakdolgozat.mygrades.util.ImageProvider

class ProfilePresenter(var view: ProfileView) {
    var imageChanged: Boolean=false

    private val imagePicked = { _: String ->
        imageChanged=true
        view.refreshAvatar()
    }

    init{
        ImagePickedEvent.event += imagePicked
    }



 fun SaveProfile() {
     FirebaseFunctionHelper.saveprofil()
         .addOnCompleteListener { result ->

             if (result.isSuccessful && result.result.equals("OK")) {
                 if(imageChanged) {
                     FirebaseStorageProvider.uploadImage(
                         {view.dataSaveOK() }, { message -> view.dataSaveError(message ?: "error_picture") }
                     )
                     imageChanged=false
                 }
                 else{
                     view.dataSaveOK()
                    }
             } else {
                 view.dataSaveError(result.exception?.message ?: "error_datas")
             }
         }
     }


    fun changeProfilePicture(){
        ImageProvider.getImageFromGallery(view.getFragmentActivity())
    }

}