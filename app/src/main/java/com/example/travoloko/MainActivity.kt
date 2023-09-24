package com.example.travoloko

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.travoloko.databinding.ActivityMainBinding
import org.intellij.lang.annotations.JdkConstants.TitledBorderTitlePosition

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val brand = arrayOf(
        "Mayasari Bakti",
        "Rosalia Indah",
        "Panorama",
        "Antavaya"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var selectedBrand = ""
        var selectedDate = ""
        var selectedTime = ""

        with(binding) {
            val adapterBrand = ArrayAdapter(this@MainActivity, R.layout.simple_spinner_item, brand)
            adapterBrand.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            spinBrand.adapter = adapterBrand

            pickDate.init(
                pickDate.year, pickDate.month, pickDate.dayOfMonth
            ) { _, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
//                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
            }

            pickTime.setOnTimeChangedListener { view, hourOfDay, minute ->
                selectedTime = String.format("%02d:%02d", hourOfDay, minute)
//                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
            }

            btnPesan.setOnClickListener() {
                selectedBrand = spinBrand.selectedItem.toString()
                Toast.makeText(
                    this@MainActivity,
                    "Pemesanan tiket bus $selectedBrand, tanggal $selectedDate, jam $selectedTime berhasil dilakukan.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
