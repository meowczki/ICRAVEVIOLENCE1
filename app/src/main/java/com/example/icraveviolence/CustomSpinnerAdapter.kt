package com.example.icraveviolence

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomSpinnerAdapter(
    context: Context,
    textViewResourceId: Int,
    val list: List<Product>
) : ArrayAdapter<Product>(
    context,
    textViewResourceId,
    list
) {
    override fun getCount() = list.size

    override fun getItem(position: Int) = list[position]

    fun getItemIdString(position: Int) = list[position].productId

    override fun getItemId(position: Int) = 1L


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return (super.getDropDownView(position, convertView, parent) as TextView).apply {
            text = list[position].name
        }
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return (super.getDropDownView(position, convertView, parent) as TextView).apply {
            text = list[position].name
        }
    }
}