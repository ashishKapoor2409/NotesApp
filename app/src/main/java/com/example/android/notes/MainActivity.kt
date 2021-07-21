   package com.example.android.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

   class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
            .getInstance(application))
            .get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {

        })
    }
}