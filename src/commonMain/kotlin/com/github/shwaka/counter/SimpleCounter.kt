package com.github.shwaka.counter

typealias Context = String
typealias Name = String

object SimpleCounter {
    private val counts: MutableMap<Context, MutableMap<Name, Int>> = mutableMapOf()
    private val defaultContext = "__default__"

    private fun getMapForContext(context: Context): MutableMap<Name, Int> {
        return this.counts.getOrPut(context) { mutableMapOf() }
    }

    fun add(value: Any?, context: Context = this.defaultContext) {
        val name = value.toString()
        val mapForContext = this.getMapForContext(context)
        mapForContext[name] = mapForContext.getOrElse(name) { 0 } + 1
    }

    fun getCount(value: Any?, context: Context = this.defaultContext): Int {
        val name = value.toString()
        return this.getMapForContext(context).getOrElse(name) { 0 }
    }

    fun print(context: Context = this.defaultContext) {
        val countList = this.getMapForContext(context)
            .toList()
            .sortedBy { it.second }
        for ((name, count) in countList) {
            println("$count: $name")
        }
    }
}
