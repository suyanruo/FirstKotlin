package com.example.firstkotlin.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firstkotlin.api.FlickrApi
import com.example.firstkotlin.model.response.FlickrPhotoResponse
import com.example.firstkotlin.model.response.GalleryItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created on 2021/8/16.
 *
 */
class FlickrManager {
    private var flickrService: FlickrApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        flickrService = retrofit.create(FlickrApi::class.java)
    }

    fun fetchPhotos(): LiveData<List<GalleryItem>> {
        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        val fetchPhotoRequest = flickrService.fetchPhotos()

        fetchPhotoRequest.enqueue(object : Callback<FlickrPhotoResponse> {
            override fun onResponse(call: Call<FlickrPhotoResponse>, response: Response<FlickrPhotoResponse>) {
                val flickrResponse = response.body()
                val photoResponse = flickrResponse?.photos
                val photoList: List<GalleryItem> = photoResponse?.galleryItem ?: mutableListOf()
                photoList.filterNot { galleryItem ->
                    galleryItem.url.isBlank()
                }
                responseLiveData.value = photoList
            }

            override fun onFailure(call: Call<FlickrPhotoResponse>, t: Throwable) {
                Log.e("FlickrManager", "onFailure: $t")
            }
        })

        return responseLiveData
    }
}