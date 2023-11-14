package com.hub.offershub.utils.compress

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import kotlin.math.min

object CompressImage {
    /**
     * This doesn't compress the original image file.
     * It compresses the bitmap and updates it to the new file and returns from app cache
     */
    @Throws(Exception::class)
    fun compressBitmap(context: Context, originalImageFile: File, cb: ((File) -> Unit)? = null) {
        val bitmap = updateDecodeBounds(originalImageFile)
        val file = context.getPicturesFile(originalImageFile.name)
        val fOut = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut)
        fOut.flush() // Not really required
        fOut.close() // do not forget to close the stream
        bitmap.recycle() //recycle the bitmap
        cb?.invoke(file)
    }

    fun Context.getPicturesFile(fileName: String, subDirectory: String = ""): File {
        return File(this.cacheDir.absolutePath.plus("/$subDirectory"), fileName)
    }

    /**
     * This compress the original file.
     */
    @Throws(Exception::class)
    fun compressCurrentBitmapFile(originalImageFile: File) : Boolean {
        try {
            val bitmap = updateDecodeBounds(originalImageFile)
            val fOut = FileOutputStream(originalImageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut)
            fOut.flush() // Not really required
            fOut.close() // do not forget to close the stream
            bitmap.recycle() //recycle the bitmap
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return false
        }

        return true

    }

    /**
     * Measure decodeBounds of the bitmap from given File.
     */
    private fun updateDecodeBounds(imageFile: File): Bitmap {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(imageFile.absolutePath, this)
            val sampleHeight = if (outWidth > outHeight) 900 else 1100
            val sampleWidth = if (outWidth > outHeight) 1100 else 900
            /**
             * You can tweak the sizes 900, 1100.
             * The bigger the number is, the more details you can keep.
             * The lesser, the lesser quality of details.
             */
            inSampleSize = min(outWidth / sampleWidth, outHeight / sampleHeight)
            inJustDecodeBounds = false
            BitmapFactory.decodeFile(imageFile.absolutePath, this)
        }
    }
}