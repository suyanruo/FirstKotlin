package com.example.firstkotlin.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created on 2021/8/16.
 *
 */
data class GalleryItem(var title: String = "",
                       var id: String = "",
                       @SerializedName("url_s") var url: String = "")
