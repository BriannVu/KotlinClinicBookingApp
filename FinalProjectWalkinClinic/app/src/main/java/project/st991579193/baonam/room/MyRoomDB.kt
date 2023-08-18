package project.st991579193.baonam.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Vaccine::class, Therapy::class],version = 2, exportSchema = false)

abstract class MyRoomDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun vaccineDao(): VaccineDao
    abstract fun therapyDao(): TherapyDao

    companion object {
        @Volatile
        private var INSTANCE: MyRoomDB? = null

        fun getDatabase(context: Context): MyRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRoomDB::class.java,
                    "clinic"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}