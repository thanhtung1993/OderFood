package com.example.thanhtung_pc.oderfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.thanhtung_pc.oderfood.Adapter.HienThiBanAnAdapter;
import com.example.thanhtung_pc.oderfood.DAO.BanAnDAO;
import com.example.thanhtung_pc.oderfood.DTO.BanAnDTO;
import com.example.thanhtung_pc.oderfood.FragmentApp.HienThiBanAnFragment;
import com.example.thanhtung_pc.oderfood.FragmentApp.HienThiNhanVienFragment;
import com.example.thanhtung_pc.oderfood.FragmentApp.HienThiThucDonFragment;

import java.util.ArrayList;
import java.util.List;

public class    TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout_trangchu;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView txtTenNhanVien_navi;
    FragmentManager fragmentManager;

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.layout_trangchu);
        super.onCreate(savedInstanceState);

        drawerLayout_trangchu=findViewById(R.id.drawerlayout_trangchu);
        navigationView=findViewById(R.id.navigationview_trangchu);
        toolbar=findViewById(R.id.toolbar);

       View view=navigationView.inflateHeaderView(R.layout.layout_header);
       // View view=LayoutInflater.from(this).inflate(R.layout.layout_header,null);
        txtTenNhanVien_navi=view.findViewById(R.id.txtTenNhanVien_Navigation);




        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle drawerToggle =new ActionBarDrawerToggle(this,drawerLayout_trangchu,toolbar,R.string.mo,R.string.dong)
        {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout_trangchu.setDrawerListener(drawerToggle);
        drawerToggle.syncState();//đồng bộ lại


        navigationView.setItemIconTintList(null);// lấy mầu mặc định icon
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent=getIntent();
        String tendn=intent.getStringExtra("tendangnhap");
        txtTenNhanVien_navi.setText(tendn);

        fragmentManager=getSupportFragmentManager();//vì sử dụng gói thư viện vơ sần 4
        FragmentTransaction tranHienThiBanAn=fragmentManager.beginTransaction() ;
        HienThiBanAnFragment hienThiBanAnFragment=new HienThiBanAnFragment();
        tranHienThiBanAn.replace(R.id.content,hienThiBanAnFragment);
        tranHienThiBanAn.commit();

    }



    @Override
    public boolean onNavigationItemSelected( MenuItem item)
    {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.itTrangChu:


                FragmentTransaction tranHienThiBanAn=fragmentManager.beginTransaction() ;
                HienThiBanAnFragment hienThiBanAnFragment=new HienThiBanAnFragment();
                tranHienThiBanAn.replace(R.id.content,hienThiBanAnFragment);
                tranHienThiBanAn.commit();

                item.setChecked(true);
                drawerLayout_trangchu.closeDrawers();//đóng menu
                break;
            case R.id.itThucDon:
                FragmentTransaction tranhienthithucdon=fragmentManager.beginTransaction() ;
                HienThiThucDonFragment hienthithucdon=new HienThiThucDonFragment();

                tranhienthithucdon.replace(R.id.content,hienthithucdon);
                tranhienthithucdon.commit();
                item.setChecked(true);
                drawerLayout_trangchu.closeDrawers();//đóng menu

                break;
            case R.id.itNhanVien:
                FragmentTransaction tranhienthinhanvien=fragmentManager.beginTransaction();
                HienThiNhanVienFragment hienthinhanvien=new HienThiNhanVienFragment();
                tranhienthinhanvien.replace(R.id.content,hienthinhanvien);
                tranhienthinhanvien.commit();


                item.setChecked(true);
                drawerLayout_trangchu.closeDrawers();
                break;
        }

        return false;
    }
}
