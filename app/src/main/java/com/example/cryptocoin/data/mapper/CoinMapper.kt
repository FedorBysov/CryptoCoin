package com.example.cryptocoin.data.mapper

import com.example.cryptocoin.data.api.model.CoinNameListDto
import com.example.cryptocoin.data.api.model.CoinPriceInfoDto
import com.example.cryptocoin.data.api.model.CoinPriceInfoJsonContainerDto
import com.example.cryptocoin.data.dataBase.CoinInfoDbModel
import com.example.cryptocoin.domain.CoinInfo
import com.google.gson.Gson

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinPriceInfoDto): CoinInfoDbModel {
        return CoinInfoDbModel(
            fromsymbol = dto.fromsymbol,
            tosymbol = dto.tosymbol,
            price = dto.price,
            lastupdate = dto.lastupdate,
            highday = dto.highday,
            lowday = dto.lowday,
            lastmarket = dto.lastmarket,
            imageurl = dto.imageurl
        )
    }

    fun mapJsonContainerToListCoinInfo(jsonContainer: CoinPriceInfoJsonContainerDto): List<CoinPriceInfoDto> {
        val result = mutableListOf<CoinPriceInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (coinKeyAbout in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(coinKeyAbout),
                     CoinPriceInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapNamesListToString(namesListDto: CoinNameListDto): String {
        return namesListDto.names?.map { it.coinName?.name }?.joinToString(",") ?: ""
    }

    fun mapDbModelToEntity(dbModel:CoinInfoDbModel):CoinInfo{
        return CoinInfo(fromsymbol = dbModel.fromsymbol,
            tosymbol = dbModel.tosymbol,
            price = dbModel.price,
            lastupdate = dbModel.lastupdate,
            highday = dbModel.highday,
            lowday = dbModel.lowday,
            lastmarket = dbModel.lastmarket,
            imageurl = dbModel.imageurl)
    }

}