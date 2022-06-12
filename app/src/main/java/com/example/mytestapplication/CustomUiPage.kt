package com.example.mytestapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.core.widget.TextViewCompat
import com.bumptech.glide.Glide
import com.example.mynetworklibrary.CustomUi
import com.example.mynetworklibrary.Uidata
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CustomUiPage : AppCompatActivity() {
    private var data: CustomUi? = null
    private lateinit var linear_layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_ui_page)
        linear_layout = findViewById(R.id.linear_layout)
        data = intent.extras?.get("CUSTOM_UI") as CustomUi
        if (data != null) {
            setView(data)
        }
    }


    private fun setView(body: CustomUi?) {
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )



        layoutParams.setMargins(40, 40, 40, 0)
        val imageView = ImageView(this)
        imageView.layoutParams = layoutParams
        imageView.id = ViewCompat.generateViewId()
        Glide.with(this).load(body?.logo_url).into(imageView)
        linear_layout.addView(imageView)

        layoutParams.setMargins(40, 40, 40, 0)
        val labelView = TextView(this)
        labelView.layoutParams = layoutParams
        TextViewCompat.setTextAppearance(labelView, R.style.boldText)
        labelView.id = ViewCompat.generateViewId()
        labelView.text = body?.heading_text
        linear_layout.addView(labelView)
        val uidata: List<Uidata>? = body?.uidata
        if (uidata != null) {

            for (f in uidata) {
                when (f.uitype) {

                    "edittext" -> {

                        layoutParams.setMargins(20, 20, 20, 20)

                        val textInputLayout = TextInputLayout(this)
                        val editText = TextInputEditText(textInputLayout.context)
                        textInputLayout.setBoxCornerRadii(7F, 7F, 7F, 7F)
                        textInputLayout.updatePadding(20, 20, 20, 20)
                        textInputLayout.layoutParams = layoutParams
                        textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
                        textInputLayout.addView(editText)
                        textInputLayout.hint = f.hint



                        editText.id = ViewCompat.generateViewId()
                        linear_layout.addView(textInputLayout)
                    }
                    "label" -> {
                        layoutParams.setMargins(40, 40, 40, 0)
                        val labelView = TextView(this)
                        labelView.layoutParams = layoutParams
                        TextViewCompat.setTextAppearance(labelView, R.style.italicText)
                        labelView.id = ViewCompat.generateViewId()
                        labelView.text = f.value
                        linear_layout.addView(labelView)
                    }
                    "button" -> {
                        layoutParams.setMargins(20, 20, 20, 20)
                        val button = Button(this)
                        button.layoutParams = layoutParams
                        button.updatePadding(20, 20, 20, 20)
                        button.id = ViewCompat.generateViewId()
                        button.text = f.value
                        linear_layout.addView(button)

                    }
                }
            }
        }
    }
}