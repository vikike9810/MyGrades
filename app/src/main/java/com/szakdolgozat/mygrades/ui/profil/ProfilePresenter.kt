package com.szakdolgozat.mygrades.ui.profil

import com.szakdolgozat.mygrades.model.User
import com.szakdolgozat.mygrades.util.FirebaseFunctionHelper

class ProfilePresenter(var view: ProfileView) {

 fun SaveProfile() {
     FirebaseFunctionHelper.saveprofil()
         .addOnCompleteListener { result ->

             if (result.isSuccessful && result.result.equals("OK")) {
                 view.dataSaveOK()
             } else {
                 view.dataSaveError(result.exception?.message ?: "hiba")
             }
         }
 }

}