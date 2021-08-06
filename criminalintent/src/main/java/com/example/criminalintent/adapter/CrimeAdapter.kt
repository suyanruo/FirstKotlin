package com.example.criminalintent.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.R
import com.example.criminalintent.formatDate
import com.example.criminalintent.model.Crime
import java.util.*

/**
 * Created on 2021/8/4.
 *
 */
class CrimeAdapter(private val context: Context?, var crimeList: List<Crime>) : RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder>() {
    private var onItemSelectedListener: OnItemSelectedListener? = null

    fun setOnItemSelectedListener(listener: OnItemSelectedListener) {
        onItemSelectedListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_crime_list, parent, false)
        return CrimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        holder.bind(crimeList[position])
    }

    override fun getItemCount(): Int {
        return crimeList.size
    }

    inner class CrimeViewHolder(view : View) : RecyclerView.ViewHolder(view) {
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