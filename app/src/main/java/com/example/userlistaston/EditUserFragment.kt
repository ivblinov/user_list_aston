package com.example.userlistaston

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.setFragmentResultListener
import com.example.userlistaston.databinding.FragmentEditUserBinding

private const val NAME = "name"
private const val EDIT_USER = "edit_user"

class EditUserFragment : Fragment() {

    private var _binding: FragmentEditUserBinding? = null
    private val binding get() = _binding!!
    private var user: User? = null


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditUserBinding.inflate(inflater, container, false)

        if (user == null) {
            setFragmentResultListener(requestKey = EDIT_USER) { _, bundle ->
                user = bundle.getParcelable(NAME, User::class.java)
                writeData()
            }
        } else writeData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.save.setOnClickListener {
            with(binding) {
                user?.name = editName.text.toString()
                user?.lastName = editLastname.text.toString()
                user?.phoneNumber = editPhoneNumber.text.toString().toLong()
            }
            parentFragmentManager.popBackStack(
                DetailsFragment::class.java.simpleName,
                POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun writeData() {
        with(binding) {
            editName.setText(user?.name)
            editLastname.setText(user?.lastName)
            editPhoneNumber.setText(user?.phoneNumber.toString())
            user?.photo?.let { photo.setImageResource(it) }
        }
    }
}