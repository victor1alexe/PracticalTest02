package ro.pub.cs.systems.eim.practicaltest02v2

import java.io.*
import java.net.Socket

class SocketClient(private val host: String, private val port: Int) {

    fun requestDefinition(word: String): String? {
        var definition: String? = null
        try {
            val socket = Socket(host, port)
            val writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

            // Send the word to the server
            writer.write(word)
            writer.newLine()
            writer.flush()

            // Receive the definition from the server
            definition = reader.readLine()

            socket.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return definition
    }
}
