package at.htl.demoapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabasePerson::class], version = 2)
abstract class PersonDatabase : RoomDatabase() {
    abstract val personDao: PersonDao
}

private lateinit var INSTANCE: PersonDatabase

fun getDatabase(context: Context): PersonDatabase {
    synchronized(PersonDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(context.applicationContext, PersonDatabase::class.java, "persons")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}