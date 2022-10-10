package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityMainBinding.inflate
import com.example.myapplication.databinding.ConfirmDialogBinding
import com.example.myapplication.databinding.SignupDialogBinding

class SignupDialog(context: Context) : Dialog(context) {

    private lateinit var binding: SignupDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignupDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llOk.setOnClickListener {
            dialogListener.onClick(0)
            dismiss()
        }

    }

    private lateinit var dialogListener: DialogListener

    interface DialogListener{
        fun onClick(position: Int)
    }

    @JvmName("setDialogListener1")
    fun setDialogListener(listener: DialogListener){
        this.dialogListener = listener
    }


}