package com.example.userlistaston

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.userlistaston.databinding.FragmentDetailsBinding

private const val USER = "User"
private const val NAME = "name"
private const val EDIT_USER = "edit_user"
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var user: User? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        if (user == null) {
            setFragmentResultListener(requestKey = USER) { _, bundle ->
                user = bundle.getParcelable(NAME, User::class.java)
                writeData()
            }
        } else writeData()

        binding.toEdit.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<EditUserFragment>(R.id.fragment_container)
                addToBackStack(EditUserFragment::class.java.simpleName)

                setFragmentResult(EDIT_USER, Bundle().apply { putParcelable(NAME, user) })
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun writeData() {
        with(binding) {
            name.text = user?.name
            lastName.text = user?.lastName
            phoneNumber.text = user?.phoneNumber.toString()
            user?.photo?.let { photo.setImageResource(it) }
        }
    }
}