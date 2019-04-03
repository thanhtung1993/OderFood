package com.example.thanhtung_pc.oderfood.FragmentApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.thanhtung_pc.oderfood.Adapter.HienThiLoaiMonAnThucDonAdapter;
import com.example.thanhtung_pc.oderfood.DAO.LoaiMonAnDAO;
import com.example.thanhtung_pc.oderfood.DTO.LoaiMonAnDTO;
import com.example.thanhtung_pc.oderfood.R;
import com.example.thanhtung_pc.oderfood.ThemThucDonActivity;
import com.example.thanhtung_pc.oderfood.TrangChuActivity;

import java.util.List;

public class HienThiThucDonFragment extends Fragment {
    GridView gridView;
    List<LoaiMonAnDTO> loaiMonAnDTO;
    LoaiMonAnDAO loaiMonAnDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_hienthithucdon,container,false);
        setHasOptionsMenu(true);
        ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.thucdon);


        gridView=view.findViewById(R.id.gvHienThiThucDon);
        loaiMonAnDAO=new LoaiMonAnDAO(getActivity());
        loaiMonAnDTO=loaiMonAnDAO.LayDanhSachLoaiMonAn();
        HienThiLoaiMonAnThucDonAdapter adapter=new HienThiLoaiMonAnThucDonAdapter(getActivity(),R.layout.custom_layout_loaimonan,loaiMonAnDTO);
gridView.setAdapter(adapter);
adapter.notifyDataSetChanged();
        return view;

    }

    @Override

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemThucDon = menu.add(1, R.id.itThemThucDon, 1, R.string.themthucdon);
        itThemThucDon.setIcon(R.drawable.logodangnhap);
        itThemThucDon.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch (id)
        {
            case R.id.itThemThucDon:
                Intent iThemThucDon=new Intent(getActivity(),ThemThucDonActivity.class);
                startActivity(iThemThucDon);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
