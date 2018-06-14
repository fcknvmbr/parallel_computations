import java.lang.String.format

object Main {

    @JvmStatic fun main(args: Array<String>) {

        val perceptron = Perceptron(2, 3, 3, 7)

        val trainData = mutableListOf<TrainData>()

        trainData.add(TrainData(doubleArrayOf(0.0, 0.0), doubleArrayOf(0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0)))
        trainData.add(TrainData(doubleArrayOf(0.0, 1.0), doubleArrayOf(0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0)))
        trainData.add(TrainData(doubleArrayOf(1.0, 0.0), doubleArrayOf(0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0)))
        trainData.add(TrainData(doubleArrayOf(1.0, 1.0), doubleArrayOf(1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0)))

        val trainer = Trainer(perceptron, 8)

        val timeNormal = measure { trainer.train(trainData, 10000) } / 1_000_000

        val timeParallel = measure { trainer.trainParallel(trainData, 10000) } / 1_000_000

        println("time normal  : $timeNormal ms")
        println("time parallel: $timeParallel ms")
    }

    private fun measure(function: () -> Unit): Long {
        val before = System.nanoTime()
        function.invoke()
        return System.nanoTime() - before
    }

}
