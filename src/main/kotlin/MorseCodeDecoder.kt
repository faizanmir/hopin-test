class MorseCodeDecoder {

    fun performMorseCodeDecoding(input: String?) {
        input?.let { convertFromMorseCode(it) }
    }
    //Function performs the conversion to String from morse code for a single
    //character
    private fun convertFromMorseCode(input: String) {
     //Checking for input length
        if(input.length<=4) {
            ///Checking for strings such as "???"
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
                    println("Decoded character = ${getHumanReadableString(input)}")
                }
                if (possibleValues.isEmpty().not()) {
                    println("Errors were found in the code returning all possible combinations \n ${possibleValues.filter { it.isNotBlank() }}")
                }
            } else {
                ///String has no valid inputs e.g ??? or ????
                ///This will now try and match based on length of the code entered
                getHumanReadableString(input)
            }
        }else {
            println("This algorithm handles morse code for inputs with up-to 4 characters")
        }
    }

    /**
     * This function resolves morse code with two errors example "_?"
     * the function will provide all possible combinations for the above string in a hashset
     * Error indices are required to form different combinations
     * [index] -> Index of the error
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
        possibleValues.add(getHumanReadableString(charArray.toMutableList().joinToString("")))
        charArray[idx] = '-'
        possibleValues.add(getHumanReadableString(charArray.toMutableList().joinToString("")))
    }


    /**
     * This function performs the conversion from  morse code to string
     * [input] :- is the morse code
     * */
    private fun getHumanReadableString(input: String): String {
        val hashMap  =  hashMapOf<String,String>()
        hashMap.also { 
            it["ab"] = "a"
            it[".-"] =  "a"
            it["-..."] =  "b"
            it["-.-."]  = "c"
            it["-.."] =  "d"
            it["."] = "e"
            it["..-."] = "f"
            it["--."] =  "g"
            it["...."] = "h"
            it[".." ]="i"
            it[".---"] =  "j"
            it["-.-"] = "k"
            it[".-.."] = "l"
            it["--"] = "m"
            it["-."] = "n"
            it["---"] = "o"
            it[".--."] = "p"
            it["--.-"] = "q"
            it[".-."] ="r"
            it["..."] = "s"
            it["-"] = "t"
            it["..-"] =  "u"
            it["...-"] = "v"
            it[".--" ] ="w"
            it["-..-"]= "x"
            it["-.--"] = "y"
            it["--.."] = "z"
        }
        if(hashMap.containsKey(input))
        {
            return hashMap[input]!!
        }else {
            ///Checking if all characters have been changed to something other than . and -
            if(input.contains(".").not()&&input.contains("-").not()) {
                val valuesWithSameLength = arrayListOf<String>()
                println("Encountered invalid input returning value based on string length for string $input")
                val approxValues = hashMap.keys.filter { it.length == input.length }
                approxValues.forEach {
                    valuesWithSameLength.add(hashMap[it]!!)
                }
                println(valuesWithSameLength)
            }
        }
        return ""
    }

}