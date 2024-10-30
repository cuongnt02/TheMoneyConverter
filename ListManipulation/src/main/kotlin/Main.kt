package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val n = 6
    val input = listOf(3, 7, 1, 2, 6, 4)
    val sum = input.sum()
    val sum_origin = IntRange(1, n + 1).sum()
    println(sum_origin - sum)
}