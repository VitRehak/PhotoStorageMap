package cz.uhk.fim.photostoragemap.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.uhk.fim.photostoragemap.model.entity.ImageModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(imageModel: ImageModel)
    @Query("SELECT * FROM images")
    fun selectAll(): Flow<List<ImageModel>>

    @Delete()
    suspend fun deleteImage(imageModel: ImageModel)
}