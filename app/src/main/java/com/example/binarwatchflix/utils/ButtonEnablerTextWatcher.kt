package com.example.binarwatchflix.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.binarwatchflix.R

class ButtonEnablerTextWatcher(
    private val context: Context,
    private val buttonSendContainer: CardView,
    private val imageSend: ImageView
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
        if (charSequence.toString().trim().isNotEmpty()) {
            buttonSendContainer.isEnabled = true
            buttonSendContainer.setCardBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.red_400
                )
            )

            imageSend.isEnabled = true
            imageSend.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.red_200
                )
            )
        } else {
            buttonSendContainer.isEnabled = false
            buttonSendContainer.setCardBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.grey_200
                )
            )

            imageSend.isEnabled = false
            imageSend.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.grey_400
                )
            )
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

}