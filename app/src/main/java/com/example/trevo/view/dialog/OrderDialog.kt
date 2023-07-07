package com.example.trevo.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.trevo.R

class OrderDialog: AppCompatDialogFragment(){
    private var listener: OrderDialogListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.modal_order, null)

        val editTextName = view.findViewById<EditText>(R.id.edit_name)
        val editTextEmail = view.findViewById<EditText>(R.id.edit_email)
        val editTextPhone = view.findViewById<EditText>(R.id.edit_phone)

        return AlertDialog.Builder(requireActivity())
            .setView(view)
            .setTitle("Login")
            .setNegativeButton("Cancel") {_, _, ->}
            .setPositiveButton("Ok") {_, _, ->
                val name = editTextName.text.toString()
                val email = editTextEmail.text.toString()
                val phone = editTextPhone.text.toString()
                listener!!.applyTexts(name, email, phone)
            }
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            context as OrderDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString() +
                        "must implement ExampleDialogListener"
            )
        }
    }

    interface OrderDialogListener {
        fun applyTexts(name: String?, email: String?, phone: String?)
    }
}