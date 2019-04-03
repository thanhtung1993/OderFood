package com.example.thanhtung_pc.oderfood.FragmentApp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thanhtung_pc.oderfood.Adapter.HienThiNhanVienAdapter;
import com.example.thanhtung_pc.oderfood.DAO.NhanVienDAO;
import com.example.thanhtung_pc.oderfood.DTO.NhanVienDTO;
import com.example.thanhtung_pc.oderfood.DangKyActivity;
import com.example.thanhtung_pc.oderfood.Database.CreateDatabase;
import com.example.thanhtung_pc.oderfood.R;

import java.util.List;

public class HienThiNhanVienFragment extends Fragment {
    ListView lvNhanVien;
    NhanVienDAO nhanVienDAO;
    List<NhanVienDTO> nhanVienDTOList;
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthinhanvien,container,false);
        setHasOptionsMenu(true);

        lvNhanVien = (ListView) view.findViewById(R.id.lvNhanVien);
        nhanVienDAO = new NhanVienDAO(getActivity());
        HienThiDanhSachNhanVien();
        return view;

    }
    private void HienThiDanhSachNhanVien(){
        nhanVienDTOList = nhanVienDAO.LayDanhSachNhanVien();

        HienThiNhanVienAdapter hienThiNhanVienAdapter = new HienThiNhanVienAdapter(getActivity(),R.layout.custom_layout_hienthinhanvien,nhanVienDTOList);
        lvNhanVien.setAdapter(hienThiNhanVienAdapter);
        hienThiNhanVienAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);


    }
    public boolean onContextItemSelected(MenuItem item) {

       // int id = item.getItemId();
       // AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
       // int vitri = menuInfo.position;
       // int manhanvien = nhanVienDTOList.get(vitri).getMANV();

       // switch (id){
       //     case R.id.itSua:
        //        Intent iDangKy = new Intent(getActivity(),DangKyActivity.class);
        //        iDangKy.putExtra("manv",manhanvien);
         //       startActivity(iDangKy);
         //       break;

         //   case R.id.itXoa:
         //       boolean kiemtra = nhanVienDAO.XoaNhanVien(manhanvien);
         //       if (kiemtra){
         //           HienThiDanhSachNhanVien();
         //           Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoathanhcong),Toast.LENGTH_SHORT).show();
         //       }else{
          //          Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.loi),Toast.LENGTH_SHORT).show();
           //     }
          //      break;
       // }
        return true;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemNhanVien = menu.add(1,R.id.itThemNhanVien,1,R.string.themnhanvien);
        itThemNhanVien.setIcon(R.drawable.nhanvien);
       itThemNhanVien.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itThemNhanVien:
                HienThiDanhSachNhanVien();
                Intent iDangKy = new Intent(getActivity(), DangKyActivity.class);
                startActivity(iDangKy);
                ;break;
        }
        return true;
    }






}
