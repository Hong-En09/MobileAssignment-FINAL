package com.example.mobileassignment.ui.ProductList

import android.content.Context
import android.content.Intent
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
import kotlinx.android.synthetic.main.fragment_productlist.*


class ProductListFragment: Fragment() {


    private var _binding: FragmentProductlistBinding? = null

    private val binding get() = _binding!!

    var modalList = ArrayList<ProductList>()
    var names = arrayOf(
        "Carrot",
        "Broccoli",
        "Cucumber",
        "Potato",
        "Tomato",
        "Egg",
        "Salmon"
    )

    var images = intArrayOf(
        R.drawable.carrot,
        R.drawable.broccoli,
        R.drawable.cucumber,
        R.drawable.potato,
        R.drawable.tomato,
        R.drawable.egg,
        R.drawable.salmon

    )

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

        for(i in names.indices){
            modalList.add(ProductList(names[i],images[i]))
        }
        var customAdapter = CustomAdapter(modalList, requireContext())

        gridView.adapter = customAdapter

        /*gridView.setOnItemClickListener{ adapterView, view, i, l ->
            var intent = Intent(requireContext(), AddNewRequestFragment::class.java)
            //intent.putExtra("data", modalList[i])
            intent.putExtra("data", modalList[i])
            startActivity(intent)
        }*/

        gridView.setOnItemClickListener{ _, view, i, _ ->
            //var modal = ProductList(modalList[i].name.toString(), modalList[i].image!!.toInt())
            //var intent = Intent(requireContext(), AddNewRequestFragment::class.java)
            //val bundle = Bundle()
            //bundle.putSerializable("amount", modalList[i])
            //view.findNavController().navigate(R.id.nav_addNewRequest, bundle)


            var intent = Intent (view.context, AddNewRequestActivity::class.java)
            intent.putExtra("data", modalList[i])
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
            imageView?.setImageResource(itemProduct[position].image!!)

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