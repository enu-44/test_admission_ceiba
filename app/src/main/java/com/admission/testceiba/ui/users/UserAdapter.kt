package com.admission.testceiba.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.admission.testceiba.databinding.ItemUserBinding
import com.admission.testceiba.domain.model.UserDom

class UserAdapter(
    private val items: List<UserDom>,
    private val viewPostsCallback: (( user: UserDom) -> Unit)
): RecyclerView.Adapter<ViewHolderUserItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUserItem {
        val layout = LayoutInflater.from(parent.context)
        val itemBinding = ItemUserBinding.inflate(layout, parent, false)
        return ViewHolderUserItem(itemBinding, viewPostsCallback)
    }

    override fun onBindViewHolder(holder: ViewHolderUserItem, position: Int) {
        holder.binding(items[position])
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemCount() = items.size
}

class ViewHolderUserItem(
    private val itemBinding: ItemUserBinding,
    private val editQuantityCallback: (( user: UserDom) -> Unit)
): RecyclerView.ViewHolder(itemBinding.root) {
    fun binding(item: UserDom) {
        with(itemBinding) {
            tvEmail.text = item.email
            tvName.text = item.name
            tvPhone.text = item.phone
            btnViewPosts.setOnClickListener {
                editQuantityCallback.invoke(item)
            }
        }
    }
}