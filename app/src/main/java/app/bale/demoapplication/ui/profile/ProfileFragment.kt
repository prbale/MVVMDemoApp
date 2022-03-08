package app.bale.demoapplication.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.bale.demoapplication.ui.MainActivity
import app.bale.demoapplication.databinding.FragmentProfileBinding
import app.bale.demoapplication.extension.callANumber
import app.bale.demoapplication.extension.launchWebsite
import app.bale.demoapplication.extension.sendAnEmail


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
            context?.callANumber(phoneNumber = "+918530669827")
        }

        binding.emailBtn.setOnClickListener {
            (activity as? MainActivity)?.sendAnEmail(
                email = "prashant.bale@hotmail.com",
                subject = "Hello",
                body = ""
            )
        }

        binding.linkediBtn.setOnClickListener {
            context?.launchWebsite(webUrl = "http://www.linkedin.com/profile/view?id=prashantbale")
        }

        binding.githubBtn.setOnClickListener {
            context?.launchWebsite(webUrl = "https://github.com/prbale/")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}