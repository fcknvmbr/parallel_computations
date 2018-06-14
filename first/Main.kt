package first

import firstimport.TestExecutor

object Main {

    @JvmStatic fun main(args: Array<String>) = with(TestExecutor()) {
        val normalTime = executeAndMeasureNormal(1000) / 1_000_000
        val parallelTime = executeAndMeasureParallel(1000, 4) / 1_000_000

        println("normal time:   $normalTime")
        println("parallel time: $parallelTime")
    }

}
