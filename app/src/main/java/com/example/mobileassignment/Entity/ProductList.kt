package com.example.mobileassignment.Entity

import android.graphics.Bitmap
import java.io.Serializable

class ProductList : Serializable{

    var name:String? = null
    var image:Bitmap? = null;

    constructor(name:String, image:Bitmap){
        this.name = name;
        this.image = image;
    }

    constructor()
}