package com.admission.testceiba.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.admission.testceiba.databinding.ItemPostBinding
import com.admission.testceiba.domain.model.PostDom

class PostAdapter(
    private val items: List<PostDom>,
): RecyclerView.Adapter<ViewHolderPostItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPostItem {
        val layout = LayoutInflater.from(parent.context)
        val itemBinding = ItemPostBinding.inflate(layout, parent, false)
        return ViewHolderPostItem(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolderPostItem, position: Int) {
        holder.binding(items[position])
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemCount() = items.size
}

class ViewHolderPostItem(
    private val itemBinding: ItemPostBinding
): RecyclerView.ViewHolder(itemBinding.root) {
    fun binding(item: PostDom) {
        with(itemBinding) {
            tvBody.text = item.body
            tvTitle.text = item.title
        }
    }
}