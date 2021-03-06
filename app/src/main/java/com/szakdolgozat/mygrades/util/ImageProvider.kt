package com.szakdolgozat.mygrades.util

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.ActivityCompat.startActivityForResult
import com.szakdolgozat.mygrades.events.ImagePickedEvent
import com.szakdolgozat.mygrades.model.User
import java.io.File
import java.io.IOException


object ImageProvider{

    val PICK_IMAGE = 1

    fun getImageFromGallery(requestActivity: Activity) {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
       startActivityForResult(requestActivity, intent, PICK_IMAGE,null)
    }

    fun imageSelected(data: Intent?, activity: Activity){
        try {
            val inputStream = activity.contentResolver.openInputStream(data?.data)
            var bitmap = BitmapFactory.decodeStream(inputStream)
            bitmap = BitmapTransformations.cutFromBitmap(bitmap)
            val newWidth = 100F / bitmap.height.toFloat() * bitmap.width.toFloat()
            bitmap = BitmapTransformations.resizeBitmap(bitmap, newWidth, 100F)
            User.avatar=bitmap
            User.avatarPath=data?.data
            ImagePickedEvent.event("OK")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    fun formatImage(image: File): Bitmap{
        var bitmap = BitmapFactory.decodeFile(image.absolutePath)
        bitmap = BitmapTransformations.cutFromBitmap(bitmap)
        val newWidth = 100F / bitmap.height.toFloat() * bitmap.width.toFloat()
        bitmap = BitmapTransformations.resizeBitmap(bitmap, newWidth, 100F)
        return bitmap
    }




}