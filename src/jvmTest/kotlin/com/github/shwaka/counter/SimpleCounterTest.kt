package com.github.shwaka.counter

import io.kotest.core.spec.style.StringSpec

class SimpleCounterTest : StringSpec({
    "test" {
        SimpleCounter.add("foo")
        SimpleCounter.add(1)
        SimpleCounter.add("foo")
        SimpleCounter.print()
    }
})
