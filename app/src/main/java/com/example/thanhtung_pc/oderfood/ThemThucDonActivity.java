package com.example.thanhtung_pc.oderfood;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanhtung_pc.oderfood.Adapter.HienThiLoaiMonAnAdapter;
import com.example.thanhtung_pc.oderfood.DAO.LoaiMonAnDAO;
import com.example.thanhtung_pc.oderfood.DAO.MonAnDAO;
import com.example.thanhtung_pc.oderfood.DTO.LoaiMonAnDTO;
import com.example.thanhtung_pc.oderfood.DTO.MonAnDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {
    public static int REQUEST_CODE_THEMLOAITHUCDON = 113;
    public static int REQUEST_CODE_MOHINH= 123;
    ImageButton imgThemLoaiThucDon;
    Spinner spLoaiThucDon;
    LoaiMonAnDAO loaiMonAnDAO;
    List<LoaiMonAnDTO> loaiMonAnDTOS;
    HienThiLoaiMonAnAdapter hienThiLoaiMonAnAdapter;
    ImageView imgHinhThucDon;
    Button btnDongYThemMonAn,btnThoatThemMonAn;
    String sDuongDanHinh;
    EditText edtTenMonAn,edtGiaTien;
    MonAnDAO monAnDAO;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);
          imgThemLoaiThucDon=findViewById(R.id.imgThemLoaiThucDon);
          spLoaiThucDon=findViewById(R.id.spLoaiMonAn);
          imgHinhThucDon=findViewById(R.id.imgHinhThucDon);
          btnDongYThemMonAn=findViewById(R.id.btnDongYThemMonAn);
          btnThoatThemMonAn=findViewById(R.id.btnThoatThemMonAn);
          edtTenMonAn=findViewById(R.id.edtThemTenMonAn);
          edtGiaTien=findViewById(R.id.edtThemGiaTien);


          loaiMonAnDAO=new LoaiMonAnDAO(this    );
          monAnDAO=new MonAnDAO(this );

          HienThiSpinLoaiMonAn();

          imgThemLoaiThucDon.setOnClickListener(this);
          imgHinhThucDon.setOnClickListener(this   );
          btnDongYThemMonAn.setOnClickListener(this);
          btnThoatThemMonAn.setOnClickListener(this );

    }

    private  void HienThiSpinLoaiMonAn()
    {
        loaiMonAnDTOS= loaiMonAnDAO.LayDanhSachLoaiMonAn();
        hienThiLoaiMonAnAdapter=new HienThiLoaiMonAnAdapter(ThemThucDonActivity.this,R.layout.custom_layout_sploaithucdon,loaiMonAnDTOS);
        spLoaiThucDon.setAdapter(hienThiLoaiMonAnAdapter);
        hienThiLoaiMonAnAdapter.notifyDataSetChanged();


    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.imgThemLoaiThucDon:
                Intent iThemLoaiMonAn=new Intent(ThemThucDonActivity.this,ThemLoaiThucDonActivity.class);
                startActivityForResult(iThemLoaiMonAn,REQUEST_CODE_THEMLOAITHUCDON);

                break;
            case  R.id.imgHinhThucDon:
                Intent iMoHinh=new Intent();
                iMoHinh.setType("image/*");//mở tất cả hình ảnh
                iMoHinh.setAction(Intent.ACTION_GET_CONTENT);//lọc
                startActivityForResult(Intent.createChooser(iMoHinh,"Chọn hình THực Đơn"),REQUEST_CODE_MOHINH);

                break;
          case R.id.btnDongYThemMonAn:
               int vitri = spLoaiThucDon.getSelectedItemPosition();
                int maloai = loaiMonAnDTOS.get(vitri).getMaLoai();
                String tenmonan = edtTenMonAn.getText().toString();
                String giatien = edtGiaTien.getText().toString();

               if(tenmonan != null && giatien != null && !tenmonan.equals("") && !giatien.equals("") ){
                    MonAnDTO monAnDTO = new MonAnDTO();
                    monAnDTO.setGiaTien(giatien);
                    monAnDTO.setHinhAnh(sDuongDanHinh);
                    monAnDTO.setMaLoai(maloai);
                    monAnDTO.setTenMonAn(tenmonan);

                    boolean kiemtra = monAnDAO.ThemMonAn(monAnDTO);
                    if(kiemtra){
                        Toast.makeText(this,getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(this,getResources().getString(R.string.loithemmonan),Toast.LENGTH_SHORT).show();
               }

              // Log.d("vitri",vitri + "");



                break;

            case R.id.btnThoatThemMonAn:

             finish();
               break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_THEMLOAITHUCDON)
        {
            if (resultCode==Activity.RESULT_OK)
            {
                Intent dulieu=data;
                boolean kiemtra=dulieu.getBooleanExtra("kiemtraloaithucdon",false);
                if (kiemtra)
                {
                    HienThiSpinLoaiMonAn();
                    Toast.makeText(this,getResources().getString(R.string.themthanhcong),Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(this,getResources().getString(R.string.themthatbai),Toast.LENGTH_SHORT).show();

                }
            }
        }
        else if (REQUEST_CODE_MOHINH==requestCode)
        {
            if (resultCode==Activity.RESULT_OK)
            {
                 sDuongDanHinh=data.getData().toString();
                try {
                    Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                    imgHinhThucDon.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // imgHinhThucDon.setImageURI(data.getData());
                //Log.d("duongan",data.getData()+"");
            }

        }


    }
}
