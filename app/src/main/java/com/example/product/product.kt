package com.example.product

class Product {

    var id: Int = 0
    var name: String = ""
    var quantity: Int = 0

    constructor(id: Int, name: String, quantity: Int) {
        this.id = id
        this.name = name
        this.quantity = quantity
    }

    constructor() {
    }
}


