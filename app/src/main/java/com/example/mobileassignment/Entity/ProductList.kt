package com.example.mobileassignment.Entity

import java.io.Serializable

class ProductList : Serializable{

    var name:String? = null
    var image:Int? = null;

    constructor(name:String, image:Int){
        this.name = name;
        this.image = image;
    }
}