object mainTest{
    fun mainTestFun(k : Boolean) : Boolean{
        if (k){
            return false
        }else{
            return true
        }
    }
}



class Main {
    fun main() {
        val e: Boolean = true
        val b: Boolean = mainTest.mainTestFun(e)

    }
}