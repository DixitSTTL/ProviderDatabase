package com.app.providerdatabase.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.providerdatabase.databinding.ItemLayoutUsersBinding
import com.app.providerdatabase.pojo.UserModel

class AdapterUser : RecyclerView.Adapter<AdapterUser.ViewHolder>() {
    var dataList = ArrayList<UserModel>()

    inner class ViewHolder(var mBinding: ItemLayoutUsersBinding) :

        RecyclerView.ViewHolder(mBinding.root) {

        fun bind() {
            mBinding.model = dataList[adapterPosition]

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterUser.ViewHolder {
        val mBinding =
            ItemLayoutUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: AdapterUser.ViewHolder, position: Int) {
        holder.bind()

    }

    override fun getItemCount(): Int {
        return dataList.size

    }

    fun setData(updatedList: ArrayList<UserModel>) {
        dataList = updatedList
        notifyDataSetChanged()
    }
}