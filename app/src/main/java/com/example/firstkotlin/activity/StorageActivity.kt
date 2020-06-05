package com.example.firstkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.R
import com.example.firstkotlin.util.FileUtil
import kotlinx.android.synthetic.main.activity_storage.*
import java.io.File

class StorageActivity : AppCompatActivity() {
    lateinit var fileDir: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        val path = getExternalFilesDir(null).toString() + File.separator + "storage"
        fileDir = File(path)

        btn_write_text.setOnClickListener {
            writeText()
        }
        btn_read_text.setOnClickListener {
            readText()
        }
    }

    fun writeText() {
        val writeFile = File(fileDir.toString() + File.separator + "test.txt")

        if (!fileDir.exists()) {
            fileDir.mkdirs()
            writeFile.createNewFile()
            writeFile.writeText("Hello kotlin!")
        } else {
            writeFile.appendText("\nSuch is life")
        }
    }

    fun readText() {
        val file = File(fileDir.toString() + File.separator + "test.txt")

        if (file == null || !file.exists()) {
            return
        }
        tv_content.text = file.readText()
    }

    override fun onPause() {
        super.onPause()
        FileUtil.deleteFile(this, fileDir)
    }

    private fun searchFile() {
        var fileNames = mutableListOf<String>()
        val fileTree: FileTreeWalk = fileDir.walk()
        fileTree.maxDepth(1)
            .filter { it.isFile }
            .filter { it.extension in listOf("png", "jpg", "jpeg") }
            .forEach { fileNames.add(it.name) }
    }
}
