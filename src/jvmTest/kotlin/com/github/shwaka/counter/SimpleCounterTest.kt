package com.github.shwaka.counter

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SimpleCounterTest : StringSpec({
    "test" {
        val foo = "foo"
        SimpleCounter.add(foo)
        SimpleCounter.add(1)
        SimpleCounter.add(foo)
        SimpleCounter.getCount(foo) shouldBe 2
        SimpleCounter.getCount(1) shouldBe 1
        SimpleCounter.clearAll()
        SimpleCounter.getCount(foo) shouldBe 0
    }
})
