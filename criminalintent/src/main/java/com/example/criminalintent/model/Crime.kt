package com.example.criminalintent.model

import java.util.*

/**
 * Created on 2021/8/3.
 *
 */
data class Crime(val id: UUID = UUID.randomUUID(),
                 var title: String = "",
                 var date: Date = Date(),
                 var isSolved: Boolean = false
)
