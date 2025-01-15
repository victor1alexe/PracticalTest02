package ro.pub.cs.systems.eim.practicaltest02v2

import DictionaryQueryThread
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordEditText = findViewById<EditText>(R.id.wordEditText)
        val definitionTextView = findViewById<TextView>(R.id.definitionTextView)

        val requestButton = findViewById<Button>(R.id.button)


        requestButton.setOnClickListener {
            val word = wordEditText.text.toString()
            val queryThread = DictionaryQueryThread(word)
            queryThread.start()
            try {
                queryThread.join() // This will block until the thread completes
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val definition = queryThread.getResult()
            definitionTextView.text = definition ?: "No definition found"
        }


    }
}