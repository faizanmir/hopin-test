import java.lang.Exception
import kotlin.system.exitProcess

var markDownToHtml: MarkDownToHtml = MarkDownToHtml()
var morseCodeDecoder = MorseCodeDecoder()
var sumCalculator = SumCalculator()
fun main(args: Array<String>) {

    while (true) {
        println("\nPlease enter your choice")
        println("1 for performing sum of numbers")
        println("2 for converting morse code to string")
        println("3 for converting Markdown to HTML")
        println("4 to exit")
        when (readLine()) {
            "1" -> {
                println("Please enter the value for performing the calculations")
                val range = readLine()
                try {
                    sumCalculator.performSum(range?.toInt())
                } catch (e: Exception) {
                    println("Oops! Something went wrong,Please try again")
                }
            }
            "2" -> {
                println("Please enter a character code to be converted into morse code")
                val input = readLine()
                morseCodeDecoder.performMorseCodeDecoding(input)
            }
            "3" -> {
                println("Please enter a Markdown")
                val markDown = readLine()
                markDownToHtml.convertMarkdown(markDown)
            }
            "4" -> {
                println("Exiting...")
                exitProcess(0)

            }else->{
                break;
            }
        }
    }
}














