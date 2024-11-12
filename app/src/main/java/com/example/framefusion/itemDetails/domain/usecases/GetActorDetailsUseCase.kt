package com.example.framefusion.itemDetails.domain.usecases

import com.example.framefusion.itemDetails.data.local.ActorDetailsDatabase
import com.example.framefusion.itemDetails.data.local.models.ActorDetails
import com.example.framefusion.itemDetails.data.local.models.ActorsMovie
import com.example.framefusion.itemDetails.data.local.models.Profession
import com.example.framefusion.itemDetails.data.local.models.Spouse
import com.example.framefusion.itemDetails.data.service.ItemDetailsService
import javax.inject.Inject

class GetActorDetailsUseCase @Inject constructor(
    private val itemDetailsService: ItemDetailsService,
    private val actorDetailsDatabase: ActorDetailsDatabase
) {
    suspend fun invoke(id: Int): ActorDetails? {
        val response = itemDetailsService.getActorDetails(id)

        if (response.body() == null) {
            return null
        } else {
            val body = response.body()!!

            val actor = ActorDetails(
                id = body.id ?: 0,
                age = body.age,
                birthPlace = body.birthPlace,
                birthday = body.birthday,
                countAwards = body.countAwards,
                createdAt = body.createdAt,
                death = body.death,
                deathPlace = body.deathPlace,
                enName = body.enName,
                facts = body.facts ?: emptyList(),
                growth = body.growth,
                movies = body.movies?.map { movie ->
                    ActorsMovie(
                        id = movie.id,
                        name = movie.name
                    )
                },
                name = body.name,
                photo = body.photo,
                profession = body.profession?.map { profession ->
                    Profession(value = profession.value)
                },
                sex = body.sex,
                spouses = body.spouses?.map { spouse ->
                    Spouse(
                        id = spouse.id,
                        name = spouse.name,
                        divorced = spouse.divorced,
                        children = spouse.children,
                        relation = spouse.relation
                    )
                },
                updatedAt = body.updatedAt
            )


            actorDetailsDatabase.actorDetailsDao().deleteAllActorDetails()
            actorDetailsDatabase.actorDetailsDao().insertOrUpdate(actor)

            return actor
        }
    }
}