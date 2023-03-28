package com.yeonkims.fingerpick.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.yeonkims.fingerpick.R
import com.yeonkims.fingerpick.databinding.FragmentHelpDialogBinding
import com.yeonkims.fingerpick.databinding.FragmentPickerBinding
import com.yeonkims.fingerpick.ui.help.HelpDialog

class PickerFragment : Fragment() {

    lateinit var binding: FragmentPickerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_picker, container, false
        )
        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        binding.helpBtn.setOnClickListener {
            HelpDialog().show(
                parentFragmentManager,
                HelpDialog.TAG
            )
        }
    }
}