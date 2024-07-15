package com.example.gundamage

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var categorySpinner: Spinner
    private lateinit var gunSpinner: Spinner
    private lateinit var damageTextView: TextView
    private lateinit var findDamageButton: Button
    private var selectedGun: String? = null

    private val gunCategories = mapOf(
        "Side Arms" to listOf("Classic", "Sheriff", "Shorty", "Frenzy", "Ghost"),
        "SMGs" to listOf("Stinger", "Spectre"),
        "Shotguns" to listOf("Bucky", "Judge"),
        "Rifles" to listOf("Vandal", "Phantom", "Bulldog", "Guardian"),
        "Sniper Rifles" to listOf("Marshal", "Outlaw", "Operator"),
        "Machine Guns" to listOf("Ares", "Odin")
    )
    private val gunDamage = mapOf(
        "Classic" to "Headshot: 202, Body: 65, Leg: 55",
        "Sheriff" to "Headshot: 202, Body: 65, Leg: 55",
        "Shorty" to "Headshot: 202, Body: 20, Leg: 17",
        "Frenzy" to "Headshot: 140, Body: 35, Leg: 29",
        "Ghost" to "Headshot: 195, Body: 65, Leg: 55",
        "Stinger" to "Headshot: 67, Body: 27, Leg: 22",
        "Spectre" to "Headshot: 78, Body: 26, Leg: 22",
        "Bucky" to "Headshot: 200, Body: 85, Leg: 70",
        "Judge" to "Headshot: 202, Body: 34, Leg: 28",
        "Vandal" to "Headshot: 160, Body: 40, Leg: 34",
        "Phantom" to "Headshot: 156, Body: 39, Leg: 33",
        "Bulldog" to "Headshot: 100, Body: 35, Leg: 29",
        "Guardian" to "Headshot: 195, Body: 65, Leg: 55",
        "Marshal" to "Headshot: 202, Body: 101, Leg: 85",
        "Outlaw" to "Headshot: 202, Body: 50, Leg: 42",
        "Operator" to "Headshot: 255, Body: 101, Leg: 85",
        "Ares" to "Headshot: 27, Body: 22, Leg: 20",
        "Odin" to "Headshot: 50, Body: 28, Leg: 25"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categorySpinner = findViewById(R.id.categorySpinner)
        gunSpinner = findViewById(R.id.gunSpinner)
        damageTextView = findViewById(R.id.t3)
        findDamageButton = findViewById(R.id.button)

        val categories = gunCategories.keys.toList()
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = categoryAdapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedCategory = categories[position]
                updateGunSpinner(selectedCategory)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        findDamageButton.setOnClickListener {
            selectedGun?.let {
                val damageInfo = gunDamage[it] ?: "Damage information not available."
                damageTextView.text = damageInfo
            } ?: run {
                damageTextView.text = "Please select a gun first"
            }
        }
    }

    private fun updateGunSpinner(category: String) {
        val guns = gunCategories[category] ?: emptyList()
        val gunAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, guns)
        gunAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gunSpinner.adapter = gunAdapter

        gunSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedGun = guns[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedGun = null
            }
        }
    }
}
