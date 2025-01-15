package ro.pub.cs.systems.eim.practicaltest02v2


import com.google.gson.annotations.SerializedName

data class WordResponse(
    @SerializedName("word")
    val word: String,
    @SerializedName("results")
    val meanings: List<Meaning>
)

data class Meaning(
    @SerializedName("partOfSpeech")
    val partOfSpeech: String,
    @SerializedName("definitions")
    val definitions: List<Definition>
)

data class Definition(
    @SerializedName("definition")
    val definition: String
)
