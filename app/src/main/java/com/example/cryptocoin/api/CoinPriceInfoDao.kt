package com.example.cryptocoin.api

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptocoin.pojo.CoinPriceInfo
import com.example.cryptocoin.pojo.CoinPriceInfoRawData

@Dao
interface CoinPriceInfoDao {
    //Запрос на заполнение главного экрана
    @Query("SELECT * FROM full_name_list ORDER BY lastupdate DESC")
    fun getPriceList():LiveData<List<CoinPriceInfo>>

    //Запрос на заполнение второстепенного экрана
    @Query("SELECT * FROM full_name_list WHERE fromsymbol == :fsym LIMIT 1 ")
    fun getPriceInfoAboutCoin(fsym:String):LiveData<CoinPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfo>)
}