package cz.uhk.fim.photostoragemap.model

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.uhk.fim.photostoragemap.model.dao.ImageDao
import cz.uhk.fim.photostoragemap.model.entity.ImageModel

@Database(
    version = ImageDatabase.Version,
    entities = [ImageModel::class],

)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
    companion object {
        const val Version = 2
        const val Name = "Image-db"
    }
}