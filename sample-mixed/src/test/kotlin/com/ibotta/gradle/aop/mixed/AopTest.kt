package com.ibotta.gradle.aop.mixed

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AopTest {
    @MockK(relaxed = true) private lateinit var mockMessageListener: MessageListener
    private val target = KotlinTargetExample()

    @BeforeEach
    fun before() {
        MockKAnnotations.init(this)
    }

    @Test
    fun givenAOPBeforeHook_whenTargetInvoked_thenAOPMethodRuns() {
        target.demonstrateKotlinAOP(mockMessageListener)
        verify { mockMessageListener.onMessage(any(), CallerType.BEFORE_HOOK) }
    }

    @Test
    fun givenAOPAfterHook_whenTargetInvoked_thenAOPMethodRuns() {
        target.demonstrateKotlinAOP(mockMessageListener)
        verify { mockMessageListener.onMessage(any(), CallerType.AFTER_HOOK) }
    }
}