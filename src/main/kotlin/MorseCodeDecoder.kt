class MorseCodeDecoder {

    fun performMorseCodeDecoding(input: String?) {
        input?.let { convertFromMorseCode(it) }
    }


    //Function performs the conversion to String from morse code for a single
    //character
    private fun convertFromMorseCode(input: String) {
        ///Checking for unwanted strings such as "???"
        if (input.contains(".") || input.contains("-")) {
            var inputHasError = false
            val errorIndices = arrayListOf<Int>()
            var i = 0
            var possibleValues: HashSet<String> = hashSetOf()

            //checking if the input has invalid characters or errors
            //these are then added in an arraylist to maintain the error indices

            input.map {
                if (!(it == '.' || it == '-')) {
                    errorIndices.add(i)
                    inputHasError = true
                }
                i++
            }

            //if inputHasError is true ->Error state has occurred & we have an unknown characters
            //depending upon the number of characters which are deemed errors ,separate functions handle the
            //process for code cleanliness and simplicity.
            //if one error is found we have 2 possibilities 1st that the replaced character is "." and
            //second that it is a "-",hence we need to show both,
            //this case becomes a bit complicated with the
            // next two cases as we have to check for previous errors that maybe present
            // also and form different combinations based on this premise
            //Also since the length of no morse code appeared >4 for the given problem ,
            // we assume that if all the 4 characters are "?",the input is invalid
            //
            if (inputHasError) {
                when (errorIndices.size) {
                    1 -> {
                        possibleValues = calculationForSingleError(input, errorIndices[0])
                    }
                    2 -> {
                        possibleValues = calculationsForInputWithTwoErrors(input, errorIndices[0], errorIndices[1])
                    }
                    3 -> {
                        possibleValues = calculationsForInputWithThreeErrors(
                            input,
                            errorIndices[0],
                            errorIndices[1],
                            errorIndices[2]
                        )
                    }
                    else -> {
                        println("Invalid input")
                    }
                }
            } else {
                //No errors in the provided input we can go ahead and perform the conversion
                println("Morse code  = ${getMorseCode(input)}")
            }
            if (possibleValues.isEmpty().not()) {
                println("Errors were found in the code returning all possible combinations \n ${possibleValues.filter { it.isNotBlank() }}")
            }
        }
    }

    /**
     * This function resolves morse code with two errors example "_?"
     * the function will provide all possible combinations for the above string in a hashset
     * Error indices are required to form different combinations
     * [index] ->Index of the error
     * **/
    private fun calculationForSingleError(input: String, index: Int): HashSet<String> {
        val possibleValues = hashSetOf<String>()
        val charArray = input.toCharArray()
        charArray.mapIndexed { idx: Int, c: Char ->
            if (index == idx) {
                performConversion(charArray, idx, possibleValues)
            }
        }
        return possibleValues
    }

    /**
     * This function resolves morse code with two errors example "_??"
     * the function will provide all possible combinations for the above string in a hashset
     * Error indices are required to form different combinations
     * [firstIndex],[secondIndex] ->Indices of the errors as they occur
     * **/
    private fun calculationsForInputWithTwoErrors(input: String, firstIndex: Int, secondIndex: Int): HashSet<String> {
        val possibleValues = hashSetOf<String>()
        val charArray = input.toCharArray()
        charArray.mapIndexed { idx, c ->
            when (idx) {
                secondIndex -> {
                    charArray[firstIndex] = '.'
                    performConversion(charArray, idx, possibleValues)
                    charArray[firstIndex] = '-'
                    performConversion(charArray, idx, possibleValues)
                }
            }
        }
        return possibleValues
    }

    /**
     * This function resolves morse code with three errors example "_???"
     * the function will provide all possible combinations for the above string in a hashset
     * Error indices are required to form different combinations
     * [firstIndex],[secondIndex],[thirdIndex] ->Indices of the errors as they occur
     * **/


    private fun calculationsForInputWithThreeErrors(
        input: String,
        firstIndex: Int,
        secondIndex: Int,
        thirdIndex: Int
    ): HashSet<String> {
        val possibleValues = hashSetOf<String>()
        val charArray = input.toCharArray()
        charArray.mapIndexed { idx, c ->
            when (idx) {
                thirdIndex -> {
                    charArray[firstIndex] = '.'
                    charArray[secondIndex] = '.'
                    performConversion(charArray, idx, possibleValues)
                    charArray[firstIndex] = '.'
                    charArray[secondIndex] = '-'
                    performConversion(charArray, idx, possibleValues)
                    charArray[firstIndex] = '-'
                    charArray[secondIndex] = '.'
                    performConversion(charArray, idx, possibleValues)
                    charArray[firstIndex] = '-'
                    charArray[secondIndex] = '-'
                    performConversion(charArray, idx, possibleValues)
                }
            }
        }
        return possibleValues
    }


    /**
     * This function provides the possible values for a single index when there is error
     * [charArray] is the array which is formed by converting the input to [CharArray]
     * [idx] is the index for which possible combinations need to be formed
     * [possibleValues] is a hashset which stores the morse code after processing
     * USING [HashSet] to avoid duplications in characters
     * ***/

    private fun performConversion(
        charArray: CharArray,
        idx: Int,
        possibleValues: HashSet<String>
    ) {
        charArray[idx] = '.'
        possibleValues.add(getMorseCode(charArray.toMutableList().joinToString("")))
        charArray[idx] = '-'
        possibleValues.add(getMorseCode(charArray.toMutableList().joinToString("")))
    }


    /**
     * This function performs the conversion from  morse code to string
     * [input] :- is the morse code
     * **/
    private fun getMorseCode(input: String): String {
        when (input) {
            ".-" -> return "a"
            "-..." -> return "b"
            "-.-." -> return "c"
            "-.." -> return "d"
            "." -> return "e"
            "..-." -> return "f"
            "--." -> return "g"
            "...." -> return "h"
            ".." -> return "i"
            ".---" -> return "j"
            "-.-" -> return "k"
            ".-.." -> return "l"
            "--" -> return "m"
            "-." -> return "n"
            "---" -> return "o"
            ".--." -> return "p"
            "--.-" -> return "q"
            ".-." -> return "r"
            "..." -> return "s"
            "-" -> return "t"
            "..-" -> return "u"
            "...-" -> return "v"
            ".--" -> return "w"
            "-..-" -> return "x"
            "-.--" -> return "y"
            "--.." -> return "z"
            else -> return ""
        }

    }

}