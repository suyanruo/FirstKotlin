package com.example.firstkotlin.api

import com.example.firstkotlin.model.response.FlickrPhotoResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created on 2021/8/16.
 *
 */
interface FlickrApi {

    @GET("services/rest/?method=flickr.interestingness.getList" +
            "&api_key=4f721bbafa75bf6d2cb5af54f937bb70" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s")
    fun fetchPhotos(): Call<FlickrPhotoResponse>
}