package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val inventory = listOf(
        Product("Laptop", 999.99, 5),
        Product("Smartphone", 499.99, 10),
        Product("Tablet", 299.99, 0),
        Product("Smartwatch", 199.99, 3),
        )

    val totalValue = inventory.sumOf { it.price * it.quantity }
    val highestPriceProduct = inventory.maxBy { it.price }
    val inStock = productInStock(inventory, "Headphone")
    println("Total value of the inventory: $totalValue")
    println("Highest price product of the inventory: ${highestPriceProduct.name}")
    println("Headphones exists in the inventory: $inStock")
    println("Inventory sorted by price: ${sortListByPrice(inventory)}")
    println("Inventory sorted by quantity: ${sortListByQuantity(inventory)}")
}

fun productInStock(prodList: List<Product> , productName: String): Boolean {
    return prodList.any { product ->
        product.name == productName &&
        product.quantity > 0
    }
}

fun sortListByPrice(inventory: List<Product>): List<Product> {
    return inventory.sortedWith(compareBy(Product::price))
}

fun sortListByQuantity(inventory: List<Product>): List<Product> {
    return inventory.sortedWith(compareBy(Product::quantity))
}