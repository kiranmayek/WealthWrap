package com.example.amex.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amex.R
import com.example.amex.databinding.User2ChatUserItemLayoutBinding
import com.example.amex.models.UserModel

class ChatAdapter(var context: Context, var list:ArrayList<UserModel>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var binding : User2ChatUserItemLayoutBinding = User2ChatUserItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.User2_chat_user_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val user = list[position]
        //set the profile
        //Glide.with(context).load(user.imageUrl).into(holder.binding.userImage)
        holder.binding.userName.text = user.name
        holder.binding.userId.text = user.uid
        holder.binding.userEmail.text = user.email

        //on click
        holder.itemView.setOnClickListener {
            val intent = Intent(context,User2ChatScreenActivity::class.java)
            intent.putExtra("uid",user.uid)
            context.startActivity(intent)
        }

    }
}