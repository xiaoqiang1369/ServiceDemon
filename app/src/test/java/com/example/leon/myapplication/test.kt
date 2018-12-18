package com.example.leon.myapplication

/**
 *   description:
 *   created by crx on 2018/12/12 17:46
 */
class TestNull{
    fun testNull(arg: String){
        System.out.println("inTestNull")
        var length: Int? = 0
        length = (arg as? Int)?.plus(1)
        System.out.println("length = $length")
    }
}
