package com.szakdolgozat.mygrades.ui.profil

import com.szakdolgozat.mygrades.base.BasePresenter
import com.szakdolgozat.mygrades.database.DatabaseHandler
import com.szakdolgozat.mygrades.events.ImagePickedEvent
import com.szakdolgozat.mygrades.firebase.FirebaseStorageProvider
import com.szakdolgozat.mygrades.util.ImageProvider

class ProfilePresenter(view: ProfileView) : BasePresenter<ProfileView>(view) {

    var imageChanged: Boolean = false

    private val imagePicked = { _: String ->
        imageChanged = true
        view.refreshAvatar()
    }

    init {
        ImagePickedEvent.event += imagePicked
    }


    fun saveProfile() {
        view?.showLoading()
        DatabaseHandler.saveProfil({ saveProfilSucces() }, { message -> saveProfilError(message) })
    }

    fun saveProfilSucces() {
        if (imageChanged) {
            FirebaseStorageProvider.uploadImage(
                { saveOk() }, { message -> view?.showMessage(message ?: "error_picture") }
            )
            imageChanged = false
        } else {
            saveOk()
        }
    }

    fun saveOk() {
        view?.hideLoading()
        view?.dataSaveOK()
    }

    fun saveProfilError(message: String) {
        view?.hideLoading()
        view?.showMessage(message)
    }


    fun changeProfilePicture() {
        ImageProvider.getImageFromGallery(view!!.getFragmentActivity())
    }

}