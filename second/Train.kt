import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TrainData(val input: DoubleArray, val targetOutput: DoubleArray)

class Trainer(private val perceptron: Perceptron, private val threadsCount: Int = 1) {

    private val service: ExecutorService = Executors.newFixedThreadPool(threadsCount)

    private val errorSignal = mutableMapOf<Node, Double>()

    fun train(trainData: List<TrainData>, loops: Int = 1000, batchSize: Int = trainData.size) {
        for (i in 0 until loops) {
            trainData.shuffled().take(batchSize).forEach {
                train(it.input, it.targetOutput, 0.1)
            }
        }
    }

    fun trainParallel(trainData: List<TrainData>, loops: Int = 1000, batchSize: Int = trainData.size) {
        for (i in 0 until loops) {
            service.submit {
                trainData.shuffled().take(batchSize).forEach {
                    trainSync(it.input, it.targetOutput, 0.1)
                }
            }
        }
        service.shutdown()
        while (!service.isTerminated) { }
    }

    private fun trainSync(input: DoubleArray, targetOutput: DoubleArray, rate: Double) = synchronized(this@Trainer) {
        calculate(input)
        propagateError(targetOutput)
        updateWeights(rate)
    }

    private fun train(input: DoubleArray, targetOutput: DoubleArray, rate: Double) {
        calculate(input)
        propagateError(targetOutput)
        updateWeights(rate)
    }

    private fun calculate(input: DoubleArray): DoubleArray {
        return perceptron.activate(input)
    }

    private fun propagateError(target: DoubleArray) {
        errorSignal.clear()

        perceptron.layers.last().neurons.forEachIndexed { index, neuron ->
            errorSignal[neuron] = (neuron.value - target[index]) * neuron.derivative
        }

        perceptron.layers.reversed().drop(1).forEach { layer ->
            layer.neurons.forEach { neuron ->
                errorSignal[neuron] = neuron.outbound.sumByDouble { connection ->
                    errorSignal[connection.target]?.let {
                        connection.weight * it
                    } ?: 0.toDouble()
                } * neuron.derivative
            }
        }
    }

    private fun updateWeights(rate: Double) =
        perceptron.layers.forEach { layer ->
            layer.neurons.forEach { neuron ->
                errorSignal[neuron]?.let {
                    val delta = -rate * it
                    neuron.bias += delta
                    neuron.inbound.forEach { connection ->
                        connection.weight += delta * connection.source.value
                    }
                }
            }
        }
}