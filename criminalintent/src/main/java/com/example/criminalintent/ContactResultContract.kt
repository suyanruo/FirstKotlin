package com.example.criminalintent

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import androidx.activity.result.contract.ActivityResultContract

/**
 * Created on 2021/8/9.
 * 访问联系人应用
 * ref: https://segmentfault.com/a/1190000037601888
 */
class ContactResultContract : ActivityResultContract<Unit, Uri?>() {
    override fun createIntent(context: Context, input: Unit?): Intent {
        return Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI)
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