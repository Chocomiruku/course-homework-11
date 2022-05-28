package com.chocomiruku.homework11.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chocomiruku.homework11.database.entities.DatabaseEmployee
import com.chocomiruku.homework11.database.entities.DatabasePosition


@Dao
interface PositionDao {
    @Query("select * from position_table")
    fun getPositions(): LiveData<List<DatabasePosition>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg position: DatabasePosition)
}


@Dao
interface EmployeeDao {
    @Query("select * from employee_table")
    fun getEmployees(): LiveData<List<DatabaseEmployee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg employee: DatabaseEmployee)
}


@Database(entities = [DatabaseEmployee::class, DatabasePosition::class], version = 1)
abstract class CompanyDatabase : RoomDatabase() {
    abstract val positionDao: PositionDao
    abstract val employeeDao: EmployeeDao
}

private lateinit var INSTANCE: CompanyDatabase

fun getDatabase(context: Context): CompanyDatabase {
    synchronized(CompanyDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CompanyDatabase::class.java,
                "company"
            ).build()
        }
    }
    return INSTANCE
}