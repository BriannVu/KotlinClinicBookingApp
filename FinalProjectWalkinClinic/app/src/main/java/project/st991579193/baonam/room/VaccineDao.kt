package project.st991579193.baonam.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VaccineDao {
    @Insert
    fun insertAll(vaccine: Vaccine)

    @Delete
    fun delete(vaccine: Vaccine)

    @Query("SELECT * FROM vaccine")
    fun getAll(): Flow<List<Vaccine>>

    @Query("SELECT * from vaccine WHERE uid = :id LIMIT 1")
    fun getVaccineByUser(id: Int): Vaccine
}