package com.example.company.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val Listik:List<String> = listOf("", "0", "X")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,Listik)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner11:Spinner=findViewById(R.id.spinner11)
        spinner11.setAdapter(adapter)
        spinner11.setOnItemSelectedListener(this)

        val spinner12:Spinner=findViewById(R.id.spinner12)
        spinner12.setAdapter(adapter)
        spinner12.setOnItemSelectedListener(this)

        val spinner13:Spinner=findViewById(R.id.spinner13)
        spinner13.setAdapter(adapter)
        spinner13.setOnItemSelectedListener(this)

        val spinner21:Spinner=findViewById(R.id.spinner21)
        spinner21.setAdapter(adapter)
        spinner21.setOnItemSelectedListener(this)

        val spinner22:Spinner=findViewById(R.id.spinner22)
        spinner22.setAdapter(adapter)
        spinner22.setOnItemSelectedListener(this)

        val spinner23:Spinner=findViewById(R.id.spinner23)
        spinner23.setAdapter(adapter)
        spinner23.setOnItemSelectedListener(this)

        val spinner31:Spinner=findViewById(R.id.spinner31)
        spinner31.setAdapter(adapter)
        spinner31.setOnItemSelectedListener(this)

        val spinner32:Spinner=findViewById(R.id.spinner32)
        spinner32.setAdapter(adapter)
        spinner32.setOnItemSelectedListener(this)

        val spinner33:Spinner=findViewById(R.id.spinner33)
        spinner33.setAdapter(adapter)
        spinner33.setOnItemSelectedListener(this)
        status.setText("")

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }








    //var PREV:String?="PREV"
    var STATUS:String? = null

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //проверка на победную комбинацию

        fun BOX(i:Int,j:Int):String? {
            var childOfTab = tableLayout.getChildAt(i-1)
            if (childOfTab!=null&&childOfTab is TableRow) {
                var Row: TableRow = childOfTab
                var childOfRow = Row.getChildAt(j-1)
                if (childOfRow != null && childOfRow is Spinner) {
                    var Spin: Spinner = childOfRow
                    if(Spin.selectedItem.toString()!=null){
                        return Spin.selectedItem.toString()
                    }
                }
            }
            return null
        }

        when{
            BOX(1,1)==BOX(1,2)&&BOX(1,1)==BOX(1,3)->STATUS=BOX(1,1)
            BOX(2,1)==BOX(2,2)&&BOX(2,1)==BOX(2,3)->STATUS=BOX(2,1)
            BOX(3,1)==BOX(3,2)&&BOX(3,1)==BOX(3,3)->STATUS=BOX(3,1)
            BOX(1,1)==BOX(2,1)&&BOX(1,1)==BOX(3,1)->STATUS=BOX(1,1)
            BOX(1,2)==BOX(2,2)&&BOX(1,2)==BOX(3,2)->STATUS=BOX(1,2)
            BOX(1,3)==BOX(2,3)&&BOX(1,3)==BOX(3,3)->STATUS=BOX(1,3)
            BOX(1,1)==BOX(2,2)&&BOX(1,1)==BOX(3,3)->STATUS=BOX(1,1)
            BOX(3,1)==BOX(2,2)&&BOX(1,1)==BOX(1,3)->STATUS=BOX(3,1)
            else->{STATUS=""}
        }

        /*when{
            spinner11.selectedItem.toString()==spinner12.selectedItem.toString() && spinner11.selectedItem.toString()==spinner13.selectedItem.toString() -> {STATUS=spinner11.selectedItem.toString()}
            spinner21.selectedItem.toString()==spinner22.selectedItem.toString() && spinner21.selectedItem.toString()==spinner23.selectedItem.toString() -> {STATUS=spinner21.selectedItem.toString()}
            spinner31.selectedItem.toString()==spinner32.selectedItem.toString() && spinner31.selectedItem.toString()==spinner33.selectedItem.toString() -> {STATUS=spinner31.selectedItem.toString()}
            spinner11.selectedItem.toString()==spinner21.selectedItem.toString() && spinner11.selectedItem.toString()==spinner31.selectedItem.toString() -> {STATUS=spinner11.selectedItem.toString()}
            spinner12.selectedItem.toString()==spinner22.selectedItem.toString() && spinner12.selectedItem.toString()==spinner32.selectedItem.toString() -> {STATUS=spinner12.selectedItem.toString()}
            spinner13.selectedItem.toString()==spinner23.selectedItem.toString() && spinner13.selectedItem.toString()==spinner33.selectedItem.toString() -> {STATUS=spinner13.selectedItem.toString()}
            spinner11.selectedItem.toString()==spinner22.selectedItem.toString() && spinner11.selectedItem.toString()==spinner33.selectedItem.toString() -> {STATUS=spinner11.selectedItem.toString()}
            spinner31.selectedItem.toString()==spinner22.selectedItem.toString() && spinner31.selectedItem.toString()==spinner13.selectedItem.toString() -> {STATUS=spinner31.selectedItem.toString()}
            else -> {STATUS=""} //
        }*/

        //проверка на очередность и ничью
        var xCount:Int=0 //количество Х
        var oCount:Int=0 //количество 0
        var nonSelectItemCount:Int=0 // количество оставшихся пустых клеток
        for(i in 1..3) for(j in 1..3){
            when (BOX(i,j)) {
                "X" -> xCount++
                "0" -> oCount++
                else -> nonSelectItemCount++
            }
        }
        when{
            Math.abs(xCount-oCount)>1->STATUS="E"
            nonSelectItemCount==0&&STATUS!="0"&&STATUS!="X"->STATUS="Z"
        }

        //Вывод статуса на экран
        when{
            STATUS == "0" -> status.setText("0 won")
            STATUS == "X" -> status.setText("X won")
            STATUS == "Z" -> status.setText("Draw")
            STATUS == "E" -> status.setText("Invalid")
            else -> status.setText("")
        }
    }
}
