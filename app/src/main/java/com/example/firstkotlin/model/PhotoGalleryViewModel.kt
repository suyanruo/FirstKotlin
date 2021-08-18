package com.example.firstkotlin.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.firstkotlin.model.response.GalleryItem
import com.example.firstkotlin.network.FlickrManager

/**
 * Created on 2021/8/16.
 *
 */
class PhotoGalleryViewModel: ViewModel() {
    val galleryItemLiveData: LiveData<List<GalleryItem>>

    init {
        galleryItemLiveData = FlickrManager().fetchPhotos()
    }
}