package app.bale.demoapplication.ui.deals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.bale.demoapplication.R
import app.bale.demoapplication.databinding.ItemBinding
import app.bale.demoapplication.extension.strikeThrough
import app.bale.demoapplication.listeners.OnItemClickListener
import app.bale.demoapplication.model.Deal
import com.bumptech.glide.Glide

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    var deals = mutableListOf<Deal>()

    private var listener: OnItemClickListener? = null

    fun setDealsList(deals: List<Deal>) {
        this.deals = deals.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val deal = deals[position]
        holder.binding.txtViewTitle.text = deal.name
        holder.binding.txtViewOriginalAmount.apply {
            text = context?.getString(R.string.amount, deal.original_cost.toString())
            strikeThrough()
        }
        holder.binding.txtViewDiscountedAmount.text = holder.binding.txtViewDiscountedAmount.context.getString(R.string.amount, deal.cost.toString())
        holder.binding.textviewProductBy.text = holder.binding.textviewProductBy.context.getString(R.string.by_provider, deal.provider)
        holder.binding.textviewLikes.text = deal.like_count.toString()
        holder.binding.textviewComments.text = deal.comments_count.toString()
        Glide.with(holder.itemView.context).load(deal.image_url).into(holder.binding.imgViewIcon)

        holder.binding.root.setOnClickListener {
            listener?.onItemClick(deal)
        }

    }

    override fun getItemCount(): Int {
        return deals.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        listener = onItemClickListener
    }
}

class MainViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

}