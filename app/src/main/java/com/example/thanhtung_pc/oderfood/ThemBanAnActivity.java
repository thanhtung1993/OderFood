package com.example.thanhtung_pc.oderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanhtung_pc.oderfood.DAO.BanAnDAO;
import com.example.thanhtung_pc.oderfood.DTO.BanAnDTO;
import com.example.thanhtung_pc.oderfood.DTO.NhanVienDTO;
import com.example.thanhtung_pc.oderfood.Database.CreateDatabase;

import java.util.List;

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtThemBanAn;
    Button btnDongYThemBanAn;
    CreateDatabase database;
    BanAnDAO banAnDAO;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thembanan);

        edtThemBanAn=findViewById(R.id.edtThemBanAn);
        btnDongYThemBanAn=findViewById(R.id.btnDongYThemBanAn);


        banAnDAO=new BanAnDAO(this);
        btnDongYThemBanAn.setOnClickListener(this);

        // List<BanAnDTO> banAnDTOList;
        // banAnDTOList =banAnDAO.layTatCaBanAn();
        //for (int i=0;i<banAnDTOList.size();i++)
        //  { Log.d("dulieu",banAnDTOList.get(i).getTenBan()+"-"
        //        +banAnDTOList.get(i).getMaBan());}

    }

    @Override
    public void onClick(View v) {


        String sTenBanAn=edtThemBanAn.getText().toString();


      if (sTenBanAn!=null || sTenBanAn.equals(""))
     {  boolean kiemtra=banAnDAO.ThemBanAn(sTenBanAn);
        Intent intent=new Intent();
        intent.putExtra("ketquathem",kiemtra);
        setResult(Activity.RESULT_OK,intent);
        finish();
       }



    }
}
