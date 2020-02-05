package com.szakdolgozat.mygrades.util

import android.graphics.Bitmap
import android.graphics.Matrix

object BitmapTransformations {

     fun cutFromBitmap(bitmap: Bitmap?): Bitmap? {
        val newBitmap: Bitmap
        if (bitmap?.height!! > bitmap.width) {
            val start = (bitmap.height - bitmap.width) / 2
            newBitmap = Bitmap.createBitmap(bitmap, 0, start, bitmap.width, bitmap.width, null, false)
        } else {
            val start = (bitmap.width - bitmap.height) / 2
            val matrix = Matrix()
            matrix.postRotate(270F)
            newBitmap = Bitmap.createBitmap(bitmap, start, 0, bitmap.height, bitmap.height, matrix, false)
        }
        return newBitmap

    }

     fun resizeBitmap(bm: Bitmap, newWidth: Float, newHeight: Float): Bitmap {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth / width
        val scaleHeight = newHeight / height
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        val resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false)
        bm.recycle()
        return resizedBitmap
    }

}