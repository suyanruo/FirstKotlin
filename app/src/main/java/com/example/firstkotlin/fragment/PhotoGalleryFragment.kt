package com.example.firstkotlin.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.R
import com.example.firstkotlin.adapter.RecyclerCommonAdapter
import com.example.firstkotlin.base.BaseFragment
import com.example.firstkotlin.model.PhotoGalleryViewModel
import com.example.firstkotlin.model.response.GalleryItem

/**
 * Created on 2021/8/16.
 *
 */
class PhotoGalleryFragment : BaseFragment() {
    private lateinit var rvPhoto: RecyclerView
    private lateinit var photoAdapter: RecyclerCommonAdapter<GalleryItem>
    private lateinit var photoGalleryViewModel: PhotoGalleryViewModel

    override fun getLayoutId(): Int = R.layout.fragment_photo_gallery

    override fun init(view: View) {
        rvPhoto = view.findViewById(R.id.rv_photo_gallery)
        rvPhoto.layoutManager = GridLayoutManager(requireActivity(), 3)

        photoGalleryViewModel = ViewModelProvider(requireActivity()).get(PhotoGalleryViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoGalleryViewModel.galleryItemLiveData.observe(viewLifecycleOwner) { photos ->
            photoAdapter = RecyclerCommonAdapter(requireActivity(), R.layout.item_photo_gallery, photos) {
                view, galleryItem ->
                val tvTitle: TextView = view.findViewById(R.id.tv_photo_title)
                tvTitle.text = galleryItem.title
            }
            rvPhoto.adapter = photoAdapter
        }
    }

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }
}