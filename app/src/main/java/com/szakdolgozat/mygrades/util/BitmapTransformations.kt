package com.szakdolgozat.mygrades.util

import android.graphics.Bitmap
import android.graphics.Matrix

object BitmapTransformations {

     fun cutFromBitmap(bitmap: Bitmap?): Bitmap? {
        var newBitmap: Bitmap
        if (bitmap?.height!! > bitmap.width!!) {
            var start = (bitmap.height - bitmap.width) / 2
            newBitmap = Bitmap.createBitmap(bitmap, 0, start, bitmap.width, bitmap.width, null, false)
        } else {
            var start = (bitmap.width - bitmap.height) / 2
            var matrix = Matrix()
            matrix.postRotate(270F)
            newBitmap = Bitmap.createBitmap(bitmap, start, 0, bitmap.height, bitmap.height, matrix, false)
        }
        return newBitmap

    }

     fun resizeBitmap(bm: Bitmap, newWidth: Float, newHeight: Float): Bitmap {
        var width = bm.width
        var height = bm.height
        var scaleWidth = newWidth / width
        var scaleHeight = newHeight / height
        var matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        var resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false)
        bm.recycle()
        return resizedBitmap
    }

}