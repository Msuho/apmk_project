package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ConfirmDialogBinding

class ConfirmDialog(context: Context) : Dialog(context) {

    private lateinit var binding: ConfirmDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConfirmDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llCancel.setOnClickListener {
            dialogListener.onClick(0)
            dismiss()
        }

        binding.llOk.setOnClickListener {
            dialogListener.onClick(1)
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