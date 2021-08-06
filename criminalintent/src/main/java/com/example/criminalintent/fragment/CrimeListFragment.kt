package com.example.criminalintent.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import java.util.*

/**
 * Created on 2021/8/4.
 *
 */
class CrimeListFragment : Fragment() {
    private lateinit var rvCrimeList: RecyclerView
    private var crimeAdapter: CrimeAdapter? = null
    private var callback: CrimeListCallback? = null

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    }

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as CrimeListCallback
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
                crimes?.let {
                    crimeAdapter = CrimeAdapter(context, crimes)
                    crimeAdapter?.setOnItemSelectedListener(object : CrimeAdapter.OnItemSelectedListener {
                        override fun onItemClicked(uuid: UUID) {
                            if (callback != null) {
                                callback?.onCrimeSelected(uuid)
                            }
                        }

                    })
                    rvCrimeList.adapter = crimeAdapter
                }
            })
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    interface CrimeListCallback {
        fun onCrimeSelected(uuid: UUID)
    }
}