
class Perceptron(vararg layerSizes: Int) {

    val layers = Array(layerSizes.size, { Layer(layerSizes[it]) })

    private val inputLayer: Layer get() = layers.first()
    private val hiddenLayers: List<Layer> get() = layers.drop(1).dropLast(1)
    private val outputLayer: Layer get() = layers.last()

    init {
        (0 until layers.size - 1).forEach { layerIndex ->
            layers[layerIndex].connect(layers[layerIndex + 1])
        }
    }

    fun activate(input: DoubleArray): DoubleArray {
        inputLayer.activate(input)
        hiddenLayers.forEach { it.activate() }
        return outputLayer.activate()
    }
}