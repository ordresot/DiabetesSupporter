package com.ordresot.diabetessupporter.presentation.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ordresot.diabetessupporter.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val moreViewModel = ViewModelProvider(this)[MoreViewModel::class.java]

        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}