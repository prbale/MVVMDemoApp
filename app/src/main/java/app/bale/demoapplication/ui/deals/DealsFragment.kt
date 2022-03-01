package app.bale.demoapplication.ui.deals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.bale.demoapplication.MyViewModelFactory
import app.bale.demoapplication.R
import app.bale.demoapplication.adapter.MainAdapter
import app.bale.demoapplication.databinding.FragmentDealsBinding
import app.bale.demoapplication.extension.addFragment
import app.bale.demoapplication.listeners.OnItemClickListener
import app.bale.demoapplication.model.Deal
import app.bale.demoapplication.repository.DealsRepository
import app.bale.demoapplication.repository.RetrofitService
import app.bale.demoapplication.ui.dealDetails.DealDetailsFragment

class DealsFragment : Fragment() {

    private lateinit var dealsViewModel: DealsViewModel

    private var binding: FragmentDealsBinding? = null

    private val retrofitService = RetrofitService.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        dealsViewModel = ViewModelProvider(this, MyViewModelFactory(DealsRepository(retrofitService)))[DealsViewModel::class.java]

        binding = FragmentDealsBinding.inflate(inflater)
        val root: View = binding!!.root

        val adapter = MainAdapter(requireContext(), object : OnItemClickListener {
            override fun onItemClick(item: Deal?) {
                addFragment(DealDetailsFragment(), R.id.nav_host_fragment_activity_main)
            }
        })

        binding?.rvMain?.adapter = adapter

        dealsViewModel.dealsList.observe(viewLifecycleOwner, {
            binding?.rvMain?.also { recyclerView ->
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                adapter?.setDealsList(it)
            }
        })

        dealsViewModel.errorMessage.observe(viewLifecycleOwner, {
        })

        dealsViewModel.getAllDeals()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}