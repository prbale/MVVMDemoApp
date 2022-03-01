package app.bale.demoapplication.ui.dealDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.bale.demoapplication.MainActivity
import app.bale.demoapplication.databinding.FragmentDealDetailsBinding

class DealDetailsFragment : Fragment() {

    private lateinit var dealDetailsViewModel: DealDetailsViewModel

    private var binding: FragmentDealDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Toast.makeText(activity, "Back from fragment", Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
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