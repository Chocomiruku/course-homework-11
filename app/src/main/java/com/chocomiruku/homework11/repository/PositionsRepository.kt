package com.chocomiruku.homework11.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.chocomiruku.homework11.database.CompanyDatabase
import com.chocomiruku.homework11.database.entities.asDomainModel
import com.chocomiruku.homework11.domain.Position
import com.chocomiruku.homework11.domain.asDatabaseModel
import com.chocomiruku.homework11.util.getPositionsFromJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PositionsRepository(private val context: Context, private val database: CompanyDatabase) {

    val positions: LiveData<List<Position>> =
        Transformations.map(database.positionDao.getPositions()) {
            it.asDomainModel()
        }

    suspend fun getPositions() {
        if (!wasRefreshed) {
            withContext(Dispatchers.IO) {
                val positions = getPositionsFromJson(context)
                database.positionDao.insertAll(*positions.asDatabaseModel())
            }
            wasRefreshed = true
        }
    }

    companion object {
        var wasRefreshed: Boolean = false
    }
}