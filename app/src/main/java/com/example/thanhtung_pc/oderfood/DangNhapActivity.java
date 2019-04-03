package com.example.thanhtung_pc.oderfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanhtung_pc.oderfood.DAO.NhanVienDAO;

public class DangNhapActivity  extends AppCompatActivity implements View.OnClickListener {

   Button btnDongYDN,btnDangKyDN;
   EditText edtTenDangNhapDN,edtMatKhauDN;
   NhanVienDAO nhanVienDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDongYDN=findViewById(R.id.btnDongYDN);
        btnDangKyDN=findViewById(R.id.btnDangKyDN);
        edtTenDangNhapDN=findViewById(R.id.edtTenDangNhapDN);
        edtMatKhauDN=findViewById(R.id.edtMatKhauDN);


        nhanVienDAO=new NhanVienDAO(this);
        btnDongYDN.setOnClickListener(this);
        btnDangKyDN.setOnClickListener(this);
       // HienThiButtonDangKyVSDongY();

    }
   // private  void HienThiButtonDangKyVSDongY()
   // {
   //    boolean kiemtra= nhanVienDAO.KiemTraNhanVien();//Kiểm tra
   //    if (kiemtra)//Nếu có nhân viên
   //    {
    //       btnDangKyDN.setVisibility(View.GONE);//ẩn nút
    //       btnDongYDN.setVisibility(View.VISIBLE);
    //   }
    //   else
     //  {
     //       btnDangKyDN.setVisibility(View.VISIBLE);
     //       btnDongYDN.setVisibility(View.GONE);
     //  }
   // }

    private void btnDongY(){
        String sTenDangNhap = edtTenDangNhapDN.getText().toString();
        String sMatKhau = edtMatKhauDN.getText().toString();
        int kiemtra = nhanVienDAO.KiemTraDangNhap(sTenDangNhap,sMatKhau);
        if (kiemtra!=0)
        {
            Intent itrangchu=new Intent(DangNhapActivity.this,TrangChuActivity.class);
            itrangchu.putExtra("tendangnhap",edtTenDangNhapDN.getText().toString());
            startActivity(itrangchu);
        }
        else
        {
            Toast.makeText(DangNhapActivity.this,"Bạn Nhập Sai Tên Đăng Nhập Hoặc Mật Khẩu !",Toast.LENGTH_LONG).show();
        }

        }
        private void btnDangKy()
        {
            Intent iDangKy = new Intent(DangNhapActivity.this,DangKyActivity.class);

            startActivity(iDangKy);
        }

    @Override
    protected void onResume() {
        super.onResume();
      //  HienThiButtonDangKyVSDongY();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.btnDongYDN:
                btnDongY();
                break;
            case R.id.btnDangKyDN:
                btnDangKy();
                break;
        }

    }
}
