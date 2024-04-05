package cz.uhk.fim.photostoragemap.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val data: ByteArray,
    val caption: String = "",
)
