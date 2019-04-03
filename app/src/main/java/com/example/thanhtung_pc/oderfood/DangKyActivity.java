package com.example.thanhtung_pc.oderfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanhtung_pc.oderfood.DAO.NhanVienDAO;
import com.example.thanhtung_pc.oderfood.DTO.NhanVienDTO;
import com.example.thanhtung_pc.oderfood.Database.CreateDatabase;
import com.example.thanhtung_pc.oderfood.FragmentApp.DatePickerFragment;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener ,View.OnFocusChangeListener {
    EditText edtTenDangNhapDK,edtMatKhauDK,edtNgaySinhDK,edtCMND;
    Button btnDongYDK,btnThoatDK;
    RadioGroup rgGioiTinh;
    String sGioiTinh;
    RadioButton rdNam,rdNu;
    NhanVienDAO nhanVienDAO;
    TextView txtTieuDeDangKy;
    int manv=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        addControls();
        btnDongYDK.setOnClickListener(this);
        btnThoatDK.setOnClickListener(this);

        edtNgaySinhDK.setOnFocusChangeListener(this);

        nhanVienDAO=new NhanVienDAO(this);
        manv = getIntent().getIntExtra("manv",0);
        if (manv!=0)
        {
           txtTieuDeDangKy.setText(getResources().getString(R.string.capnhatnhanvien));

            NhanVienDTO nhanVienDTO = nhanVienDAO.LayDanhSachNhanVienTheoMa(manv);

            edtTenDangNhapDK.setText(nhanVienDTO.getTENDN());
            edtMatKhauDK.setText(nhanVienDTO.getMATKHAU());
            edtNgaySinhDK.setText(nhanVienDTO.getNGAYSINH());
            edtCMND.setText(String.valueOf(nhanVienDTO.getCMND()));

            String gioitinh = nhanVienDTO.getGIOITINH();
            if(gioitinh.equals("Nam")){
                rdNam.setChecked(true);
            }else{
                rdNu.setChecked(true);
            }

            edtNgaySinhDK.setText(nhanVienDTO.getNGAYSINH());
            edtCMND.setText(String.valueOf(nhanVienDTO.getCMND()));

        }

    }



    private void addControls() {

        edtTenDangNhapDK=findViewById(R.id.edtTenDangNhapDK);
        edtMatKhauDK=findViewById(R.id.edtMatKhauDK);
        edtNgaySinhDK=findViewById(R.id.edtNgaySinhDK);
        edtCMND=findViewById(R.id.edtCMND);
        rdNam =  findViewById(R.id.rdoNam);
        rdNu =  findViewById(R.id.rdoNu);
        btnDongYDK=findViewById(R.id.btnDongYDK);

        btnThoatDK=findViewById(R.id.btnThoatDK);
        rgGioiTinh=findViewById(R.id.rgGioiTinh);
        txtTieuDeDangKy=findViewById(R.id.txtTieuDeDangKy);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.btnDongYDK:

                if (manv !=0)
                {
                    SuaNhanVien();

                }
                else
                {
                    DongYThemNhanVien();

                }


            break;
            case R.id.btnThoatDK:
                finish();break;
        }

    }
    private void SuaNhanVien(){
        String sTenDangNhap = edtTenDangNhapDK.getText().toString();
        String sMatKhau = edtMatKhauDK.getText().toString();
        String sNgaySinh = edtNgaySinhDK.getText().toString();
        int sCMND = Integer.parseInt(edtCMND.getText().toString());
        switch (rgGioiTinh.getCheckedRadioButtonId()){
            case R.id.rdoNam:
                sGioiTinh = "Nam";
                break;

            case R.id.rdoNu:
                sGioiTinh = "Nữ";
                break;
        }

        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        nhanVienDTO.setMANV(manv);
        nhanVienDTO.setTENDN(sTenDangNhap);
        nhanVienDTO.setMATKHAU(sMatKhau);
        nhanVienDTO.setCMND(sCMND);
        nhanVienDTO.setNGAYSINH(sNgaySinh);
        nhanVienDTO.setGIOITINH(sGioiTinh);

        boolean kiemtra = nhanVienDAO.SuaNhanVien(nhanVienDTO);
        if(kiemtra){
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.suathanhcong),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(DangKyActivity.this,getResources().getString(R.string.loi),Toast.LENGTH_SHORT).show();
        }

    }
    public void DongYThemNhanVien()
    {
        String sTenDangNhap=edtTenDangNhapDK.getText().toString();
        String sMatKhau=edtMatKhauDK.getText().toString();
        switch (rgGioiTinh.getCheckedRadioButtonId())
        {
            case R.id.rdoNam:
                sGioiTinh="Nam";
                break;
            case R.id.rdoNu:
                sGioiTinh="Nữ";
                break;

        }
        String sNgaySinh=edtNgaySinhDK.getText().toString();
        String sCMND=edtCMND.getText().toString();
        if (sTenDangNhap==null ||sTenDangNhap.equals(""))
        {
            Toast.makeText(DangKyActivity.this, getResources().getString(R.string.tendangnhap)+" ",Toast.LENGTH_SHORT).show();
        }
        else if (sMatKhau==null|| sMatKhau.equals(""))
        {
            Toast.makeText(DangKyActivity.this,getResources().getText(R.string.loinhapmatkhau),Toast.LENGTH_SHORT).show();

        }
        else {
            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            nhanVienDTO.setTENDN(sTenDangNhap);
            nhanVienDTO.setMATKHAU(sMatKhau);
            nhanVienDTO.setCMND(Integer.parseInt(sCMND));
            nhanVienDTO.setNGAYSINH(sNgaySinh);
            nhanVienDTO.setGIOITINH(sGioiTinh);


            long kiemtra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
            if (kiemtra != 0) {
                Toast.makeText(DangKyActivity.this, getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DangKyActivity.this, getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id=v.getId();
        switch (id)
        {
            case R.id.edtNgaySinhDK:
                if (hasFocus)
                    //xuất pos up ngày sinh
                {
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(),"Ngày Sinh");
                }

                break;
        }

    }
}
