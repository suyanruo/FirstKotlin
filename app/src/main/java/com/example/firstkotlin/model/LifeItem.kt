package com.example.firstkotlin.model

import com.example.firstkotlin.R

data class LifeItem(var title: String, var picId: Int) {
    companion object {
        val default: MutableList<LifeItem>
            get() {
                val itemArray = mutableListOf<LifeItem>()
                for (i in 0..39) {
                    itemArray.add(LifeItem("功能$i", R.mipmap.small_charge))
                }
                return itemArray
            }

        fun getLifeList(size: Int): MutableList<LifeItem> {
            val itemArray = mutableListOf<LifeItem>()
            for (i in 0..size) {
                itemArray.add(LifeItem("功能$i", R.mipmap.small_charge))
            }
            return itemArray
        }
    }
}
