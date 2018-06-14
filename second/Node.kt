
class Node {

    val inbound: MutableList<Connection> = mutableListOf()
    val outbound: MutableList<Connection> = mutableListOf()
    val derivative: Double get() = sigmoidDerivative(value)

    var value: Double = 0.0
    var bias: Double = random(-1.0, 1.0)

    fun connect(targetNode: Node, weight: Double): Connection {
        val connection = Connection(this, weight, targetNode)
        outbound += connection
        targetNode.inbound += connection
        return connection
    }

    fun activate() =
        sigmoid(bias + inbound.sumByDouble { it.source.value * it.weight })


    fun activate(value: Double): Double {
        this.value = value
        return this.value
    }

}