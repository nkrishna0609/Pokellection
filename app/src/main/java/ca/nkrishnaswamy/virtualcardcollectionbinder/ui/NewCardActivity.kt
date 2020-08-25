package ca.nkrishnaswamy.virtualcardcollectionbinder.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ca.nkrishnaswamy.virtualcardcollectionbinder.R

class NewCardActivity : AppCompatActivity() {

    private lateinit var editCardName: EditText
    private lateinit var editCardSetName: EditText
    private lateinit var editCardSetNum: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_card)

        editCardName = findViewById(R.id.enterCardName)
        editCardSetName = findViewById(R.id.enterCardSetName)
        editCardSetNum = findViewById(R.id.enterCardSetNum)

        val searchButton = findViewById<Button>(R.id.buttonSearch)
        searchButton.setOnClickListener{
            val searchCardIntent = Intent()
            if (TextUtils.isEmpty(editCardSetName.text) || TextUtils.isEmpty(editCardSetNum.text) || TextUtils.isEmpty(editCardSetNum.text)){
                setResult(Activity.RESULT_CANCELED, searchCardIntent)
                Toast.makeText(applicationContext, R.string.errorEmptyStrings, Toast.LENGTH_LONG).show()
            }
            else{
                val cardName = editCardName.text.toString()
                val cardSetName = editCardSetName.text.toString()
                val cardSetNum = editCardSetNum.text.toString()

                searchCardIntent.putExtra("cardName",cardName)
                searchCardIntent.putExtra("cardSetName",cardSetName)
                searchCardIntent.putExtra("cardSetNum",cardSetNum)

                setResult(Activity.RESULT_OK,searchCardIntent)
                finish()
            }
        }
        val cancelButton = findViewById<Button>(R.id.buttonCancel)
        cancelButton.setOnClickListener{
            finish()
        }
    }
}