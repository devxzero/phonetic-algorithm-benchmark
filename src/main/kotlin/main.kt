import org.apache.commons.codec.language.*

fun main() {
    val nameGroups = listOf(
        listOf("Katy anna", "Katie n", "Katie ana"),
        listOf("caty", "cati", "catti", "catie", "cathy", "cathi", "ketie", "kety", "keti", "kathy", "kathi", "kathie"),
        listOf("greatness", "Graetnes"),
        listOf("Smith", "Schmidt", "Smyth"),
        listOf("Catherine", "Katherine", "Katarina"),
        listOf("Teresa", "Theresa"),
        listOf("Johnathan", "Jonathan")
    )

    fun similarityScore(list: List<String>): Double {
        val largestCommonSize = list.groupBy { it }.values.map { it.size }.maxOrNull() ?: 0
        return largestCommonSize.toDouble() / list.size
    }

    val encoders = listOf(Caverphone(), Caverphone1(), Caverphone2(), ColognePhonetic(), DaitchMokotoffSoundex(), Metaphone(), DoubleMetaphone(), MatchRatingApproachEncoder(), Nysiis(), Soundex(), RefinedSoundex())

    val encoderScores = mutableMapOf<String, Double>()

    for (encoder in encoders) {
        val encoderName = encoder.javaClass.simpleName
        println("Encoder: $encoderName")

        for (nameGroup in nameGroups) {
            val encodedNames = mutableListOf<String>()

            for (name in nameGroup) {
                val encodeName = encoder.encode(name)
                encodedNames += encodeName
                println("- $name\t->\t$encodeName")
            }
            val similarityScore = similarityScore(encodedNames)
            println("Name group similarity score: $similarityScore")
            println()

            encoderScores.compute(encoderName) { _, prev -> if (prev == null) similarityScore else prev + similarityScore }
        }

        println("---------")
    }


    println("Encoders ordered by score descending")
    encoderScores.entries.sortedByDescending { it.value }.forEach {
        println("Encoder:${it.key}, score:${it.value}")
    }
}
