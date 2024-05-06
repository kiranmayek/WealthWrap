package com.example.amex.adapters

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amex.R
import com.example.amex.activities.HomeScreenActivity
import com.example.amex.models.FInancialTypeModel
import com.example.amex.fragments.UserFInancialDetailsFragment

class FinancialTypeAdapter(private val FInancialTypeList:ArrayList<FInancialTypeModel>, private val activity: HomeScreenActivity):
    RecyclerView.Adapter<FinancialTypeAdapter.FInancialTypeViewHolder>() {

    class FInancialTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvFInancialType: TextView = itemView.findViewById(R.id.tvFInancialType)
        val ivFInancialType: ImageView = itemView.findViewById(R.id.ivFInancialType)
        val layout:LinearLayout = itemView.findViewById(R.id.layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FInancialTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_FInancial_type_item,parent,false)
        return FInancialTypeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return FInancialTypeList.size
    }

    var index = -1
    override fun onBindViewHolder(holder: FInancialTypeViewHolder, position: Int) {
        val FInancialType = FInancialTypeList[position]
        holder.tvFInancialType.text = FInancialType.FInancialType
        holder.ivFInancialType.setImageResource(FInancialType.FInancialIcon)
        holder.itemView.setOnClickListener (object:View.OnClickListener{
            override fun onClick(p0: View?) {
                val activity = p0!!.context as AppCompatActivity
                val UserFInancialDetailsFragment = UserFInancialDetailsFragment()

                //try
                val bundle = Bundle()
                bundle.putString("FInancialType", FInancialType.FInancialType)
                UserFInancialDetailsFragment.arguments = bundle

//                activity.supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainerView,UserFInancialDetailsFragment)
//                    .addToBackStack(null)
//                    .commit()

                val fragmentManager = activity.supportFragmentManager
                fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView, UserFInancialDetailsFragment)
                transaction.commit()

                index = position
                notifyDataSetChanged()
            }

        })

        if(index == position){
            holder.tvFInancialType.setTextColor(Color.BLACK)
            holder.layout.setBackgroundColor(Color.parseColor("#FDD962"))

            val color = ContextCompat.getColor(holder.itemView.context, R.color.black)
            holder.ivFInancialType.setColorFilter(color)

        }
        else{
            holder.tvFInancialType.setTextColor(Color.parseColor("#C6C7C9"))
            holder.layout.setBackgroundColor(Color.parseColor("#233238"))

            val color = ContextCompat.getColor(holder.itemView.context, R.color.myWhite)
            holder.ivFInancialType.setColorFilter(color)

        }

    }

}