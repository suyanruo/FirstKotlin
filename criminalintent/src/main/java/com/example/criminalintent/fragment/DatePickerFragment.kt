package com.example.criminalintent.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import java.util.*

/**
 * Created on 2021/8/6.
 *
 */
private const val ARG_DATE = "date"
const val REQUEST_KEY_DATE = "RequestKeyDate"
const val BUNDLE_KEY_DATE = "KeyDate"

class DatePickerFragment : DialogFragment() {

    companion object {
        fun newInstance(date: Date): DatePickerFragment {
            return DatePickerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_DATE, date)
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date: Date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // 设置日期选择变动的监听器
        val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val date = GregorianCalendar(year, month, dayOfMonth).time
            // 当日期有变动时返回修改结果给监听日期的fragment
            setFragmentResult(REQUEST_KEY_DATE, bundleOf(BUNDLE_KEY_DATE to date))
        }

        return DatePickerDialog(requireContext(), listener, year, month, day)
    }
}