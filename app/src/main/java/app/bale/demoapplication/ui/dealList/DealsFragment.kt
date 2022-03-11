package app.bale.demoapplication.ui.dealList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.bale.demoapplication.R
import app.bale.demoapplication.data.model.Deal
import app.bale.demoapplication.databinding.FragmentDealsBinding
import app.bale.demoapplication.extension.addFragment
import app.bale.demoapplication.extension.gone
import app.bale.demoapplication.extension.showMessage
import app.bale.demoapplication.extension.visible
import app.bale.demoapplication.listeners.OnItemClickListener
import app.bale.demoapplication.ui.base.BaseFragment
import app.bale.demoapplication.ui.dealDetails.DealDetailsFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DealsFragment :
        BaseFragment<DealsViewModel, FragmentDealsBinding>(DealsViewModel::class.java) {

    @Inject
    internal lateinit var adapter: MainAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_deals

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.rvMain.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {

        // Item click listener
        adapter.setOnItemClickListener(onDealItemClickListener())

        // Observe
        viewModel.dealsUiState.observe(viewLifecycleOwner) { state -> handleState(state) }

        // Trigger call
        viewModel.getAllDeals()
    }

    private fun handleState(state: DealsUiState?) {
        when (state) {
            is DealsUiState.Error -> showError(state.errorMessage)
            is DealsUiState.Loading -> showLoading()
            is DealsUiState.Success -> loadDeals(state.data)
        }
    }

    private fun onDealItemClickListener() = object : OnItemClickListener {
        override fun onItemClick(item: Deal?) {
            item?.let {
                addFragment(
                    DealDetailsFragment.createInstance(it),
                    R.id.nav_host_fragment_activity_main
                )
            }
        }
    }

    private fun showError(errorMessage: String) {
        dataBinding.loadingIndicator.gone()
        showMessage(errorMessage)
    }

    private fun showLoading() = dataBinding.loadingIndicator.visible()

    private fun loadDeals(data: List<Deal>?) {
        dataBinding.loadingIndicator.gone()
        data?.let { adapter.setDealsList(it) }
    }
}