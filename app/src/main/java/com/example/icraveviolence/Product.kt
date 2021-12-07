package com.example.icraveviolence


data class Product(
    var productId:String?="",
    var type:String?="",
    var name: String?="",
    var protein: String?="",
    var energy: String?="",
    var fat: String?="",
    var water: String?="",
    var carbohydrates: String?="",
    var calcium: String?="",
    var phosphor: String?="",
    var magnesium: String?="",
    var sodium: String?="",
    var potassium: String?="",
    var chlorine: String?="",
    var iron: String?="",
    var zinc: String?="",
    var Copper: String?="",
    var manganese: String?="",
    var iodine: String?="",
    var selenium: String?="",
    var taurine: String?="",
    var vita: String?="",
    var vitd: String?="",
    var vite: String?="",
    var vitk: String?="",
    var vitb1: String?="",
    var vitb2: String?="",
    var vitb3: String?="",
    var vitb5: String?="",
    var vitb6: String?="",
    var vitb7: String?="",
    var vitb9: String?="",
    var vitb12: String?="",
    var vitc: String?="",
    var linoleicAcid: String?="",
    var arachidonicAcid: String?="",) {
    override fun toString(): String {
        return name.toString();
    }


}

