package com.example.criminalintent.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created on 2021/8/3.
 *
 */
@Entity
data class Crime(@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var date: Date = Date(),
                 var isSolved: Boolean = false
)

object CrimeItemDiff : DiffUtil.ItemCallback<Crime>() {
    override fun areItemsTheSame(oldItem: Crime, newItem: Crime): Boolean {
        return when {
            oldItem is Crime && newItem is Crime ->
                oldItem.id == newItem.id
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: Crime, newItem: Crime): Boolean {
        return when {
            oldItem is Crime && newItem is Crime ->
                oldItem.id == newItem.id &&
                        oldItem.title == newItem.title &&
                        oldItem.date == newItem.date &&
                        oldItem.isSolved == newItem.isSolved
            else -> false
        }
    }

}
