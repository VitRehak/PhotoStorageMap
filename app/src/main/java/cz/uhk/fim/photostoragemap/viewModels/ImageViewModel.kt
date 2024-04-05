package cz.uhk.fim.photostoragemap.viewModels

import android.R.attr
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import cz.uhk.fim.photostoragemap.model.dao.ImageDao
import cz.uhk.fim.photostoragemap.model.entity.ImageModel
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.nio.ByteBuffer


class ImageViewModel(
    private val imageDao: ImageDao,
    private val context: Context,

    ) : BaseViewModel() {

    val images = imageDao.selectAll()
    fun addImage(caption: String, uri: Uri?) {
        val bytes: ByteArray
        val stream: InputStream = context.contentResolver.openInputStream(uri!!)!!
        bytes = stream.readBytes()
        add(bytes, caption)


//            val projections = arrayOf(
//                @Suppress("DEPRECATION")
//                MediaStore.Images.ImageColumns.LATITUDE,
//                @Suppress("DEPRECATION")
//                MediaStore.Images.ImageColumns.LONGITUDE
//            )
//            val metadata = Kim.readMetadata(bytes)
//            val photoMetadata: PhotoMetadata = metadata!!.convertToPhotoMetadata();
//            val latitude = photoMetadata.gpsCoordinates?.latitude
//            val longitude = photoMetadata.gpsCoordinates?.longitude
//            ExifInterface(stream).let { exif ->
//                val latLng = exif.getLatLong(gps)
//                if (latLng) {
//                    println(gps[0])
//                    println(gps[1])
//                }
//            }

    }

    fun addImage(caption: String, bitmap: Bitmap?) {

        val stream = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val bytes = stream.toByteArray()

        add(bytes, caption)
    }


    private fun add(bytes: ByteArray, caption: String) {
        launch {
            imageDao.insertImage(
                imageModel = ImageModel(
                    data = bytes,
                    caption = caption,
                )
            )
        }
    }

    fun deleteImage(image: ImageModel) {
        launch {
            imageDao.deleteImage(image)
        }

    }
}