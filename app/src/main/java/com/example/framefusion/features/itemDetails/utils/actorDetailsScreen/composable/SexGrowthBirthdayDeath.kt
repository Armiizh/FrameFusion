package com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framefusion.features.itemDetails.data.local.models.Profession
import com.example.framefusion.features.itemDetails.utils.actorDetailsScreen.formatBirthdate

@Composable
fun SexGrowthBirthdayDeath(
    sex: String?,
    growth: Int?,
    birthday: String?,
    death: String?,
    profession: List<Profession>?
) {
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        modifier = Modifier.padding(start = 12.dp),
        text = "Пол: ${sex ?: "пусто"} ",
        fontSize = 14.sp,
        color = Color.White.copy(.5f)
    )
    Text(
        modifier = Modifier.padding(start = 12.dp),
        text = "Рост: ${growth ?: "пусто"} ",
        fontSize = 14.sp,
        color = Color.White.copy(.5f)
    )

    val datOfBirt = when (sex) {
        "Мужской" -> {
            "Родился"
        }

        "Женский" -> {
            "Родилась"
        }

        else -> "Родилось"
    }

    Text(
        modifier = Modifier.padding(start = 12.dp),
        text = "$datOfBirt: ${formatBirthdate(birthday)} ",
        fontSize = 14.sp,
        color = Color.White.copy(.5f)
    )

    val dayOfDeath = when (sex) {
        "Мужской" -> {
            "Умер"
        }

        "Женский" -> {
            "Умерла"
        }

        else -> "Умерло"
    }

    if (!death.isNullOrEmpty()) {
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = "$dayOfDeath: ${formatBirthdate(death)} ",
            fontSize = 14.sp,
            color = Color.White.copy(.5f)
        )
    }
    if (!profession.isNullOrEmpty()) {

        val prof = profession.map { it.value }

        Text(
            modifier = Modifier.padding(start = 12.dp, top = 12.dp),
            text = prof.joinToString(", "),
            fontSize = 14.sp,
            color = Color.White.copy(.5f)
        )
    }
}