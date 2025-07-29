package com.watsidev.producto3.model.network

import com.watsidev.producto3.model.data.music.FeaturedPlaylistsResponse
import com.watsidev.producto3.model.data.music.NewReleasesResponse
import com.watsidev.producto3.model.data.music.SearchResponse
import com.watsidev.producto3.model.data.music.SpotifyAlbumDetail
import com.watsidev.producto3.model.data.music.SpotifyAlbumResponse
import com.watsidev.producto3.model.data.music.SpotifyArtist
import com.watsidev.producto3.model.data.music.SpotifyTokenResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyAuthApi {
    @FormUrlEncoded
    @POST("api/token")
    suspend fun getToken(
        @Header("Authorization") authorization: String,
        @Field("grant_type") grantType: String = "client_credentials"
    ): Response<SpotifyTokenResponse>
}

object SpotifyAuthService {
    private const val BASE_URL = "https://accounts.spotify.com/"

    val api: SpotifyAuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotifyAuthApi::class.java)
    }
}


interface SpotifyApi {
    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("type") type: String = "artist,album,track,playlist",
        @Header("Authorization") token: String
    ): Response<SearchResponse>

    @GET("browse/new-releases")
    suspend fun getNewReleases(
        @Header("Authorization") token: String
    ): Response<NewReleasesResponse>

    @GET("browse/featured-playlists")
    suspend fun getFeaturedPlaylists(
        @Header("Authorization") token: String
    ): Response<FeaturedPlaylistsResponse>

    @GET("artists/{id}")
    suspend fun getArtist(
        @Path("id") artistId: String,
        @Header("Authorization") token: String
    ): Response<SpotifyArtist>

    @GET("artists/{id}/albums")
    suspend fun getArtistAlbums(
        @Path("id") artistId: String,
        @Header("Authorization") token: String
    ): Response<SpotifyAlbumResponse>

    @GET("albums/{id}")
    suspend fun getAlbum(
        @Path("id") albumId: String,
        @Header("Authorization") token: String
    ): Response<SpotifyAlbumDetail>
}

object SpotifyService {
    private const val BASE_URL = "https://api.spotify.com/v1/"

    val api: SpotifyApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpotifyApi::class.java)
    }
}
