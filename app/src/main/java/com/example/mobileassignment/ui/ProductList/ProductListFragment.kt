package com.example.mobileassignment.ui.ProductList

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobileassignment.Entity.ProductList
import com.example.mobileassignment.R
import com.example.mobileassignment.databinding.FragmentProductlistBinding
import com.example.mobileassignment.ui.CustomerFolder.AddNewRequestActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_productlist.*
import java.io.ByteArrayOutputStream


class ProductListFragment: Fragment() {


    private var _binding: FragmentProductlistBinding? = null

    private val binding get() = _binding!!

    var modalList = ArrayList<ProductList>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductlistBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Prevent Duplicate
        modalList.clear()
        //Call Firebase (root)
        val databaseRef = FirebaseDatabase.getInstance().reference.child("product")
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(ds in snapshot.children){
                    val names = ds.child("name").getValue(String::class.java).toString()
                    val url = ds.child("url").getValue(String::class.java).toString()
                    if(url != ""){
                        val storageRef = FirebaseStorage.getInstance().reference

                        val photoRef = storageRef.child("images/"+url)
                        val ONE_MEGABYTE = (1920 * 1080).toLong()
                        photoRef.getBytes(ONE_MEGABYTE)
                            .addOnSuccessListener { bytes ->
                                //convert into bitmap
                                val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                                modalList.add(ProductList(names,bmp))
                                var customAdapter = CustomAdapter(modalList, requireContext())
                                gridView.adapter = customAdapter

                            }.addOnFailureListener(OnFailureListener {

                            })

                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        gridView.setOnItemClickListener{ _, view, i, _ ->

            //address
            var intent = Intent (view.context, AddNewRequestActivity::class.java)
            //take array data
            val intentString : String? = modalList[i].name
            intent.putExtra("dataString", intentString)
            val intentBitMap : Bitmap? = modalList[i].image
            //Convert from bitmap to byte
            val stream = ByteArrayOutputStream()
            intentBitMap!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray: ByteArray = stream.toByteArray()
            intent.putExtra("dataBitmap", byteArray)
            startActivity(intent)
        }

    }

    class CustomAdapter(
        var itemProduct: ArrayList<ProductList>,
        var context: Context
    ) : BaseAdapter(){

        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            var view = view;
            if(view == null){
                view = layoutInflater.inflate(R.layout.productlist_layout,viewGroup,false);
            }

            var tvImageName = view?.findViewById<TextView>(R.id.imageName)
            var imageView = view?.findViewById<ImageView>(R.id.imageViewProduct);

            tvImageName?.text = itemProduct[position].name;
            imageView?.setImageBitmap(itemProduct[position].image)

            return view!!;
        }

        override fun getItem(p0: Int): Any {
            return itemProduct[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return itemProduct.size
        }

    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}