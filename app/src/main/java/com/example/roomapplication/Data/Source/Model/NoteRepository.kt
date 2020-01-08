package com.example.roomapplication.Data.Source.Model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.roomapplication.Data.Source.Model.Database.NoteDatabase
import com.example.roomapplication.Data.Source.Model.Entity.Note
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNoteDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    // initial noteDao
    init {
        val db = NoteDatabase.getDatabase(application)
        mNoteDao = db.noteDao()
    }

    // get all data use live data
    fun getAllNotes(sort : String): DataSource.Factory<Int, Note>{
        val query = SortUtils.getSorteredQuery(sort)
        // use rawquery
        return mNoteDao.getAllNote(query)
    }

    // insert all note
    fun insert(note: Note) {
        executorService.execute {
            mNoteDao.insert(note)
        }
    }

    // delete the note
    fun delete(note: Note) {
        executorService.execute {
            mNoteDao.delete(note)
        }
    }

    // udpate note
    fun update(note: Note) {
        executorService.execute {
            mNoteDao.update(note)
        }
    }
}