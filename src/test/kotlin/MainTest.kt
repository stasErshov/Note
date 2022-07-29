import org.junit.Assert.*
import org.junit.Test

class MainTest {
    @Test()
    fun test() {
        val e: Boolean = true
        val b: Boolean = mainTest.mainTestFun(e)
        assertEquals(false, b)
    }
    @Test()
    fun test1() {
        val e: Boolean = false
        val b: Boolean = mainTest.mainTestFun(e)
        assertEquals(true, b)
    }
}