package com.example.gson

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

private val etJson: EditText by lazy { findViewById(R.id.etJson) }
private val tvObjectData: TextView by lazy { findViewById(R.id.tvObjectData) }
private val fabJsonToObject: FloatingActionButton by lazy { findViewById(R.id.fabJsonToObject) }
private val fabObjectToJson: FloatingActionButton by lazy { findViewById(R.id.fabObjectToJson) }

 override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    var person = getPerson()
    val gson = Gson()
    val json = """
            [
                {
                    "id": "6212577f0095c22f40b1a78a",
                    "email": "fields_tyson@manglo.degree",
                    "roles": [
                        "owner",
                        "guest"
                    ],
                    "apiKey": "f767f1e7-63e2-4f7b-984d-1f4743e7dfd1",
                    "profile": {
                        "dob": "17.02.1989",
                        "name": "Fields Tyson",
                        "about": "Quis labore commodo culpa aliquip cillum deserunt culpa non pariatur minim ullamco reprehenderit nulla esse. Ullamco aliquip do commodo cillum.",
                        "address": "59 Menahan Street, Loretto, Washington",
                        "company": "Manglo",
                        "location": {
                            "lat": 56.740646,
                            "long": -60.715809
                        }
                    },
                    "username": "fields89",
                    "createdAt": "09.12.2013T14:53:16.548Z",
                    "updatedAt": "10.12.2013T14:53:16.548Z"
                }
            ]
        """.trimIndent()

    etJson.setText(json)

    fabJsonToObject.setOnClickListener {
        try {
            val type = genericType<List<Person>>()
            val currentJson = etJson.text.toString().trimIndent()
            val personList: List<Person> = gson.fromJson(currentJson, type)
            if (personList.isNotEmpty()) {
                person = personList.first()
                tvObjectData.text = person.toString()
            }
            etJson.setText("")
            etJson.isEnabled = false
            fabJsonToObject.isEnabled = false
            fabObjectToJson.isEnabled = true
        } catch (e: JsonSyntaxException) {
            Toast.makeText(applicationContext, "FAILED  JSON", Toast.LENGTH_LONG).show()
        }
    }

    fabObjectToJson.setOnClickListener {
        val newJson = gson.toJson(listOf(person))
        etJson.setText(newJson)
        tvObjectData.text = ""
        etJson.isEnabled = true
        fabJsonToObject.isEnabled = true
        fabObjectToJson.isEnabled = false
    }
}
// ПОЛУЧЕНИЕ СПИСКА ОБЩЕГО ТИПА ИЗ ПОЛЬЗОВАТЕЛЬСКОГО КЛАССА (ИСПОЛЬЗОВАНИЕ TypeToken + ДЖЕНЕРИКИ С БИБЛИОТЕКОЙ GSON В КОТЛИНЕ)
  inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

    // НУЖЕН ДЛЯ ТОГО ЧТОБЫ ПОЛУЧАТЬ ОБЪЕКТ ПО УМОЛЧАНИЮ
  private fun getPerson(): Person {
      val personLocation = PersonLocation(10.5, 100.0)
      val personProfile = PersonProfile(
        "05.07.1900",
        "Alex Show",
        "likes apple",
        "Vladik, Vaty st. 50",
        "TEKNAVO",
        personLocation
      )
       return Person(
        "55555",
        "GOODBUY@YANDEX.RU",
        listOf("GGGGGG", "AAAAAAAA"),
        "AAA-567-DDDD-555",
        personProfile,
        "SHOW",
        "09.12.2013T14:53:16.548Z",
        "10.12.2013T14:53:16.548Z"
        )
  }

}