package com.example.firstkotlin.fragment

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.firstkotlin.R
import com.example.firstkotlin.base.BaseFragment
import com.example.firstkotlin.model.User
import com.google.gson.Gson

/**
 * Created on 2020/6/2.
 *
 */
class PersonFragment: BaseFragment() {
    private lateinit var btnParseJson: Button
    private lateinit var btnParseString: Button
    private lateinit var tvResult: TextView

    private val user: User =
        User("Z", "J", 28, "男")

    override fun getLayoutId(): Int = R.layout.fragment_person

    override fun init(view: View) {
        btnParseJson = view.findViewById(R.id.btn_parse_json)
        btnParseString = view.findViewById(R.id.btn_parse_string)
        tvResult = view.findViewById(R.id.tv_parse_result)

        val userJson = Gson().toJson(user)

        btnParseJson.setOnClickListener {
            tvResult.text = userJson.toString()
        }
        btnParseString.setOnClickListener {
            val user2 = Gson().fromJson(userJson, User::class.java)
            tvResult.text = "name: ${user2.firstName} ${user2.lastName}"
        }
    }

    companion object {
        fun newInstance(): PersonFragment {
            return PersonFragment()
        }
    }
}