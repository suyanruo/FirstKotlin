package com.example.criminalintent.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.criminalintent.R
import com.example.criminalintent.formatDate
import com.example.criminalintent.model.Crime

class CrimeFragment : Fragment() {
    private lateinit var etTitle: EditText
    private lateinit var btnDate: Button
    private lateinit var cbSolved: CheckBox

    private lateinit var crime: Crime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
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
            isEnabled = false
        }
        return view
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
            setOnCheckedChangeListener { _, isChecked -> crime.isSolved = isChecked }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CrimeFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putString("ARG_PARAM2", param2)
                }
            }
    }
}