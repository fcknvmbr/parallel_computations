
class Connection(val source: Node, var weight: Double, val target: Node)

enum class ConnectionType {
    ALL_TO_ALL,
    ALL_TO_ELSE,
    ONE_TO_ONE
}
