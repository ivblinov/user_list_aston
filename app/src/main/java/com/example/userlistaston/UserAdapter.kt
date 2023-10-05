package com.example.userlistaston


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userlistaston.databinding.UserListItemBinding

class UserAdapter(private val userList: MutableList<User>, private val userListener: UserListener) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserListItemBinding.inflate(LayoutInflater.from(parent.context))
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position], userListener)
    }
}

class UserViewHolder(private val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: User, userListener: UserListener) {
        val fullName = item.name + " " + item.lastName
        with(binding) {
            name.text = fullName
            photoItem.setImageResource(item.photo)
            itemView.setOnClickListener {
                userListener.onClick(item)
            }
        }
    }
}