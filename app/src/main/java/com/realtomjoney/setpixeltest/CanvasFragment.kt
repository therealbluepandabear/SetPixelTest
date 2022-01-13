package com.realtomjoney.pyxlmoose.fragments.canvas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.realtomjoney.setpixeltest.MyCanvasView
import com.realtomjoney.setpixeltest.databinding.FragmentCanvasBinding


class CanvasFragment(private val spanCount: Int) : Fragment() {
    private var _binding: FragmentCanvasBinding? = null

    private val binding get() = _binding!!

    lateinit var myCanvasViewInstance: MyCanvasView

    private fun setupCanvas() {
        myCanvasViewInstance = MyCanvasView(requireContext(), spanCount)

        binding.rootLayout.addView(myCanvasViewInstance)
    }

    companion object {
        fun newInstance(spanCount: Int) = CanvasFragment(spanCount)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCanvasBinding.inflate(inflater, container, false)

        setupCanvas()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}