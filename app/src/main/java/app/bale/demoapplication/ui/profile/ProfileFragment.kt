package app.bale.demoapplication.ui.profile

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.bale.demoapplication.databinding.FragmentProfileBinding
import app.bale.demoapplication.di.Injectable


class ProfileFragment : Fragment(), Injectable {

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

        setDetailsAboutMe()

        return root
    }

    private fun setDetailsAboutMe() {
        binding.callBtn.setOnClickListener {
            val phone = "+918530669827"
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }

        binding.emailBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("prashant.bale@hotmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hello...!!")
            intent.putExtra(Intent.EXTRA_TEXT, "")
            intent.data = Uri.parse("mailto:")

            if (activity?.packageManager?.let { it1 -> intent.resolveActivity(it1) } != null) {
                startActivity(intent)
            } else {
                Toast.makeText(
                    activity, "There is no application that support this action",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.linkediBtn.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=prashantbale"))
            val packageManager = context?.packageManager
            val list =
                packageManager?.queryIntentActivities(intent!!, PackageManager.MATCH_DEFAULT_ONLY)
            if (list?.isEmpty() == true) {
                intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://www.linkedin.com/profile/view?id=prashantbale")
                )
            }
            startActivity(intent)
        }

        binding.githubBtn.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/prbale/"))
            val packageManager = context?.packageManager
            val list =
                packageManager?.queryIntentActivities(intent!!, PackageManager.MATCH_DEFAULT_ONLY)
            if (list?.isEmpty() == true) {
                intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/prbale/")
                )
            }
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}