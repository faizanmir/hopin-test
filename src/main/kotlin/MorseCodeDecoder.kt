class MorseCodeDecoder {

    fun performMorseCodeDecoding(input: String?) {

        println(input?.let { convertFromMorseCode(it) })
    }

    private fun convertFromMorseCode(input: String) {
        var inputHasError = false
        val errorIndices = arrayListOf<Int>()
        var i = 0
        var possibleValues:HashSet<String> = hashSetOf()

        input.map {
            if (!(it == '.' || it == '-')) {
                errorIndices.add(i)
                inputHasError = true
            }
            i++
        }

        if (inputHasError) {
            when (errorIndices.size) {
                1 -> {
                    possibleValues =  calculationForSingleError(input, errorIndices[0])
                }
                2 -> {
                    possibleValues =  calculationsForInputWithTwoErrors(input, errorIndices[0], errorIndices[1])
                }
                3 -> {
                    possibleValues =  calculationsForInputWithThreeErrors(input, errorIndices[0], errorIndices[1], errorIndices[2])
                }
                else -> {
                    println("Invalid input")
                }
            }
        } else {
            println("Morse code  = ${getMorseCode(input)}")
        }
        if (possibleValues.isEmpty().not()) {
            println("Errors were found in the code\nreturning all possible combinations\n ${possibleValues.filter { it.isNotBlank() }}")
        }
    }

    private fun calculationForSingleError(input: String, index: Int):HashSet<String> {
        val possibleValues = hashSetOf<String>()
        val charArray = input.toCharArray()
        charArray.mapIndexed { idx: Int, c: Char ->
            if (index == idx) {
                performConversion(charArray, idx, possibleValues)
            }
        }
        return  possibleValues;
    }

    private fun calculationsForInputWithTwoErrors(input: String, firstIndex: Int, secondIndex: Int):HashSet<String> {
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
        return  possibleValues;
    }

    private fun calculationsForInputWithThreeErrors(input: String, firstIndex: Int, secondIndex: Int, thirdIndex: Int):HashSet<String> {
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
        return  possibleValues;
    }


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


    private fun getMorseCode(input: String): String {

        println("Code for conversion  =  $input")

        when (input.toLowerCase()) {
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