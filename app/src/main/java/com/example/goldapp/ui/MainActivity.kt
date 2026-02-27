package com.example.goldapp.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goldapp.adapter.RecyclerMainAdapter
import com.example.goldapp.databinding.ActivityMainBinding
import com.example.goldapp.remote.datamodel.ContentModel
import com.example.goldapp.remote.datamodel.ProductModel
import com.example.goldapp.remote.datamodel.TimeModel
import com.example.goldapp.remote.golds.ProductApiRepository
import com.example.goldapp.remote.golds.ProductRequest
import com.example.goldapp.remote.timenow.TimeApiRepository
import com.example.goldapp.remote.timenow.TimeRequest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val goldPrice = ArrayList<ContentModel>()
    private val currencyPrice = ArrayList<ContentModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProduct() // دریافت اطلاعات از سمت سرور

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        TimeApiRepository.instance.getTime(
            object : TimeRequest {
                override fun onSuccess(data: TimeModel) {
                    val dataOfView = data.date
                    val text = "${dataOfView.lValue} ${dataOfView.jValue}" +
                            " ${dataOfView.fValue} ${dataOfView.yValue}"
                    binding.textViewTime.text = text

                }

                override fun onNotSuccess(massage: String) {
                    Toast.makeText(this@MainActivity, massage, Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                }
            }
        )

        binding.textPriceArz.setOnClickListener {

            binding.textPriceArz.setTextColor(Color.parseColor("#E7C376"))
            binding.textPriceGold.setTextColor(Color.parseColor("#787879"))

            setData(currencyPrice) // بارگذاری اطلاعات ارز در ریسایکلر

        }

        binding.textPriceGold.setOnClickListener {

            binding.textPriceArz.setTextColor(Color.parseColor("#787879"))
            binding.textPriceGold.setTextColor(Color.parseColor("#E7C376"))

            setData(goldPrice) // بارگزاری اطلاعات طلا در ریسایکلر
        }


    }

    private fun getProduct(){

        ProductApiRepository.instance.getProducts(

            object : ProductRequest{
                override fun onSuccess(data: ProductModel) {
                    // دریافت داده ها از سرور در قالب data
                    currencyPrice.addAll(data.data.currencies) // ذخیره اطلاعات ارز ها در یک متغییر جدا
                    goldPrice.addAll(data.data.golds) // ذخیره اطلاعات طلا در یک متغییر جدا

                    setData(goldPrice) // بارگزاری اولیه اطلاعات  برای نمایش پیشفرض
                }

                override fun onNotSuccess(massage: String) {
                    Toast.makeText(
                        this@MainActivity,
                        " عملیانت موفق نبود \n\n $massage",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onError(error: String) {
                    Toast.makeText(
                        this@MainActivity,
                        "ERROR \n\n  $error",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        )
    }

    // هر وقت داده های ارز رو بهت دادم اونارو داخل ریسایکلر بارگذاری کن
    // و هر وقت داده های طلا رو بهت دادم  بجای ارز اونارو داخل ریسایکلر بارگزاری کن
    // یجورایی داده هات قراره متغییر باشن
    private fun setData(data : ArrayList<ContentModel>){

        binding.recyclerView.adapter = RecyclerMainAdapter(data)

    }
}
