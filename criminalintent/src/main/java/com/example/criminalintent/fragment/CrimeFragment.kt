package com.example.criminalintent.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.criminalintent.R
import com.example.criminalintent.formatDate
import com.example.criminalintent.model.Crime
import com.example.criminalintent.viewModel.CrimeDetailsViewModel
import java.util.*

private const val CRIME_UUID = "crime_uuid"
private const val DIALOG_DATE = "DialogDate"

class CrimeFragment : Fragment() {
    private lateinit var etTitle: EditText
    private lateinit var btnDate: Button
    private lateinit var cbSolved: CheckBox

    private lateinit var crime: Crime

    private val crimeDetailsViewModel: CrimeDetailsViewModel by lazy {
        ViewModelProvider(this).get(CrimeDetailsViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance(uuid: UUID) =
            CrimeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CRIME_UUID, uuid)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uuid = arguments?.getSerializable(CRIME_UUID) as UUID
        crimeDetailsViewModel.loadCrime(uuid)
        crime = Crime()

        // 设置跳转到的日期选择fragment的返回监听器
        setFragmentResultListener(REQUEST_KEY_DATE) { requestKey, bundle ->
            if (REQUEST_KEY_DATE == requestKey) {
                crime.date = bundle.getSerializable(BUNDLE_KEY_DATE) as Date
                updateUI()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)
        etTitle = view.findViewById(R.id.et_crime_title)
        btnDate = view.findViewById(R.id.btn_crime_date)
        cbSolved = view.findViewById(R.id.cb_solved)

        btnDate.apply {
            text = formatDate(crime.date)
        }
        btnDate.setOnClickListener {
            DatePickerFragment.newInstance(crime.date).apply {
                show(this@CrimeFragment.parentFragmentManager, DIALOG_DATE)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeDetailsViewModel.crimeLiveData.observe(viewLifecycleOwner, Observer { crime ->
            crime?.let {
                this.crime = crime
                updateUI()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        // 因为设备配置放生变化时会重新执行声明周期函数，而数据存储在viewmodel中，如果在onCreateView中设置监听器，视图恢复后在数据重置时会触发监听器
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        etTitle.addTextChangedListener(textWatcher)

        cbSolved.apply {
            setOnCheckedChangeListener { _, isChecked ->
                crime.isSolved = isChecked
                // 跳过勾选动画
                jumpDrawablesToCurrentState()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        // 保存修改后的数据
        crimeDetailsViewModel.saveCrime(crime)
    }

    private fun updateUI() {
        etTitle.setText(crime.title)
        btnDate.text = formatDate(crime.date)
        cbSolved.isChecked = crime.isSolved
    }
}