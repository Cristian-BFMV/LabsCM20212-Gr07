package co.edu.udea.compumovil.gr07_20212.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import java.util.*

class ContactDataActivity : AppCompatActivity() {
    private val paisesLatinoamerica = arrayOf(
        "Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Costa Rica", "Cuba", "Ecuador",
        "El Salvador", "Guayana Francesa", "Granada", "Guatemala", "Guayana", "Haití", "Honduras",
        "Jamaica", "México", "Nicaragua", "Paraguay", "Panamá", "Perú", "Puerto Rico",
        "República Dominicana", "Surinam", "Uruguay", "Venezuela")

    private val ciudadesColombia = arrayOf(
        "Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena de Indias", "Soacha", "Cúcuta", "Soledad",
        "Bucaramanga", "Bello", "Villavicencio", "Ibagué", "Santa Marta", "Valledupar", "Manizales",
        "Pereira", "Montería", "Neiva", "Pasto", "Armenia")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)

        // Form next button
        val nextButton = findViewById<Button>(R.id.nextButton)
        // Form inputs
        val phoneInput = findViewById<EditText>(R.id.phone_number)
        val addressInput = findViewById<EditText>(R.id.address)
        val emailInput = findViewById<EditText>(R.id.email)
        val editTextPais = findViewById<AutoCompleteTextView>(R.id.country)
        val editTextCiudad = findViewById<AutoCompleteTextView>(R.id.city)

        val adapterPais = ArrayAdapter(this,
            R.layout.custom_item, R.id.autoCompleteItem, paisesLatinoamerica
        )
        val adapterCiudad = ArrayAdapter(this,
            R.layout.custom_item, R.id.autoCompleteItem, ciudadesColombia
        )
        editTextPais.setAdapter(adapterPais)
        editTextCiudad.setAdapter(adapterCiudad)

        nextButton.setOnClickListener {
            if(phoneInput.text.isEmpty() && addressInput.text.isEmpty() && emailInput.text.isEmpty()  && editTextPais.text.isEmpty()  && editTextCiudad.text.isEmpty())
                showErrorMessage()
            else {
                val builder = StringBuilder()
                builder.append("Teléfono: ").append(phoneInput.text).append("\n").append("Dirección: ").append(addressInput.text).append("\n").append("Email: ").append(emailInput.text).append("\n").append("País: ").append(editTextPais.text).append("\n").append("Ciudad: ").append(editTextCiudad.text)
                Log.v("Info de contacto:\n", builder.toString())
            }
        }
    }

    private fun showErrorMessage() {
        val errorMessage = if(Locale.getDefault().language.equals("en")) resources.getString(R.string.app_english_error_message) else resources.getString(R.string.app_spanish_error_message)
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}