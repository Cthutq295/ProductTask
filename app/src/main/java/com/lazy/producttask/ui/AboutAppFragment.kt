package com.lazy.producttask.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lazy.producttask.R
import com.lazy.producttask.databinding.FragmentAboutAppBinding
import com.lazy.producttask.databinding.FragmentProductInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutAppFragment : Fragment(R.layout.fragment_about_app) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAboutAppBinding.bind(view)
        binding.tvOpenCompanySite.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ironwaterstudio.com/ru"))
            startActivity(browserIntent)
        }
    }
}