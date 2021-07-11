class SumCalculator {
    fun performSum(range:Int?) {
        val numbers = arrayListOf<Int>()
        if (range != null) {
            for (i in 1..range) {
                if (i % 3 == 0) {
                    if (i % 5 != 0) {
                        numbers.add(i)
                    }
                } else if (i % 5 == 0) {
                    if (i % 3 != 0) {
                        numbers.add(i)
                    }
                } else {
                    println("Number not divisible by 5 or 3 $i")
                }
            }

            var sum = 0
            numbers.forEach {
                sum += it
            }
            println(numbers)
            println("Sum is $sum")
        }
    }
}