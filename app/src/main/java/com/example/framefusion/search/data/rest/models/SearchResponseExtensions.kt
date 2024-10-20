package com.example.framefusion.search.data.rest.models

import com.example.framefusion.home.data.local.models.Genre
import com.example.framefusion.home.data.local.models.Poster
import com.example.framefusion.itemDetails.data.local.models.Rating
import com.example.framefusion.search.data.local.models.SearchItem

fun SearchResponse.toSearchItemList(): List<SearchItem> {
    return docs.map { searchItem ->
        SearchItem(
            id = searchItem.id,
            poster = Poster(
                url = searchItem.poster?.url,
                previewUrl = searchItem.poster?.previewUrl
            ),
            name = searchItem.name,
            description = searchItem.description,
            shortDescription = searchItem.shortDescription,
            year = searchItem.year,
            genres = searchItem.genres?.map { genres ->
                Genre(
                    name = genres.name
                )
            },
            rating = Rating(
                kp = searchItem.rating?.kp,
                imdb = searchItem.rating?.imdb,
                filmCritics = searchItem.rating?.filmCritics,
                russianFilmCritics = searchItem.rating?.russianFilmCritics,
                await = searchItem.rating?.await
            ),
            type = searchItem.type,

//            alternativeName = searchItem.alternativeName,
//            enName = searchItem.enName,

//            movieLength = searchItem.movieLength,
//            isSeries = searchItem.isSeries,
//            ticketsOnSale = searchItem.ticketsOnSale,
//            totalSeriesLength = searchItem.totalSeriesLength,
//            seriesLength = searchItem.seriesLength,
//            ratingMpaa = searchItem.ratingMpaa,
//            ageRating = searchItem.ageRating,
//            top10 = searchItem.top10,
//            top250 = searchItem.top250,
//            typeNumber = searchItem.typeNumber,
//            status = searchItem.status,
//            names = searchItem.names?.map { names ->
//                Name(
//                    name = names.name,
//                    language = names.language,
//                    type = names.type
//                )
//            },
//            externalId = ExternalId(
//                kpHD = searchItem.externalId?.kpHD,
//                imdb = searchItem.externalId?.imdb,
//                tmdb = searchItem.externalId?.tmdb
//            ),
//            logo = Logo(
//                url = searchItem.logo?.url,
//                previewUrl = searchItem.logo?.previewUrl
//            ),
//            poster = Poster(
//                url = searchItem.poster?.url,
//                previewUrl = searchItem.poster?.previewUrl
//            ),
//            backdrop = Backdrop(
//                url = searchItem.backdrop?.url,
//                previewUrl = searchItem.backdrop?.previewUrl
//            ),
//            votes = Votes(
//                kp = searchItem.votes?.kp,
//                imdb = searchItem.votes?.imdb,
//                filmCritics = searchItem.votes?.filmCritics,
//                russianFilmCritics = searchItem.votes?.russianFilmCritics,
//                await = searchItem.votes?.await
//            ),

//            countries = searchItem.countries?.map { countries ->
//                Country(
//                    name = countries.name
//                )
//            },
//            releaseYears = searchItem.releaseYears?.map { releaseYears ->
//                ReleaseYear(
//                    start = releaseYears.start,
//                    end = releaseYears.end
//                )
//            }
        )
    }
}