package com.android.blessed.it_books.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.blessed.it_books.R
import com.android.blessed.it_books.category.CategoryActivity
import com.android.blessed.it_books.models.ProgrammingLanguage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class CategoryAdapter(private val context: Context, var data: List<ProgrammingLanguage>?) :
    RecyclerView.Adapter<CategoryAdapter.LangViewHolder>() {
    inner class LangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val langImage: ImageView = itemView.findViewById(R.id.lang_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LangViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.category_recycler_view_item, null)
        return LangViewHolder(view)
    }

    override fun onBindViewHolder(holder: LangViewHolder, position: Int) {
        val langData = data!![position]

        Glide.with(context)
            .asDrawable()
            .load(langData.imageId)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.langImage)

        holder.itemView.setOnClickListener { view ->
            val i = Intent(view.context, CategoryActivity::class.java)
            i.putExtra("NAME", langData.title)
            view.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data!!.size
    }


}