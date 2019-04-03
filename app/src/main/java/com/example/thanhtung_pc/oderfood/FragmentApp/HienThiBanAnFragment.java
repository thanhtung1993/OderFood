package com.example.thanhtung_pc.oderfood.FragmentApp;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thanhtung_pc.oderfood.Adapter.HienThiBanAnAdapter;
import com.example.thanhtung_pc.oderfood.DAO.BanAnDAO;
import com.example.thanhtung_pc.oderfood.DTO.BanAnDTO;
import com.example.thanhtung_pc.oderfood.R;
import com.example.thanhtung_pc.oderfood.SuaBanAnActivity;
import com.example.thanhtung_pc.oderfood.ThemBanAnActivity;
import com.example.thanhtung_pc.oderfood.TrangChuActivity;

import java.util.List;

public class HienThiBanAnFragment  extends Fragment {
    public static int REQUEST_CODE_THEM = 111;
    public static int RESQUEST_CODE_SUA = 16;
    GridView grHienThiBanAn;
    List<BanAnDTO> banAnDTOList;
    BanAnDAO banAnDAO;
    HienThiBanAnAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthibanan, container, false);
        setHasOptionsMenu(true);
       // ((TrangChuActivity)getActivity()).getSupportActionBar().setTitle(R.string.banan);

        grHienThiBanAn = view.findViewById(R.id.gvHienBanAn);

        banAnDAO = new BanAnDAO(getActivity());
        banAnDTOList = banAnDAO.layTatCaBanAn();

        registerForContextMenu(grHienThiBanAn);
        HienThiDanhSachBanAn();

        return view;


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

          AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
          int vitri = menuInfo.position;
          int maban = banAnDTOList.get(vitri).getMaBan();

        switch (id){
            case R.id.itSua:

                Intent intent = new Intent(getActivity(), SuaBanAnActivity.class);
                intent.putExtra("maban",maban);

                startActivityForResult(intent,RESQUEST_CODE_SUA);


                break;


            case R.id.itXoa:
                boolean kiemtra = banAnDAO.XoaBanAnTheoMa(maban);
                if(kiemtra){
                    HienThiDanhSachBanAn();
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.xoathanhcong),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.loi) + maban,Toast.LENGTH_SHORT).show();
                }
                ;break;
        }
        return super.onContextItemSelected(item);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itThemBanAn = menu.add(1, R.id.itThemBanAn, 1, R.string.thembanan);
        itThemBanAn.setIcon(R.drawable.thembanan);
        itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.itThemBanAn:

                Intent iThemBanAn = new Intent(getActivity(), ThemBanAnActivity.class);
                startActivityForResult(iThemBanAn, REQUEST_CODE_THEM);
                HienThiDanhSachBanAn();
                break;
        }
        return true;

    }

    private void HienThiDanhSachBanAn() {
        banAnDTOList = banAnDAO.layTatCaBanAn();
        adapter = new HienThiBanAnAdapter(getActivity(), R.layout.custom_layout_hienthibanan, banAnDTOList);
        grHienThiBanAn.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

        @Override
        public void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE_THEM) {
                if (resultCode == Activity.RESULT_OK) {
                    Intent intent = data;

                    boolean kiemtra = intent.getBooleanExtra("ketquathem", false);

                    if (kiemtra) {
                        HienThiDanhSachBanAn();

                        Toast.makeText(getActivity(), getResources().getString(R.string.themthanhcong), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), getResources().getString(R.string.themthatbai), Toast.LENGTH_SHORT).show();
                    }
                }
                else if(requestCode == RESQUEST_CODE_SUA){
                    if(resultCode == Activity.RESULT_OK){
                        Intent intent = data;
                        boolean kiemtra = intent.getBooleanExtra("kiemtra",false);
                        HienThiDanhSachBanAn();
                        if(kiemtra){
                            Toast.makeText(getActivity(), getResources().getString(R.string.suathanhcong), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), getResources().getString(R.string.loi), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

