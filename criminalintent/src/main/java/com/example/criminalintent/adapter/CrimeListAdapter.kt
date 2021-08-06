package com.example.criminalintent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.R
import com.example.criminalintent.formatDate
import com.example.criminalintent.model.Crime
import com.example.criminalintent.model.CrimeItemDiff
import java.util.*

/**
 * Created on 2021/8/6.
 * 使用ListAdapter + DiffUtil来更新单个item，提高更新效率
 */
class CrimeListAdapter() : ListAdapter<Crime, CrimeListAdapter.CrimeListViewHolder<Crime>>(CrimeItemDiff) {
    private var onItemSelectedListener: OnItemSelectedListener? = null

    fun setOnItemSelectedListener(listener: OnItemSelectedListener) {
        onItemSelectedListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeListViewHolder<Crime> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crime_list, parent, false)
        return CrimeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CrimeListViewHolder<Crime>, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CrimeListViewHolder<T: Crime>(view: View) : RecyclerView.ViewHolder(view) {
        private var tvCrimeTitle: TextView = view.findViewById(R.id.tv_crime_title)
        private var tvCrimeDate: TextView = view.findViewById(R.id.tv_crime_date)
        private var ivCrimeSolved: ImageView = view.findViewById(R.id.iv_crime_solved)

        private lateinit var crime: Crime

        init {
            itemView.setOnClickListener {
                if (onItemSelectedListener != null) {
                    onItemSelectedListener?.onItemClicked(crime.id)
                }
            }
        }

        fun bind(crime: Crime) {
            this.crime = crime
            tvCrimeTitle.text = crime.title
            tvCrimeDate.text = formatDate(crime.date)
            ivCrimeSolved.visibility = if (crime.isSolved) View.VISIBLE else View.GONE
        }
    }

    interface OnItemSelectedListener {
        fun onItemClicked(uuid: UUID)
    }
}