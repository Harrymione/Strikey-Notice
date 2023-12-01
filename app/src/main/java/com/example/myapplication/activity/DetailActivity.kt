package com.example.myapplication.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.api.Meal
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.fragment.HomeFragment2
import com.example.myapplication.videoModel.StickerViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var mealId:String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var youtubeLink : String
    private lateinit var binding:ActivityDetailBinding
    private lateinit var mealMvve:StickerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityDetailBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvve = ViewModelProviders.of(this)[StickerViewModel::class.java]

        getMealInformationFormIntent() ////

        setInformationInView() ////

        loadingCase()

        mealMvve.getMealDetail(mealId)
        observerMealDetailsLiverData()

        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick(){
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiverData(){
        mealMvve.observerMealDetailsLiveData().observe(this,object :Observer<Meal>{
            override fun onChanged(t: Meal) {

                onResponseCase()
                val meal = t

                binding.tvCategories.text = "Category : ${meal!!.strCategory}"
                binding.tvArea.text = "Area : ${meal.strArea}"
                binding.tvInstructionSt.text = meal.strInstructions

                youtubeLink = meal.strYoutube

            }
        })
    }

    private fun setInformationInView(){
        Glide.with(applicationContext)
            .load(mealThumb).
            into(binding.imgStickerDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))

    }

    private fun getMealInformationFormIntent(){
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment2.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment2.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment2.MEAL_THUMB)!!

    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSave.visibility = View.INVISIBLE
        binding.tvInstruction.visibility = View.INVISIBLE
        binding.tvCategories.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE
    }

    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnSave.visibility = View.VISIBLE
        binding.tvCategories.visibility = View.VISIBLE
        binding.tvInstruction.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE

    }
}