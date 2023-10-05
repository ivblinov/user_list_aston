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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userlistaston.databinding.FragmentUserListBinding
import kotlin.random.Random

private const val USER = "User"
private const val NAME = "name"
class UserListFragment : Fragment(), UserListener {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val userList = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (userList.isEmpty()) getContactList()

        binding.recyclerContainer.layoutManager = LinearLayoutManager(requireContext())

        val adapter = UserAdapter(userList, this)
        binding.recyclerContainer.adapter = adapter

        binding.recyclerContainer.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun getContactList() {
        val names = listOf("Иван", "Олег", "Алексей", "Игорь", "Александр", "Виктор", "Семён", "Егор", "Андрей", "Никита", "Борис")
        val lastNames = listOf("Солнечный", "Белый", "Черный", "Васильев", "Есенин", "Тютчев", "Толстой", "Гребенщиков", "Пушкин", "Ломов", "Ким")
        val photos = listOf(R.drawable.man1, R.drawable.man2, R.drawable.man3, R.drawable.man4, R.drawable.man5, R.drawable.man6, R.drawable.man7, R.drawable.man8)
        for (item in 1..50) {
            val itemContact = User(
                id = item,
                name = names[Random.nextInt(names.size)],
                lastName = lastNames[Random.nextInt(lastNames.size)],
                phoneNumber = Random.nextLong(80000000000, 89999999999),
                photo = photos[Random.nextInt(photos.size)]
            )
            userList.add(itemContact)
        }
    }

    override fun onClick(user: User) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<DetailsFragment>(R.id.fragment_container)
            addToBackStack(DetailsFragment::class.java.simpleName)

            setFragmentResult(USER, Bundle().apply { putParcelable(NAME, user) })
        }
    }
}