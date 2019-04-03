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

import com.example.thanhtung_pc.oderfood.DAO.BanAnDAO;

public class SuaBanAnActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDongYSua;
    EditText edtSuaTenBan;
    BanAnDAO banAnDAO;
    int maban;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suabanan);

        btnDongYSua=findViewById(R.id.btnDongYSuaBanAn);
        edtSuaTenBan=findViewById(R.id.edtSuaTenBanAn);

        banAnDAO=new BanAnDAO(this);
       btnDongYSua.setOnClickListener(this);
        maban=getIntent().getIntExtra("maban",0);

    }

    @Override





    public void onClick(View v) {
        String tenban = edtSuaTenBan.getText().toString();
        if(tenban.trim().equals("") || tenban.trim() != null){
            boolean kiemtra = banAnDAO.CapNhatLaiTenBan(maban,tenban);
            Intent intent = new Intent();
            intent.putExtra("kiemtra",kiemtra);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }else{
            Toast.makeText(SuaBanAnActivity.this, getResources().getString(R.string.vuilongnhapdulieu), Toast.LENGTH_SHORT).show();
        }
    }

    }

