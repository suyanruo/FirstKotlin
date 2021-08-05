package com.example.criminalintent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.R
import com.example.criminalintent.adapter.CrimeAdapter
import com.example.criminalintent.viewModel.CrimeListViewModel

/**
 * Created on 2021/8/4.
 *
 */
class CrimeListFragment : Fragment() {
    private lateinit var rvCrimeList: RecyclerView
    private var crimeAdapter: CrimeAdapter? = null

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        rvCrimeList = view.findViewById(R.id.rv_crime_list)
        rvCrimeList.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe(
            viewLifecycleOwner,
            Observer { crimes ->
                crimes?.let { if (crimeAdapter != null && crimes == crimeAdapter?.crimeList) {
                    crimeAdapter?.notifyDataSetChanged()
                } else {
                    crimeAdapter = CrimeAdapter(context, crimes)
                    rvCrimeList.adapter = crimeAdapter
                } }
            })
    }
}