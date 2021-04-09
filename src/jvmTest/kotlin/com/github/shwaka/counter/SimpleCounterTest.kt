package com.github.shwaka.counter

import io.kotest.core.spec.style.StringSpec

class SimpleCounterTest : StringSpec({
    "test" {
        SimpleCounter.hello()
    }
})
