package com.example.framefusion.search.domain.usecases

import com.example.framefusion.home.data.rest.RestApi
import com.example.framefusion.search.data.local.dao.Top10hdDao
import com.example.framefusion.search.data.rest.models.toTop10hdItemList
import javax.inject.Inject

class GetTop10hdUseCase @Inject constructor(
    private val restApi: RestApi,
    private val top10hdDao: Top10hdDao
){
    suspend fun invoke() {
        val selectedFields = listOf(
            "id",
            "name",
            "poster"
        )
        val notNullFields = listOf(
            "id",
            "poster.url"
        )
        val response = restApi.getTop10hd(
            page = 1,
            limit = 10,
            selectedFields = selectedFields,
            notNullFields = notNullFields,
            lists = "top10-hd"
        )

        val top10hd = response.body()!!.toTop10hdItemList()

        val sortOrder = listOf(
            "Триггер",
            "Пчеловод",
            "Противостояние",
            "Игры",
            "Три кота",
            "Ворон",
            "Дино",
            "Бордерлендс",
            "Маша и Медведь",
            "Луана"
        )

        val orderMap = sortOrder.withIndex().associate { it.value to it.index }
        val sortedTop10hd = top10hd.sortedWith(compareBy { orderMap[it.name] ?: Int.MAX_VALUE })

        top10hdDao.updateSearchItem(sortedTop10hd)
    }
}