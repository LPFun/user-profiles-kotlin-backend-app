package com.lpfun.backend.common.cor

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ProfileDomainCorTest {

    @Test
    fun corTest() {
        val context = TestContext()
        val processor = cor<TestContext> {

            condition {
                true
            }

            error {

            }

            execute {
                a += "a"
            }

            handler {
                condition {
                    true
                }

                exec {
                    a += "b"
                }

                error {

                }
            }

            processor {
                handler {
                    exec {
                        a += "c"
                    }
                }
            }
        }

        runBlocking {
            processor.execute(context)
            assertEquals("abc", context.a)
        }

    }
}

class TestContext(var a: String = "")

