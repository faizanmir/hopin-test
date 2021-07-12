class SumCalculator {

    /**Calculates sum based on divisibility by 3 and 5
     * [range]: is the maximum number for which you need to calculate the sum based on divisibility
     * **/

    fun performSum(range:Int?) {
        val numbers = arrayListOf<Int>()
        if (range != null) {
            for (i in 1..range) {
                if (i % 3 == 0) { //checking divisibility by 3
                    if (i % 5 != 0) {// Number shouldn't be divisible by 5 if it is divisible by 3
                        numbers.add(i)
                    }
                } else if (i % 5 == 0) {//checking divisibility by 5
                    if (i % 3 != 0) {// Number shouldn't be divisible by 3 if it is divisible by 5
                        numbers.add(i)
                    }
                }
            }

            var sum = 0
            numbers.forEach {
                sum += it //calculate sum
            }
            println("Sequence of numbers divisible by 3 and 5 but not both = $numbers")
            println("Sum is $sum")
        }
    }
}