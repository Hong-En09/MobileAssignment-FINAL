package com.example.mobileassignment.Entity

import java.io.Serializable

class ProductPhoto : Serializable{

    var name:String? = null
    var url:String? = null;

    constructor(name:String, url:String){
        this.name = name;
        this.url = url;
    }
}