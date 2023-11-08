package com.technosudo.gymfollower.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.technosudo.gymfollower.data.dao.ExerciseDao
import com.technosudo.gymfollower.data.dao.ProgressDao
import com.technosudo.gymfollower.domain.entity.Exercise

@Database(
    entities = [
        Exercise::class,
        Process::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun progressDao(): ProgressDao

    companion object {
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "translate.app.database"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}