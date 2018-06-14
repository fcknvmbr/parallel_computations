package first

import firstimport.TestExecutor

object Main {

    @JvmStatic fun main(args: Array<String>) = with(TestExecutor()) {
        val normalTime = executeAndMeasureNormal(1000)
        val parallelTime = executeAndMeasureParallel(1000, 4)

        println("normal time:   $normalTime")
        println("parallel time: $parallelTime")
    }

}
