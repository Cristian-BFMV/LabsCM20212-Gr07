package co.edu.udea.compumovil.gr07_20212.lab1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private val calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private var selectedGrade: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the datepicker elements
        val birthDateButton = findViewById<Button>(R.id.birthDateButton)
        val birthDateText = findViewById<TextView>(R.id.date)

        // Event listener to show datepicker
        birthDateButton.setOnClickListener {
            val datePicker = DatePickerDialog(
                this,
                { _: DatePicker?, datePickerYear: Int, datePickerMonth: Int, datePickerDay: Int ->
                    year = datePickerYear
                    month = datePickerMonth
                    day = datePickerDay
                    birthDateText.text = "" + datePickerDay + "/" + (datePickerMonth + 1) + "/" + datePickerYear
                },
                year,
                month,
                day
            )
            datePicker.show()
        }

        val educationSpinner: Spinner = findViewById(R.id.education)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.app_information_grades,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            educationSpinner.adapter = adapter
        }

        educationSpinner.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val appGrades = resources.getStringArray(R.array.app_information_grades)
                selectedGrade = appGrades[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        // Form next button
        val nextButton = findViewById<Button>(R.id.nextButton)
        // Form inputs
        val namesInput = findViewById<EditText>(R.id.names)
        val lastNamesInput = findViewById<EditText>(R.id.last_names)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.gender)
        nextButton.setOnClickListener {
            if(namesInput.text.isEmpty() && lastNamesInput.text.isEmpty() && birthDateText.text.isEmpty())
                showErrorMessage()
            else {
                val selectedGender = if (genderRadioGroup.checkedRadioButtonId == -1) "No has seleccionado un genero" else if(findViewById<RadioButton>(genderRadioGroup.checkedRadioButtonId).text.equals("Mujer")) "Femenino" else "Masculino"
                val builder = StringBuilder()
                builder.append(namesInput.text).append(lastNamesInput.text).append(selectedGender).append("Nació el").append(birthDateText.text).append(selectedGrade)
                Log.v("Información personal", builder.toString())
                val intent: Intent = Intent(this, ContactDataActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showErrorMessage() {
        val errorMessage = if(Locale.getDefault().language.equals("en")) resources.getString(R.string.app_english_error_message) else resources.getString(R.string.app_spanish_error_message)
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}