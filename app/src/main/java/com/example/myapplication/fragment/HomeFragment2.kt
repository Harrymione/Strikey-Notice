package com.example.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapplication.api.Meal
import com.example.myapplication.databinding.FragmentHome2Binding
import com.example.myapplication.videoModel.HomeViewModel
//import com.example.myapplication.retrofit.RetrofitInstance


class HomeFragment2 : Fragment() {
    private lateinit var binding: FragmentHome2Binding
    private lateinit var homeMvvm: HomeViewModel
    private lateinit var ramdomSticker: Meal


    companion object{
        const val MEAL_ID = "com.example.myapplication.fragment.idMeal"
        const val MEAL_NAME = "com.example.myapplication.fragment.nameMeal"
        const val MEAL_THUMB = "com.example.myapplication.fragment.thumbMeal"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeMvvm = ViewModelProvider(this)[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHome2Binding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeMvvm.getRandomSticker()
        observerRandomSticker()

        onRamdomStickerClick()
    }

    private fun onRamdomStickerClick(){
//        binding.randomStickerCard.setOnClickerListener {
//            val intent = Intent(activity.MainActivity::class.java)
//            startActivity(intent)
//        }

        binding.cardPower.setOnClickListener(){
            val intent = Intent(activity,DetailActivity::class.java)

            intent.putExtra(MEAL_NAME,ramdomSticker.idMeal)
            intent.putExtra(MEAL_NAME,ramdomSticker.strMeal)
            intent.putExtra(MEAL_NAME,ramdomSticker.strMealThumb)
            startActivity(intent)
        }


    }

    private fun observerRandomSticker(){
        homeMvvm.observeRandomStickerLivedata().observe(viewLifecycleOwner,object : Observer<Meal>{
            override fun onChanged(m: Meal) {
                Glide.with(this@HomeFragment2)
                    .load(m.strMealThumb)
                    .into(binding.powerpuffGirlImg)


//                this@HomeFragment2.ramdomSticker = meal
            }

        })


    }

}

