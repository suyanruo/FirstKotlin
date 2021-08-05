package com.example.criminalintent.model

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
