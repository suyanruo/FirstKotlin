package com.example.firstkotlin.modle

import com.example.firstkotlin.R

data class LifeItem(var title: String, var picId: Int) {
    companion object {
        val default: MutableList<LifeItem>
            get() {
                val itemArray = mutableListOf<LifeItem>()
                for (i in 0..39) {
                    itemArray.add(LifeItem("转账", R.mipmap.small_charge))
                }
                return itemArray
            }
    }
}
