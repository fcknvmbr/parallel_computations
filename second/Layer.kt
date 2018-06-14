
class Layer(layerSize: Int) {

    val neurons = Array(
            layerSize,
            { Node() }
    )

    fun connect(layer: Layer, type: ConnectionType = ConnectionType.ALL_TO_ALL) =
        when (type) {
            ConnectionType.ALL_TO_ALL -> {
                this.neurons.forEach { n ->
                    layer.neurons.forEach { on ->
                        n.connect(on, random(-1.0, 1.0))
                    }
                }
            }
            ConnectionType.ALL_TO_ELSE -> {
                this.neurons.forEach { n ->
                    layer.neurons.forEach { on ->
                        if (n != on) {
                            n.connect(on, random(-1.0, 1.0))
                        }
                    }
                }
            }
            ConnectionType.ONE_TO_ONE -> {
                this.neurons.forEachIndexed { index, n ->
                    n.connect(layer.neurons[index], random(-1.0, 1.0))
                }
            }
        }

    fun activate(input: DoubleArray) =
        input.mapIndexed { index, value ->
            neurons[index].activate(value)
        }.toDoubleArray()

    fun activate() =
        neurons.map {
            it.activate()
        }.toDoubleArray()

}