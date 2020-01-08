package com.example.roomapplication.Ui.Main

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapplication.Data.Source.Model.Entity.Note
import com.example.roomapplication.R
import com.example.roomapplication.Ui.Insert.InsertActivity
import com.example.roomapplication.Ui.Main.MainListAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_note.view.*

public class MainListAdapter internal constructor(private val activity: Activity) :
    PagedListAdapter<Note, ViewHolder>(Diff_Callback) {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            with(itemView) {
                tv_item_title.text = note.title
                tv_item_date.text = note.date
                tv_item_description.text = note.description
                cv_item_note.setOnClickListener {
                    val intent = Intent(activity, InsertActivity::class.java)
                    intent.putExtra(InsertActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(InsertActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, InsertActivity.REQUEST_UPDATE)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) as Note)
    }

    companion object {
        private val Diff_Callback: DiffUtil.ItemCallback<Note> =
            object : DiffUtil.ItemCallback<Note>() {
                override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                    return oldItem.title == newItem.title && oldItem.description == newItem.description
                }

                override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                    return oldItem == newItem
                }

            }
    }

}