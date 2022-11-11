package com.example.cryptocoin.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinInfoDao {
    //Запрос на заполнение главного экрана
    @Query("SELECT * FROM full_name_list ORDER BY lastupdate DESC")
    fun getPriceList(): LiveData<List<CoinInfoDbModel>>

    //Запрос на заполнение второстепенного экрана
    @Query("SELECT * FROM full_name_list WHERE fromsymbol == :fsym LIMIT 1 ")
    fun getPriceInfoAboutCoin(fsym: String): LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList: List<CoinInfoDbModel>)
}