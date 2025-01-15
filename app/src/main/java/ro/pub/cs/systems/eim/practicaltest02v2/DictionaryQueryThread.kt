import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DictionaryQueryThread(private val word: String) : Thread() {

    // Variable to store the result
    private var definitionResult: String? = null

    override fun run() {
        try {
            // Construct the URL for the Dictionary API
            val url = URL("https://api.dictionaryapi.dev/api/v2/entries/en/$word")
            val urlConnection = url.openConnection() as HttpURLConnection

            // Set request method and timeouts
            urlConnection.requestMethod = "GET"
            urlConnection.connectTimeout = 5000
            urlConnection.readTimeout = 5000

            // Get the response code
            val responseCode = urlConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                val inputStream = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val response = StringBuilder()
                var inputLine: String?
                while (inputStream.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }

                // Parse the response to get the first definition
                definitionResult = parseFirstDefinition(response.toString())
            } else {
                // If the request failed, store a message
                definitionResult = "Error: Unable to fetch definition"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            definitionResult = "Error: Exception occurred"
        }
    }

    // Getter method to access the result
    fun getResult(): String? {
        return definitionResult
    }

    private fun parseFirstDefinition(jsonResponse: String): String? {
        // A simple way to parse the first definition (you can improve this with a proper JSON parser)
        val definitionRegex = """"definition":"(.*?)"""".toRegex()
        val match = definitionRegex.find(jsonResponse)
        return match?.groupValues?.get(1) // Returns the first definition
    }
}
