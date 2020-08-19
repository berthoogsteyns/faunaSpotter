package com.example.faunaspotv2.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Spot::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun spotDao(): SpotDao

    private class SpotDbCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch { populateDatabase(database.spotDao()) }
            }
        }

        suspend fun populateDatabase(spotDao: SpotDao) {

            val fox = Spot(1, "50.850346", "4.351721", "fox", "mamal", null, null)

            val owl = Spot(2, "50.938635", "5.611177", "owl", "bird", null, null)

            spotDao.insert(fox)
            spotDao.insert(owl)
            Log.i("Db is populated", "")

        }
    }


    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDb {
            Log.i("TAG", "hello")
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "faunaDb"
                ).fallbackToDestructiveMigration().addCallback(SpotDbCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}