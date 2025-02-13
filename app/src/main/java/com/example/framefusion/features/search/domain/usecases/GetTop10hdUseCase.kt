package com.example.framefusion.features.search.domain.usecases

import com.example.framefusion.features.search.data.local.dao.Top10hdDao
import com.example.framefusion.features.search.data.rest.models.toTop10hdItemList
import com.example.framefusion.features.search.data.service.SearchService
import javax.inject.Inject

class GetTop10hdUseCase @Inject constructor(
    private val searchService: SearchService,
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
        val response = searchService.getTop10hd(
            page = 1,
            limit = 10,
            selectedFields = selectedFields,
            notNullFields = notNullFields,
            lists = "top10-hd"
        )

        val top10hd = response.body()!!.toTop10hdItemList()

        val sortOrder = listOf(
            "Триггер",
            "Преступление и наказание",
            "Последний богатырь. Наследие",
            "Молодёжка. Новая смена",
            "Беляковы в отпуске",
            "Противостояние",
            "Маме снова 17",
            "Три кота",
            "Ворон",
            "Дино"
        )

        val orderMap = sortOrder.withIndex().associate { it.value to it.index }
        val sortedTop10hd = top10hd.sortedWith(compareBy { orderMap[it.name] ?: Int.MAX_VALUE })

        top10hdDao.updateSearchItem(sortedTop10hd)
    }
}