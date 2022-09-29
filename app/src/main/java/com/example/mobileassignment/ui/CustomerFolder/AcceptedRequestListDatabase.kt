package com.example.mobileassignment.ui.CustomerFolder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobileassignment.Entity.AcceptedRequestList

@Database(entities = arrayOf(AcceptedRequestList::class), version = 1, exportSchema = false)
abstract class AcceptedRequestListDatabase: RoomDatabase() {

    abstract fun AcceptedRequestListDAO(): AcceptedRequestListDAO

    companion object{
        //Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE : AcceptedRequestListDatabase? = null

        fun getDatabase(context: Context): AcceptedRequestListDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AcceptedRequestListDatabase::class.java,
                    "AcceptedRequestListDatabase_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}