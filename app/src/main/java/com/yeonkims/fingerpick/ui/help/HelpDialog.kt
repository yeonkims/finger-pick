package com.yeonkims.fingerpick.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.yeonkims.fingerpick.R
import com.yeonkims.fingerpick.databinding.FragmentHelpDialogBinding


class HelpDialog : DialogFragment() {

    lateinit var binding: FragmentHelpDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.fragment_help_dialog, null, false)

        setOnClickListeners()

        return binding.root
    }

    private fun setOnClickListeners() {
        binding.confirmButton.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val TAG = "HelpDialog"
    }

}