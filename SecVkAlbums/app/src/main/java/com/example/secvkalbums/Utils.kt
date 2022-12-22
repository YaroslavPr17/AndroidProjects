package com.example.secvkalbums

val ACCESS_TOKEN = "vk1.a.G3_AhHzV_y3P6XVTyOzSbMdU71qyxjrGapYtjA0KKfs5tKUhM9BlQiW_7jc8NZwXmRvBdwnlV0mUAx3PsXYbu0CJ0AiuYSooiCIRyfaJYBum7fV2CGhPvgyyIUBdjzVwo8lYbvwi00zgi0fOT83uad-daDvb-hQfcTrTOyKIHBZMnbziKkd1rx0XDUoLeBbm"
val API_VERSION = "5.131"

fun getRequestUserInfo (userId : String, accessToken: String = ACCESS_TOKEN, apiVersion: String = API_VERSION) : String =
    "https://api.vk.com/method/users.get?user_ids=$userId&fields=bdate&access_token=$accessToken&v=$apiVersion"

fun getRequestUserAlbums (userId :String, need_system :Int, need_covers: Int, accessToken :String = ACCESS_TOKEN, apiVersion :String = API_VERSION) : String =
    "https://api.vk.com/method/photos.getAlbums?owner_id=$userId&need_system=$need_system&need_covers=$need_covers&access_token=$accessToken&v=$apiVersion"

fun getRequestAlbumPhotos (userId :String, album_id :String, accessToken :String = ACCESS_TOKEN, apiVersion :String = API_VERSION) : String =
    "https://api.vk.com/method/photos.get?owner_id=$userId&album_id=$album_id&access_token=$accessToken&v=$apiVersion"