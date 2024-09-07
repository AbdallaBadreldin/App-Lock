package com.jetawy.applock.ui

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jetawy.applock.databinding.ItemPackageBinding

class PackagesAdapter(
    private var data: List<ApplicationInfo> = mutableListOf()
) :
    RecyclerView.Adapter<PackagesAdapter.ViewPagerViewHolder>() {
    lateinit var context: Context

    inner class ViewPagerViewHolder(val binding: ItemPackageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(item: ApplicationInfo, position: Int) {
            val packageManager = context.packageManager
            Glide.with(binding.root.context)
                .load(item.loadIcon(packageManager))
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .into(binding.imageView)

            binding.textView.text = item.loadLabel(packageManager)


            binding.root.setOnClickListener {
//                listener.onClickStickyBanner(position = position)
            }
        }

    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        context = parent.context
        val binding = ItemPackageBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.setData(data[position], position)
    }

    fun setData(data: MutableList<ApplicationInfo>) {
        this.data = data
        notifyDataSetChanged()
    }
}