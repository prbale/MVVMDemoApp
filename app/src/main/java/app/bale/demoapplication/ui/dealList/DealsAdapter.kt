package app.bale.demoapplication.ui.dealList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.bale.demoapplication.R
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.databinding.ItemBinding
import app.bale.demoapplication.extension.loadImage
import app.bale.demoapplication.extension.strikeThrough
import app.bale.demoapplication.listeners.OnItemClickListener

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    private var deals = mutableListOf<Deal>()

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

        holder.binding.apply {
            txtViewTitle.text = deal.name
            txtViewOriginalAmount.apply {
                text = context?.getString(R.string.amount, deal.original_cost.toString())
                strikeThrough()
            }
            txtViewDiscountedAmount.text = holder.binding.txtViewDiscountedAmount.context.getString(R.string.amount, deal.cost.toString())
            textviewProductBy.text = holder.binding.textviewProductBy.context.getString(R.string.by_provider, deal.provider)
            textviewLikes.text = deal.like_count.toString()
            textviewComments.text = deal.comments_count.toString()
            imgViewIcon.loadImage(deal.image_url)

            root.setOnClickListener {
                listener?.onItemClick(deal)
            }
        }

    }

    override fun getItemCount(): Int = deals.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        listener = onItemClickListener
    }
}

class MainViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)