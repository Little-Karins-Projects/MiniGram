package com.thelittlekarinprojects.minigram

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.image_details_list_item.view.*

class ImageDetailsAdapter (val imageDetailsList : ArrayList<ImageDetails>)
    : RecyclerView.Adapter<ImageDetailsAdapter.ImageDetailsViewHolder>() {

    class ImageDetailsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindImageDetails(imageDetails: ImageDetails) {

            itemView.image_details_image.setImageResource(imageDetails.image)
            itemView.image_details_comment.text = imageDetails.comment
            itemView.image_details_user_owner.text = imageDetails.userOwner

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDetailsViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_details_list_item,parent,false)

        return ImageDetailsViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return imageDetailsList.size
    }

    override fun onBindViewHolder(holder: ImageDetailsViewHolder, position: Int) {
        var iDetails = imageDetailsList[position]
        holder.bindImageDetails(iDetails)
    }
}