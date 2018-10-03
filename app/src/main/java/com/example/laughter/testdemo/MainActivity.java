package com.example.laughter.testdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.laughter.testdemo.fragment.ClothesFragment;
import com.example.laughter.testdemo.fragment.FoodFragment;
import com.example.laughter.testdemo.fragment.HotelFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //底部菜单栏3个TextView
    private TextView mTextClothes;
    private TextView mTextFood;
    private TextView mTextHotel;

    //3个Fragment
    private Fragment mClothesFragment;
    private Fragment mFoodFragment;
    private Fragment mHotelFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
            case R.id.text_clothes:
                setFragment(0);
                break;
            case R.id.text_food:
                setFragment(1);
                break;
            case R.id.text_hotel:
                setFragment(2);
                break;
        }
    }

    private void init(){
        //初始化控件
        mTextClothes = (TextView)findViewById(R.id.text_clothes);
        mTextFood = (TextView)findViewById(R.id.text_food);
        mTextHotel = (TextView)findViewById(R.id.text_hotel);

        //设置监听
        mTextClothes.setOnClickListener(this);
        mTextFood.setOnClickListener(this);
        mTextHotel.setOnClickListener(this);

        //设置第一个Fragment默认选中
        setFragment(0);
    }

    private void setFragment(int index){
        //获取Fragment管理器
        FragmentManager mFragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        //隐藏所有Fragment
        hideFragments(mTransaction);
        switch (index){
            default:
                break;
            case 0:
                mTextClothes.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTextClothes.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_clothes_pressed,0,0);
                if(mClothesFragment == null){
                    mClothesFragment = new ClothesFragment();
                    mTransaction.add(R.id.container, mClothesFragment);
                }else {
                    mTransaction.show(mClothesFragment);
                }
                break;
            case 1:
                mTextFood.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTextFood.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_food_pressed,0,0);
                if(mFoodFragment == null){
                    mFoodFragment = new FoodFragment();
                    mTransaction.add(R.id.container, mFoodFragment);
                }else {
                    mTransaction.show(mFoodFragment);
                }
                break;
            case 2:
                mTextHotel.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTextHotel.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_hotel_pressed,0,0);
                if(mHotelFragment == null){
                    mHotelFragment = new HotelFragment();
                    mTransaction.add(R.id.container, mHotelFragment);
                }else {
                    mTransaction.show(mHotelFragment);
                }
                break;
        }
        //提交事务
        mTransaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction){
        if(mClothesFragment != null){
            transaction.hide(mClothesFragment);
            mTextClothes.setTextColor(getResources().getColor(R.color.colorText));
            mTextClothes.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_clothes,0,0);
        }
        if(mFoodFragment != null){
            transaction.hide(mFoodFragment);
            mTextFood.setTextColor(getResources().getColor(R.color.colorText));
            mTextFood.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_food,0,0);
        }
        if(mHotelFragment != null){
            transaction.hide(mHotelFragment);
            mTextHotel.setTextColor(getResources().getColor(R.color.colorText));
            mTextHotel.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_hotel,0,0);
        }
    }
}
