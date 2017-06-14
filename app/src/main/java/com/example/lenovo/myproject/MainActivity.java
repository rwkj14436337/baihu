package com.example.lenovo.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView ding_view;
    private RadioGroup actors_rgp;
    private RadioButton radioButtonThree;
    private int actors=4;
    private EditText editText[][]=new EditText[3][4];
    private int huoNiao[]={0,0,0,0};
    private EditText price_edit;
    private double price=0.5;
    private int tuoNiao[]={0,0,0,0};
    private int huXi[]={0,0,0,0};
    double result[]={0,0,0,0};
    private TextView result_view[]=new TextView[4];
    private Button calculate_btn,clean_btn,exit_btn;
    private MyCalculateOnClickListener myCal = new MyCalculateOnClickListener();
    private MyOnFocusChangListener myOnFocusChangListener =new MyOnFocusChangListener();
    private MyClearOnClickListener myClearOnClickListener = new MyClearOnClickListener();

    class MyOnCheckChangListener implements RadioGroup.OnCheckedChangeListener{
        //选择参与人数时执行
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            clear();
            boolean tag;
            if (radioButtonThree.getId()==checkedId){
                actors=3;
                tag=false;
            }else {
                actors=4;
                tag=true;
            }
            for (int i=0;i<3;i++){
                editText[i][3].setEnabled(tag);
            }
            result_view[3].setEnabled(tag);
            ding_view.setEnabled(tag);
        }
    }
    class MyCalculateOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            init();
            calculate();
            view();
        }
    }
    //比较大小返回值
    private int compare(int x,int y){
        if (x==y) return 0;
        return x>y?1:-1;
    }

    //四舍五入
    private int myInit(int huxi){
        int tag =1,huxi_abs;
        huxi_abs=Math.abs(huxi);
        if(huxi<0)tag=-1;
        if (huxi_abs%10>=5)
            huxi_abs=(huxi_abs/10+1)*10;
        else
            huxi_abs=huxi_abs/10*10;
        return huxi_abs*tag;
    }
    class MyOnFocusChangListener implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText editText=(EditText) v;
            if (hasFocus){
                editText.setText("");
            }else {
                if (editText.getText().toString().equals("")) {
                    if (editText.getId() == R.id.price)
                        editText.setText("0.5");
                    else
                        editText.setText("0");
                }
            }
        }

    }
    class MyClearOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            clear();
        }
    }
    //清除方法
    private void clear(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                editText[i][j].setText("0");
            }
        }
        for (int j=0;j<4;j++){
            result_view[j].setText("0");
        }
    }
    //设置焦点改变方法
    private void setEditFocusListener(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                editText[i][j].setOnFocusChangeListener(myOnFocusChangListener);
            }
        }
        price_edit.setOnFocusChangeListener(myOnFocusChangListener);
    }

    //检查是否有空的文本框
    private void init() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (editText[i][j].getText().toString().equals(""))
                    editText[i][j].setText("0");
            }
        }
        if (price_edit.getText().toString().equals(""))
            price_edit.setText("0.5");
        try {
            for (int i=0;i<actors;i++){
                huoNiao[i] = Integer.parseInt(editText[0][i].getText().toString());
                tuoNiao[i] = Integer.parseInt(editText[1][i].getText().toString());
                huXi[i] = Integer.parseInt(editText[2][i].getText().toString());
            }
            price=Double.parseDouble(price_edit.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //计算方法
    private void calculate(){
        result[0]=result[1]=result[2]=result[3]=0;
        for (int i=0;i<actors;i++){
            for (int j=0;j<actors;j++){
                result[i]+=compare(huXi[i],huXi[j])*(tuoNiao[i]+tuoNiao[j]);
                result[i]+=(myInit(huXi[i]-myInit(huXi[j]))*(huoNiao[i]+1)*(huoNiao[j]+1))*price;
            }
        }
    }
    private void view(){
        for (int i = 0; i < actors; i++) {
            result_view[i].setText(String.valueOf(Math.round(result[i]*10/10.0)));
        }
    }

    private void findObject(){
        actors_rgp=(RadioGroup)findViewById(R.id.actors_rgp);
        radioButtonThree=(RadioButton) findViewById(R.id.radioButtonThree);
        price_edit=(EditText) findViewById(R.id.price);

        editText[0][0]=(EditText) findViewById(R.id.editText1);
        editText[0][1]=(EditText) findViewById(R.id.editText2);
        editText[0][2]=(EditText) findViewById(R.id.editText3);
        editText[0][3]=(EditText) findViewById(R.id.editText4);
        editText[1][0]=(EditText) findViewById(R.id.editText5);
        editText[1][1]=(EditText) findViewById(R.id.editText6);
        editText[1][2]=(EditText) findViewById(R.id.editText7);
        editText[1][3]=(EditText) findViewById(R.id.editText8);
        editText[2][0]=(EditText) findViewById(R.id.editText9);
        editText[2][1]=(EditText) findViewById(R.id.editText10);
        editText[2][2]=(EditText) findViewById(R.id.editText11);
        editText[2][3]=(EditText) findViewById(R.id.editText12);
        result_view[0]=(TextView) findViewById(R.id.textView12);
        result_view[1]=(TextView) findViewById(R.id.textView13);
        result_view[2]=(TextView) findViewById(R.id.textView14);
        result_view[3]=(TextView) findViewById(R.id.textView15);

        ding_view=(TextView) findViewById(R.id.textView7);
        calculate_btn=(Button)findViewById(R.id.calculate_btn);
        clean_btn=(Button)findViewById(R.id.clean_btn);
        exit_btn=(Button)findViewById(R.id.exit_btn);

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       findObject();
        actors_rgp.setOnCheckedChangeListener(new MyOnCheckChangListener());
        calculate_btn.setOnClickListener(myCal);
        setEditFocusListener();
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        clean_btn.setOnClickListener(myClearOnClickListener);

    }
}
