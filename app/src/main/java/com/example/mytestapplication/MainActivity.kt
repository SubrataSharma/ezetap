package com.example.mytestapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.mynetworklibrary.ApiClient
import com.example.mynetworklibrary.CustomUi
import com.example.mynetworklibrary.NetworkServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonClick: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonClick = findViewById(R.id.button_click)
        progressBar = findViewById(R.id.progress_bar)
        buttonClick.setOnClickListener {
            progressBar.isVisible = true
            buttonClick.isVisible = false
            networkCall()
        }
    }

    private fun networkCall() {
        ApiClient().retrofit.create(NetworkServiceInterface::class.java).fetchCustomUI()
            .enqueue(object :
                Callback<CustomUi> {
                override fun onResponse(call: Call<CustomUi>, response: Response<CustomUi>) {
                    Timber.d("onResponse ${response.code()}")
                    if (response.code() == 200) {
//                        Glide
//                            .with(this@MainActivity)
//                            .load(response.body()?.logo_url)
//                            .centerCrop()
//                            .into(network_img)
                        launchCustomUiPage(response.body())
                    }
                }

                override fun onFailure(call: Call<CustomUi>, t: Throwable) {
                    Timber.d("onResponse ${t.stackTrace}")
                    buttonClick.isVisible= false
                }
            })
    }

    private fun launchCustomUiPage(body: CustomUi?) {
        val intent = Intent(this, CustomUiPage::class.java)
        intent.putExtra("CUSTOM_UI",body)
        startActivity(intent)
        finish()
        progressBar.isVisible = false

    }

}