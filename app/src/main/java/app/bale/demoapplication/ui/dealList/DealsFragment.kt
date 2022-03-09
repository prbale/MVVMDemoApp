package app.bale.demoapplication.ui.dealList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.bale.demoapplication.R
import app.bale.demoapplication.databinding.FragmentDealsBinding
import app.bale.demoapplication.extension.addFragment
import app.bale.demoapplication.listeners.OnItemClickListener
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.data.repository.DealsRepository
import app.bale.demoapplication.dependencyinjection.module.viewmodel.ViewModelFactory
import app.bale.demoapplication.extension.gone
import app.bale.demoapplication.extension.showMessage
import app.bale.demoapplication.extension.visible
import app.bale.demoapplication.ui.dealDetails.DealDetailsFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DealsFragment : Fragment() {

    @Inject
    internal lateinit var adapter: MainAdapter

    @Inject
    internal lateinit var repository: DealsRepository

    private lateinit var dealsViewModel: DealsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var binding: FragmentDealsBinding? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        dealsViewModel = ViewModelProvider(this, viewModelFactory)[DealsViewModel::class.java]

        binding = FragmentDealsBinding.inflate(inflater)
        val root: View = binding!!.root

        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(item: Deal?) {
                item?.let {
                    addFragment(
                        DealDetailsFragment.createInstance(it),
                        R.id.nav_host_fragment_activity_main
                    )
                }
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
        context?.showMessage(errorMessage)
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