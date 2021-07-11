class MarkDownToHtml {
    fun convertMarkdown( input:String?) {
        var command = ""
        var hashes = 0
        val lastHash = input!!.lastIndexOf("#")

        if (input[lastHash + 1] == ' ') {
            val list = input
                .trim()
                .split(" ")
                .filter { it.isNotBlank() }

            list.map {
                if (it.contains("#")) {
                    hashes = it.length
                } else {
                    command = it
                }
            }

            if (hashes <= 6) {
                println("command <h${hashes}>$command</h$hashes>")
            } else {
                println("Number of hashes exceeds 6")
            }

        } else {
            println("Input is invalid since there is no space between the last hash and the first character")
        }
    }
}