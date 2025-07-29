package com.watsidev.producto3.model.data.music

data class SpotifyTokenResponse(
    val access_token: String,
    val token_type: String,
    val expires_in: Int
)

data class SearchResponse(
    val artists: ArtistsResponse?,
    val albums: AlbumsResponse?,
    val tracks: TracksResponse?,
    val playlists: PlaylistsResponse?
)

data class ArtistsResponse(val items: List<SpotifyArtist>)
data class AlbumsResponse(val items: List<SpotifyAlbum>)
data class TracksResponse(val items: List<SpotifyTrack>)
data class PlaylistsResponse(val items: List<SpotifyPlaylist>)
data class SpotifyAlbum(val id: String, val name: String, val images: List<Image>)
//data class SpotifyTrack(val id: String, val name: String)
data class SpotifyPlaylist(val id: String, val name: String, val images: List<Image>)

data class Image(val url: String, val height: Int?, val width: Int?)
data class NewReleasesResponse(val albums: AlbumsResponse)
data class FeaturedPlaylistsResponse(val playlists: PlaylistsResponse)

data class SpotifyAlbumDetail(
    val id: String,
    val name: String,
    val images: List<Image>,
    val artists: List<SpotifyArtistSimple>,
    val tracks: SpotifyTracks
)

data class SpotifyTracks(
    val items: List<SpotifyTrack>
)

data class SpotifyTrack(
    val id: String,
    val name: String,
    val duration_ms: Int,
    val preview_url: String?
)

data class SpotifyArtistSimple(
    val id: String,
    val name: String
)

data class SpotifyAlbumResponse(
    val items: List<SpotifyAlbum>
)

data class SpotifyArtist(
    val id: String,
    val name: String,
    val images: List<Image> = emptyList(),
    val genres: List<String>? = null,
    val followers: Followers? = null
)

data class Followers(
    val total: Int
)
