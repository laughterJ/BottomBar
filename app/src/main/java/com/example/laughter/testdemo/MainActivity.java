package com.example.laughter.testdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
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

    //标记当前显示的Fragment
    private int fragmentId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        init();
        //根据传入的Bundle对象判断Activity是正常启动还是销毁重建
        if(savedInstanceState == null){
            //设置第一个Fragment默认选中
            setFragment(0);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //通过onSaveInstanceState方法保存当前显示的fragment
        outState.putInt("fragment_id",fragmentId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        //通过FragmentManager获取保存在FragmentTransaction中的Fragment实例
        mClothesFragment = mFragmentManager.findFragmentByTag("clothes_fragment");
        mFoodFragment = mFragmentManager.findFragmentByTag("food_fragment");
        mHotelFragment = mFragmentManager.findFragmentByTag("hotel_fragment");
        //恢复销毁前显示的Fragment
        setFragment(savedInstanceState.getInt("fragment_id"));
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
        mTextClothes = findViewById(R.id.text_clothes);
        mTextFood = findViewById(R.id.text_food);
        mTextHotel = findViewById(R.id.text_hotel);

        //设置监听
        mTextClothes.setOnClickListener(this);
        mTextFood.setOnClickListener(this);
        mTextHotel.setOnClickListener(this);
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
                fragmentId = 0;
                //设置菜单栏为选中状态（修改文字和图片颜色）
                mTextClothes.setTextColor(getResources()
                        .getColor(R.color.colorTextPressed));
                mTextClothes.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_clothes_pressed,0,0);
                //显示对应Fragment
                if(mClothesFragment == null){
                    mClothesFragment = new ClothesFragment();
                    mTransaction.add(R.id.container, mClothesFragment,
                            "clothes_fragment");
                }else {
                    mTransaction.show(mClothesFragment);
                }
                break;
            case 1:
                fragmentId = 1;
                mTextFood.setTextColor(getResources()
                        .getColor(R.color.colorTextPressed));
                mTextFood.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_food_pressed,0,0);
                if(mFoodFragment == null){
                    mFoodFragment = new FoodFragment();
                    mTransaction.add(R.id.container, mFoodFragment,
                            "food_fragment");
                }else {
                    mTransaction.show(mFoodFragment);
                }
                break;
            case 2:
                fragmentId = 2;
                mTextHotel.setTextColor(getResources()
                        .getColor(R.color.colorTextPressed));
                mTextHotel.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_hotel_pressed,0,0);
                if(mHotelFragment == null){
                    mHotelFragment = new HotelFragment();
                    mTransaction.add(R.id.container, mHotelFragment,
                            "hotel_fragment");
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
            //隐藏Fragment
            transaction.hide(mClothesFragment);
            //将对应菜单栏设置为默认状态
            mTextClothes.setTextColor(getResources()
                    .getColor(R.color.colorText));
            mTextClothes.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.drawable.ic_clothes,0,0);
        }
        if(mFoodFragment != null){
            transaction.hide(mFoodFragment);
            mTextFood.setTextColor(getResources()
                    .getColor(R.color.colorText));
            mTextFood.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.drawable.ic_food,0,0);
        }
        if(mHotelFragment != null){
            transaction.hide(mHotelFragment);
            mTextHotel.setTextColor(getResources()
                    .getColor(R.color.colorText));
            mTextHotel.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.drawable.ic_hotel,0,0);
        }
    }
}
