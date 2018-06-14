import java.lang.String.format
import kotlin.system.measureTimeMillis

object Main {

    @JvmStatic fun main(args: Array<String>) {
        val perceptron = Perceptron(2, 3, 3, 7)

        val trainData = mutableListOf<TrainData>()

        trainData.add(TrainData(doubleArrayOf(0.0, 0.0), doubleArrayOf(0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0)))
        trainData.add(TrainData(doubleArrayOf(0.0, 1.0), doubleArrayOf(0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0)))
        trainData.add(TrainData(doubleArrayOf(1.0, 0.0), doubleArrayOf(0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0)))
        trainData.add(TrainData(doubleArrayOf(1.0, 1.0), doubleArrayOf(1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0)))

        val trainer = Trainer(perceptron, 4)

        val timeNormal = measureTimeMillis { trainer.train(trainData, 10000) }
        val timeParallel = measureTimeMillis { trainer.trainParallel(trainData, 10000) }

        println("time normal  : $timeNormal ms")
        println("time parallel: $timeParallel ms")
    }
}
