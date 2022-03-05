package app.bale.demoapplication.ui.deals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.bale.demoapplication.MyViewModelFactory
import app.bale.demoapplication.R
import app.bale.demoapplication.adapter.MainAdapter
import app.bale.demoapplication.databinding.FragmentDealsBinding
import app.bale.demoapplication.extension.addFragment
import app.bale.demoapplication.extension.gone
import app.bale.demoapplication.extension.visible
import app.bale.demoapplication.listeners.OnItemClickListener
import app.bale.demoapplication.model.Deal
import app.bale.demoapplication.repository.DealsRepository
import app.bale.demoapplication.repository.RetrofitService
import app.bale.demoapplication.ui.dealDetails.DealDetailsFragment

class DealsFragment : Fragment() {

    private lateinit var dealsViewModel: DealsViewModel

    private var binding: FragmentDealsBinding? = null

    private val retrofitService = RetrofitService.getInstance()

    private lateinit var adapter: MainAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        dealsViewModel = ViewModelProvider(this, MyViewModelFactory(DealsRepository(retrofitService)))[DealsViewModel::class.java]

        binding = FragmentDealsBinding.inflate(inflater)
        val root: View = binding!!.root

        adapter = MainAdapter(requireContext(), object : OnItemClickListener {
            override fun onItemClick(item: Deal?) {
                addFragment(DealDetailsFragment(), R.id.nav_host_fragment_activity_main)
            }
        })

        binding?.rvMain?.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        dealsViewModel.dealsUiState.observe(viewLifecycleOwner) {
            when(it) {
                is DealsUiState.Error -> showError(it.errorMessage)
                DealsUiState.Loading -> showLoading()
                is DealsUiState.Success -> loadDeals(it.data)
            }
         }

        dealsViewModel.getAllDeals()

        return root
    }

    private fun showError(errorMessage: String) {
        binding?.loadingIndicator?.gone()
        Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding?.loadingIndicator?.visible()
    }

    private fun loadDeals(data: List<Deal>?) {
        binding?.loadingIndicator?.gone()
        data?.let {
            adapter.setDealsList(data)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}