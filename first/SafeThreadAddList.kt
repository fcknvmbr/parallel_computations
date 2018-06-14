package first

class SafeThreadAddList<in T> {

    var size = 0

    private var last: Node<T>? = null
    private var first: Node<T>? = null

    data class Node<E> (var item: E?, var next: Node<E>?)

    fun addSafe(element: T) = synchronized(this@SafeThreadAddList, {
        if (size == 0) { linkFirst(element) } else { linkLast(element) }
    })

    fun add(element: T) = if (size == 0) { linkFirst(element) } else { linkLast(element) }

    private fun linkFirst(element: T) {
        val newNode = Node(item = element, next = null)
        first = newNode
        last = newNode
        size++
    }

    private fun linkLast(element: T) {
        val newNode = Node(item = element, next = null)
        last?.next = newNode
        last = newNode
        size++
    }

}