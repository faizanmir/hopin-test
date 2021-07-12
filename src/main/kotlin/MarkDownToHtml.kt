class MarkDownToHtml {
    //Converts a valid input to an HTML tag
    //Valid input for the function is something like #### TITLE
    //restrictions on hashes and no space between # and the command such as TITLE will lead to an invalid input
    fun convertMarkdown(input: String?) {
        if (input!!.contains("#")) {
            var command = ""
            var hashes = 0
            val lastHash = input.lastIndexOf("#")

            try {
                if (input[lastHash + 1] == ' ') {
                    val list = input
                        .trim() //To remove spaces from the start and the end
                        .split(" ") //split at first space
                        .filter { it.isNotBlank() } //remove spaces


                    list.map {
                        if (it.contains("#")) {
                            hashes = it.length //calculating number of hashes
                        } else {
                            command = it
                        }
                    }


                    when {
                        hashes in 1..6 -> {
                            //Everything checks out -- print the desires output
                            println("command <h${hashes}>$command</h$hashes>")
                        }
                        hashes > 6 -> {
                            println("Number of hashes greater than 6")
                        }
                    }


                } else {
                    println("Input is invalid since there is no space between the last hash and the first character")
                }
            } catch (e: Exception) {
                println("There seem to be no characters after #,Invalid input terminating...")
            }
        } else {
            println("No hashes found ,Invalid input")
        }
    }
}