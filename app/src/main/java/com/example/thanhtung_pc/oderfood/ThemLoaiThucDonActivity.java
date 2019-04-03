package com.example.thanhtung_pc.oderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanhtung_pc.oderfood.DAO.LoaiMonAnDAO;

public class ThemLoaiThucDonActivity extends AppCompatActivity implements View.OnClickListener{
  Button btnDongYThemLoaiThucDon;
  EditText edtTenLoai;
  LoaiMonAnDAO loaiMonAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themloaithucdon);

        loaiMonAnDAO=new LoaiMonAnDAO(this);

        btnDongYThemLoaiThucDon=findViewById(R.id.btnDongYThemLoaiThucDon);
        edtTenLoai=findViewById(R.id.edtThemLoaiThucDon);

        btnDongYThemLoaiThucDon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String sTenLoaiThucDon=edtTenLoai.getText().toString();
        if (sTenLoaiThucDon !=null || sTenLoaiThucDon.equals(""))
        {
            boolean kiemtra=loaiMonAnDAO.ThemLoaiMonAn(sTenLoaiThucDon);
            Intent idulieu=new Intent();
            idulieu.putExtra("kiemtraloaithucdon",kiemtra);
            setResult(Activity.RESULT_OK,idulieu);
            finish();
        }
        else
        {
            Toast.makeText(this,getResources().getString(R.string.vuilongnhapdulieu),Toast.LENGTH_SHORT).show();

        }

    }
}
