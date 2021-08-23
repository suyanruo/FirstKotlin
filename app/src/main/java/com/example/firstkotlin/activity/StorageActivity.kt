package com.example.firstkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.databinding.ActivityStorageBinding
import com.example.firstkotlin.util.FileUtil
import java.io.File

class StorageActivity : AppCompatActivity() {
    lateinit var fileDir: File
    private lateinit var binding: ActivityStorageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)

        val path = getExternalFilesDir(null).toString() + File.separator + "storage"
        fileDir = File(path)

        binding.btnWriteText.setOnClickListener {
            writeText()
        }
        binding.btnReadText.setOnClickListener {
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
        binding.tvContent.text = file.readText()
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
