package project.st991579193.baonam.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(user: User) : Long

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * from User WHERE email LIKE :email LIMIT 1")
    fun getUserByEmail(email: String): User
}