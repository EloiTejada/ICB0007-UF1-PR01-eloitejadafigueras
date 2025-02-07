package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface RocketDAO {

    @Insert(onConflict = REPLACE)
    suspend fun insert(rocket: List<Rocket>)

    @Query("SELECT * FROM rocket")
    suspend fun getAll(): List<Rocket>

}