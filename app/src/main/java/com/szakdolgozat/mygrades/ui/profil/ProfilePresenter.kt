package com.szakdolgozat.mygrades.ui.profil

import com.szakdolgozat.mygrades.database.DatabaseHandler
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
   DatabaseHandler.saveProfil({saveProfilSucces()}, {message -> saveProfilError(message)})
     }

    fun saveProfilSucces(){
        if(imageChanged) {
            FirebaseStorageProvider.uploadImage(
                {view.dataSaveOK() }, { message -> view.dataSaveError(message ?: "error_picture") }
            )
            imageChanged=false
        }
        else{
            view.dataSaveOK()
        }
    }

    fun saveProfilError(message:String){
        view.dataSaveError(message ?: "error_datas")
    }


    fun changeProfilePicture(){
        ImageProvider.getImageFromGallery(view.getFragmentActivity())
    }

}