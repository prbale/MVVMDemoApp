package app.bale.demoapplication.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.bale.demoapplication.constants.AppConstants
import app.bale.demoapplication.databinding.FragmentProfileBinding
import app.bale.demoapplication.extension.callANumber
import app.bale.demoapplication.extension.launchWebsite
import app.bale.demoapplication.extension.sendAnEmail
import app.bale.demoapplication.ui.MainActivity


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        addButtonClickListeners()

        return root
    }

    private fun addButtonClickListeners() {

        binding.callBtn.setOnClickListener {
            context?.callANumber(phoneNumber = AppConstants.PHONE_NUMBER)
        }

        binding.emailBtn.setOnClickListener {
            (activity as? MainActivity)?.sendAnEmail(email = AppConstants.EMAIL)
        }

        binding.linkediBtn.setOnClickListener {
            context?.launchWebsite(webUrl = AppConstants.LINKED_IN_PROFILE)
        }

        binding.githubBtn.setOnClickListener {
            context?.launchWebsite(webUrl = AppConstants.GITHUB_PROFILE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}