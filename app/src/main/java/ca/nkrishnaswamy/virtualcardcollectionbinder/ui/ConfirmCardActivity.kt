package ca.nkrishnaswamy.virtualcardcollectionbinder.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ca.nkrishnaswamy.virtualcardcollectionbinder.R

class ConfirmCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_card)

        val cancelButton = findViewById<Button>(R.id.buttonCancel2)
        cancelButton.setOnClickListener{
            finish()
        }
    }
}