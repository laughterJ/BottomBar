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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //底部菜单栏3个TextView
    @BindView(R.id.tv_clothes) TextView mTabClothes;
    @BindView(R.id.tv_food) TextView mTabFood;
    @BindView(R.id.tv_hotel) TextView mTabHotel;

    //3个Fragment
    private Fragment mClothesFragment;
    private Fragment mFoodFragment;
    private Fragment mHotelFragment;

    //标记当前显示的Fragment
    private int mFragmentId = 0;

    // 标记三个Fragment
    public static final int FRAGMENT_CLOTHES = 0;
    public static final int FRAGMENT_FOOD = 1;
    public static final int FRAGMENT_HOTEL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //根据传入的Bundle对象判断Activity是正常启动还是销毁重建
        if(savedInstanceState == null){
            //设置第一个Fragment默认选中
            setFragment(FRAGMENT_CLOTHES);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //通过onSaveInstanceState方法保存当前显示的fragment
        outState.putInt("fragment_id", mFragmentId);
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

    @OnClick({R.id.tv_clothes, R.id.tv_food, R.id.tv_hotel})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_clothes:
                setFragment(FRAGMENT_CLOTHES);
                break;
            case R.id.tv_food:
                setFragment(FRAGMENT_FOOD);
                break;
            case R.id.tv_hotel:
                setFragment(FRAGMENT_HOTEL);
                break;
        }
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
            case FRAGMENT_CLOTHES:
                mFragmentId = FRAGMENT_CLOTHES;
                //设置菜单栏为选中状态（修改文字和图片颜色）
                mTabClothes.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTabClothes.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_clothes_pressed,0,0);
                //显示对应Fragment
                if(mClothesFragment == null){
                    mClothesFragment = new ClothesFragment();
                    mTransaction.add(R.id.container, mClothesFragment, "clothes_fragment");
                }else {
                    mTransaction.show(mClothesFragment);
                }
                break;
            case FRAGMENT_FOOD:
                mFragmentId = FRAGMENT_FOOD;
                mTabFood.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTabFood.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_food_pressed,0,0);
                if(mFoodFragment == null){
                    mFoodFragment = new FoodFragment();
                    mTransaction.add(R.id.container, mFoodFragment, "food_fragment");
                }else {
                    mTransaction.show(mFoodFragment);
                }
                break;
            case FRAGMENT_HOTEL:
                mFragmentId = FRAGMENT_HOTEL;
                mTabHotel.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTabHotel.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_hotel_pressed,0,0);
                if(mHotelFragment == null){
                    mHotelFragment = new HotelFragment();
                    mTransaction.add(R.id.container, mHotelFragment, "hotel_fragment");
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
            mTabClothes.setTextColor(getResources().getColor(R.color.colorText));
            mTabClothes.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.drawable.ic_clothes,0,0);
        }
        if(mFoodFragment != null){
            transaction.hide(mFoodFragment);
            mTabFood.setTextColor(getResources().getColor(R.color.colorText));
            mTabFood.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.drawable.ic_food,0,0);
        }
        if(mHotelFragment != null){
            transaction.hide(mHotelFragment);
            mTabHotel.setTextColor(getResources().getColor(R.color.colorText));
            mTabHotel.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.drawable.ic_hotel,0,0);
        }
    }
}
