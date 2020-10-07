package com.lpfun.backend.profile.domain

import com.lpfun.backend.profile.domain.cor.cor
import kotlin.test.Test

internal class ProfileDomainCorTest {

    @Test
    fun corTest() {
        val context = TestContext()
        cor<TestContext> {

            condition {
                true
            }

            error {

            }

            execute {
                println("Test 1")
            }

            handler {
                condition {
                    true
                }

                exec {
                    println("Test 2")
                }

                error {

                }
            }

            processor {
                handler {

                }
            }
        }
    }
}

class TestContext

