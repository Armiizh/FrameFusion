package com.example.framefusion.features.search.utils

object FieldsForSearchScreenUseCases {

    object Top10hd {

        val selectedFields = listOf("id", "name", "poster")

        val notNullFields = listOf("id", "poster.url")

        //Актуальный список с сайте кинопоиск выписан вручную для сортивроки данных,
        // принятых от api, который возвращает их в неправильном порядке
        val sortOrder = listOf(
            "Красная Поляна",
            "Ландыши. Такая нежная любовь",
            "Иван Царевич и Серый Волк 6",
            "Сто лет тому вперёд",
            "Граф Монте-Кристо",
            "Три кота. Зимние каникулы",
            "Папины дочки. Новые",
            "Анора",
            "Три кота",
            "Демис и Марина"
        )
    }
}