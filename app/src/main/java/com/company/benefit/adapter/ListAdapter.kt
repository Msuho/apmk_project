package com.company.benefit.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.company.benefit.R
import com.company.benefit.databinding.ItemRecyclerBinding
import com.company.benefit.model.ListInfo

class ListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: ArrayList<ListInfo> = arrayListOf()

    interface OnItemClickListener {
        fun onOptionItemClick(position: Int)
    }

    var listener: OnItemClickListener? = null


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return DataBindingUtil.inflate<ItemRecyclerBinding>(
            LayoutInflater.from(context),
            R.layout.item_recycler,
            parent,
            false
        ).let {
            Log.d("UserAdapter", "binding $it / listener $listener")
            ViewHolder(it, listener)
        }

    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(list.getOrNull(position) ?: return)
    }



    /**
     * Provide a reference to the type of views that you are using
     * 사용 중인 보기 유형에 대한 참조 제공
     * (custom ViewHolder).
     */
    class ViewHolder(private val binding: ItemRecyclerBinding, listener: OnItemClickListener?) : RecyclerView.ViewHolder(binding.root){

        init {

        }

        fun bind(item: ListInfo){

            binding.itemTitle.text = item.title
            binding.itemPoint.text = "${item.point}P"

        }

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(list: ArrayList<ListInfo>) {
        this.list = list
    }




}