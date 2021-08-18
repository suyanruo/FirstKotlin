package com.example.firstkotlin.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created on 2021/8/16.
 *
 */
class FlickrPhotoResponse {
    lateinit var photos: PhotoResponse

    inner class PhotoResponse {
        @SerializedName("photo")
        lateinit var galleryItem: List<GalleryItem>
    }
}