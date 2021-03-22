package com.briefnoteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(data: ArrayList<Notes>, var clickListener: onItemClickListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    internal var data: List<Notes>
    init {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.show_note_layout, parent, false)

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.showId.text = data[position].showID
        //holder.title.text = data[position].showTitle
        //holder.description.text = data[position].showDescription

        holder.initialize(data.get(position), clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id: TextView
        val title: TextView
        val description: TextView
        val deleteNote: ImageView
        val cardView: CardView

        init {
            id = itemView.findViewById(R.id.id_no)
            title = itemView.findViewById(R.id.show_title)
            description = itemView.findViewById(R.id.show_description)
            deleteNote = itemView.findViewById(R.id.delete)
            cardView = itemView.findViewById(R.id.card_view)
        }

        fun initialize (item: Notes, action: onItemClickListener) {
            id.text = item.showID
            title.text = item.showTitle
            description.text = item.showDescription


            deleteNote.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }

            cardView.setOnClickListener {
                action.onDescriptionClick(item, adapterPosition)

            }


        }


    }

    interface onItemClickListener {
        fun onItemClick(data: Notes, position: Int)
        fun onDescriptionClick(data: Notes, position: Int)
    }

}