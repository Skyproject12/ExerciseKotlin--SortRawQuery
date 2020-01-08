package com.example.roomapplication.ViewModel.Main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.roomapplication.Data.Source.Model.Entity.Note
import com.example.roomapplication.Data.Source.Model.NoteRepository

class MainViewModel(application: Application) : ViewModel() {
    val mNoteRepository: NoteRepository = NoteRepository(application)
    // get All note from noteRepository
    fun getAllNotes(sort : String): LiveData<PagedList<Note>> =
        LivePagedListBuilder(mNoteRepository.getAllNotes(sort), 20).build()

}