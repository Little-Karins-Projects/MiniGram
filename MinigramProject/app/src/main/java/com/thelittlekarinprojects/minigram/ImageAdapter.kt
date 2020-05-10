package com.thelittlekarinprojects.minigram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.image_list_item.view.*

class ImageAdapter(val imageList : ArrayList<Image>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        // Binds the data
        fun bindImage(image : Image){
            // TODO add listener to show more details of the photo
            itemView.image_list_item_0.setImageResource(image.image0)
            //itemView.image_list_item_1.setImageResource(image.image0)
            //itemView.image_list_item_2.setImageResource(image.image0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_list_item, parent, false) // Inflates the data

        return ImageViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageItem = imageList[position]
        holder.bindImage(imageItem)
    }

}