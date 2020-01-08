package com.example.roomapplication.Data.Source.Model

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.roomapplication.Data.Source.Model.Entity.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    // pluss datasource .factory to change in pagging
    @RawQuery(observedEntities = [Note::class])
    // change select wirh dao rawquery
    fun getAllNote(query: SupportSQLiteQuery): DataSource.Factory<Int, Note>
}