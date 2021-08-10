package com.example.criminalintent.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.launch
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.criminalintent.ContactResultContract
import com.example.criminalintent.R
import com.example.criminalintent.formatDate
import com.example.criminalintent.model.Crime
import com.example.criminalintent.viewModel.CrimeDetailsViewModel
import java.util.*

private const val CRIME_UUID = "crime_uuid"
private const val DIALOG_DATE = "DialogDate"
private const val DATE_FORMAT = "EEE, MMM, dd"

class CrimeFragment : Fragment() {
    private lateinit var etTitle: EditText
    private lateinit var btnDate: Button
    private lateinit var cbSolved: CheckBox
    private lateinit var btnSuspect: Button
    private lateinit var btnSendReport: Button

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
        btnSuspect = view.findViewById(R.id.btn_suspect)
        btnSendReport = view.findViewById(R.id.btn_send_report)

        btnDate.apply {
            text = formatDate(crime.date)
        }
        btnDate.setOnClickListener {
            DatePickerFragment.newInstance(crime.date).apply {
                show(this@CrimeFragment.parentFragmentManager, DIALOG_DATE)
            }
        }
        btnSendReport.setOnClickListener {
            // 打开短信应用
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getReport())
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.crime_report_suspect, crime.suspect))
            }.also { intent ->
                val chooseIntent = Intent.createChooser(intent, getString(R.string.send_report))
                startActivity(chooseIntent)
            }
        }
        btnSuspect.apply {
            // 处理需要返回结果的activity。startActivityForResult方法已弃用
            val launcher = registerForActivityResult(ContactResultContract()) { uri ->
                if (uri != null) {
                    val field = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
                    val cursor = requireActivity().contentResolver.query(uri, field, null, null, null)
                    cursor?.use {
                        if (it.count == 0) return@use
                        it.moveToFirst()
                        val suspect = it.getString(0)
                        crime.suspect = suspect
                        crimeDetailsViewModel.saveCrime(crime)
                        btnSuspect.text = suspect
                    }
                }
            }
            val contactIntent = Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI)
            setOnClickListener{
                launcher.launch(contactIntent)
            }
            // 检查是否存在可打开的应用
            val activity = requireActivity().packageManager.resolveActivity(contactIntent, PackageManager.MATCH_DEFAULT_ONLY)
            if (activity == null) {
                isEnabled = false
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
        if (crime.suspect.isNotEmpty()) {
            btnSuspect.text = crime.suspect
        }
    }

    /**
     * 获取发送短信的crime内容
     */
    private fun getReport(): String {
        val solvedString = if (crime.isSolved) {
            getString(R.string.crime_report_solved)
        } else {
            getString(R.string.crime_report_unsolved)
        }
        val dateString = DateFormat.format(DATE_FORMAT, crime.date).toString()
        val suspectString = if (crime.suspect.isBlank()) {
            getString(R.string.crime_report_no_suspect)
        } else {
            getString(R.string.crime_report_suspect, crime.suspect)
        }
        return getString(R.string.crime_report, crime.title, dateString, solvedString, suspectString)
    }
}