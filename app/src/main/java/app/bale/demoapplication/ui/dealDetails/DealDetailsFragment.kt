package app.bale.demoapplication.ui.dealDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.bale.demoapplication.MainActivity
import app.bale.demoapplication.databinding.FragmentDealDetailsBinding

class DealDetailsFragment : Fragment() {

    private lateinit var dealDetailsViewModel: DealDetailsViewModel

    private var binding: FragmentDealDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.title = "Deal Details"

        dealDetailsViewModel = ViewModelProvider(this)[DealDetailsViewModel::class.java]

        binding = FragmentDealDetailsBinding.inflate(inflater)
        val root: View = binding!!.root

        dealDetailsViewModel.dealsList.observe(viewLifecycleOwner, Observer {

        })

        dealDetailsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })

        dealDetailsViewModel.displayDealDetails()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}