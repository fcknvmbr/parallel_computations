package firstimport

import first.SafeThreadAddList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

class TestExecutor {

    fun executeAndMeasureParallel(iterations: Int, threadCount: Int): Long {
        val list = SafeThreadAddList<Int>()
        val service: ExecutorService = Executors.newFixedThreadPool(threadCount)

        return measureTimeMillis {
            (1..iterations).forEach {
                service.submit { list.addSafe(1) }
            }
            service.shutdown()
            while (!service.isTerminated) { }
        }

    }

    fun executeAndMeasureNormal(iterations: Int): Long {
        val list = SafeThreadAddList<Int>()

        val time = measureTimeMillis {
            (1..iterations).forEach {
                list.add(1)
            }
        }

        assertEquals(iterations, list.size)
        return time
    }

}