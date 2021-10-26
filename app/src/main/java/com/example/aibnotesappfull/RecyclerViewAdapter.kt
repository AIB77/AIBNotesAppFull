package com.example.aibnotesappfull

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter (private val activity: MainActivity, private val TheNote: ArrayList<NoteModel1>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> (){



    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return  ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pNote=TheNote[position]

        holder.itemView.apply {
            tvResult.text= pNote.note
            deletenote.setOnClickListener {
                activity.deleteNote(pNote.ID)
            }
            updatenote.setOnClickListener {
                activity.raiseDialog(pNote.ID)
            }

        }
    }

    override fun getItemCount() = TheNote.size

}
