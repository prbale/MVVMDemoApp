package app.bale.demoapplication.ui.dealDetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.bale.demoapplication.R
import app.bale.demoapplication.databinding.FragmentDealDetailsBinding
import app.bale.demoapplication.extension.gone
import app.bale.demoapplication.extension.strikeThrough
import app.bale.demoapplication.extension.visible
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.dependencyinjection.module.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DealDetailsFragment : Fragment() {

    lateinit var dealDetailsViewModel: DealDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var binding: FragmentDealDetailsBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.actionBar?.title = "Deal Details"
        activity?.title = "Deal Details"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.supportFragmentManager?.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dealDetailsViewModel = ViewModelProvider(this, viewModelFactory)[DealDetailsViewModel::class.java]

        binding = FragmentDealDetailsBinding.inflate(inflater)
        val root: View = binding!!.root

        val deal: Deal = arguments?.getParcelable<Deal>("DEAL") as Deal
        displayDealDetails(deal)

        return root
    }

    private fun displayDealDetails(deal: Deal) {

        binding?.let { binding ->
            context?.let { Glide.with(it).load(deal.image_url).into(binding.productImage) }
            binding.textviewComments.text = deal.comments_count.toString()
            binding.textviewLikes.text = deal.like_count.toString()
            binding.textviewName.text = deal.name
            binding.textviewDescription.text = deal.description
            binding.textviewProductBy.text = context?.getString(R.string.by_provider, deal.provider)
            binding.txtViewOriginalAmount.apply {
                text = context.getString(R.string.amount, deal.original_cost.toString())
                strikeThrough()
            }

            binding.txtViewDiscountedAmount.text = context?.getString(R.string.amount, deal.cost.toString())
            if(deal.shipping_cost == 0.0) {
                binding.textviewFreeShipping.visible()
            }
            else {
                binding.textviewFreeShipping.gone()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun createInstance(deal: Deal): DealDetailsFragment {
            val fragment = DealDetailsFragment()
            val bundle = Bundle().apply {
                putParcelable("DEAL", deal)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}