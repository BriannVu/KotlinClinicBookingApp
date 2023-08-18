package project.st991579193.baonam.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TherapyDao {
    @Insert
    fun insertAll(therapy: Therapy)

    @Delete
    fun delete(therapy: Therapy)

    @Query("SELECT * FROM therapy")
    fun getAll(): Flow<List<Therapy>>

    @Query("SELECT * from therapy WHERE uid = :id LIMIT 1")
    fun getTherapyByUser(id: Int): Therapy
}