package com.example.criminalintent

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

/**
 * Created on 2021/8/9.
 * 访问联系人应用
 * ref: https://segmentfault.com/a/1190000037601888
 */
class ContactResultContract : ActivityResultContract<Intent, Uri?>() {
    override fun createIntent(context: Context, input: Intent): Intent {
        return input
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return when {
            resultCode != Activity.RESULT_OK || intent == null -> null
            else -> {
                intent.data
            }
        }
    }
}