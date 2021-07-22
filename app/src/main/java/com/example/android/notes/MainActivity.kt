   package com.example.android.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

   class MainActivity : AppCompatActivity(), NoteAdapter {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NotesAdapter(this,this)
        recyclerView.adapter=adapter
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
            .getInstance(application))
            .get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }


        })

    }

       override fun omItemClicked(note: Note) {
            viewModel.deleteNote(note)
           Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()
       }

       fun submitData(view: View) {
           val input=findViewById<TextView>(R.id.input)
           val noteText =input.text.toString()
           if(noteText.isNotEmpty()) {
               viewModel.insertNote(Note(noteText))
               Toast.makeText(this,"${noteText} Inserted",Toast.LENGTH_LONG).show()
           }
       }
   }