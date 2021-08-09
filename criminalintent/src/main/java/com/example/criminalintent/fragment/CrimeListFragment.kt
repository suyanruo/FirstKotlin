package com.example.criminalintent.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.criminalintent.R
import com.example.criminalintent.adapter.CrimeAdapter
import com.example.criminalintent.adapter.CrimeListAdapter
import com.example.criminalintent.model.Crime
import com.example.criminalintent.viewModel.CrimeListViewModel
import java.util.*

/**
 * Created on 2021/8/4.
 *
 */
class CrimeListFragment : Fragment() {
    private lateinit var rvCrimeList: RecyclerView
    private var crimeAdapter: CrimeAdapter? = null
//    private var crimeListAdapter: CrimeListAdapter? = null
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Activity通过FragmentManager通知Fragment处理onCreateOptionsMenu方法
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        rvCrimeList = view.findViewById(R.id.rv_crime_list)
        rvCrimeList.layoutManager = LinearLayoutManager(context)

        // 使用ListAdapter
//        crimeListAdapter = CrimeListAdapter()
//        crimeListAdapter?.setOnItemSelectedListener(object : CrimeListAdapter.OnItemSelectedListener {
//            override fun onItemClicked(uuid: UUID) {
//                if (callback != null) {
//                    callback?.onCrimeSelected(uuid)
//                }
//            }
//        })
//        rvCrimeList.adapter = crimeListAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe(
            viewLifecycleOwner,
            Observer { crimes ->
                crimes?.let {
                    // 使用RecyclerView.Adapter
                    crimeAdapter = CrimeAdapter(context, crimes)
                    crimeAdapter?.setOnItemSelectedListener(object : CrimeAdapter.OnItemSelectedListener {
                        override fun onItemClicked(uuid: UUID) {
                            if (callback != null) {
                                callback?.onCrimeSelected(uuid)
                            }
                        }

                    })
                    rvCrimeList.adapter = crimeAdapter

                    // 使用ListAdapter
//                    crimeListAdapter?.submitList(it)
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_crime_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.new_crime -> {
                val crimeTemp = Crime()
                crimeListViewModel.addCrime(crimeTemp)
                callback?.onCrimeSelected(crimeTemp.id)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    interface CrimeListCallback {
        fun onCrimeSelected(uuid: UUID)
    }
}