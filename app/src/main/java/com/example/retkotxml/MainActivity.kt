package com.example.retkotxml
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var textViewTitle: TextView
    private lateinit var textViewBody: TextView
    private lateinit var buttonFetch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        textViewTitle = findViewById(R.id.textViewTitle)
        textViewBody = findViewById(R.id.textViewBody)
        buttonFetch = findViewById(R.id.buttonFetch)

        buttonFetch.setOnClickListener {
            fetchPost()
        }
    }

    private fun fetchPost() {
        RetrofitClient.apiService.getPost().enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    textViewTitle.text = post?.title
                    textViewBody.text = post?.body
                } else {
                    textViewTitle.text = "Error: ${response.code()}"
                    textViewBody.text = response.message()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                textViewTitle.text = "Failure: ${t.message}"
                textViewBody.text = ""
            }
        })
    }
}
