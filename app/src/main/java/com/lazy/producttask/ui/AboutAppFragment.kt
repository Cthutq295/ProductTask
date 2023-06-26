package com.lazy.producttask.ui

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lazy.producttask.R
import com.lazy.producttask.databinding.FragmentAboutAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutAppFragment : Fragment(R.layout.fragment_about_app) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAboutAppBinding.bind(view)
        binding.tvOpenCompanySite.setOnClickListener {
            val dialogLayout =
                LayoutInflater.from(requireContext()).inflate(R.layout.open_site_dialog, null)
            val builder = AlertDialog.Builder(requireContext())
                .setView(dialogLayout)
                .show()

            dialogLayout.findViewById<TextView>(R.id.tvConfirm).setOnClickListener {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ironwaterstudio.com/ru"))
                builder.dismiss()
                startActivity(browserIntent)
            }

            dialogLayout.findViewById<TextView>(R.id.tvCancel).setOnClickListener {
                builder.dismiss()
            }
        }
    }
}