package com.example.modul5

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)

        view.findViewById<Button>(R.id.next_button).setOnClickListener {
            if (!isPasswordValid(view.findViewById<EditText>(R.id.password_edit_text).text)) {
                view.findViewById<TextInputLayout>(R.id.password_text_input).error = getString(R.string.shr_error_password)
            } else {
                view.findViewById<TextInputLayout>(R.id.password_text_input).error = null
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false)
            }
        }

        view.findViewById<EditText>(R.id.password_edit_text).setOnKeyListener { _, _, _ ->
            if (isPasswordValid(view.findViewById<EditText>(R.id.password_edit_text).text)) {
                view.findViewById<TextInputLayout>(R.id.password_text_input).error = null
            }
            false
        }
        return view
    }

    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}
