package com.example.thanhtung_pc.oderfood.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thanhtung_pc.oderfood.DAO.LoaiMonAnDAO;
import com.example.thanhtung_pc.oderfood.DTO.LoaiMonAnDTO;
import com.example.thanhtung_pc.oderfood.R;

import java.util.List;

public class HienThiLoaiMonAnThucDonAdapter extends BaseAdapter {

  Context context;
    int layout;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    ViewHolderHienThiLoaiThucDon viewHolderHienThiLoaiThucDon;

    LoaiMonAnDAO loaiMonAnDAO;

    public HienThiLoaiMonAnThucDonAdapter(Context context, int layout, List<LoaiMonAnDTO> loaiMonAnDTOList) {
        this.context = context;
        this.layout = layout;
        this.loaiMonAnDTOList = loaiMonAnDTOList;
        loaiMonAnDAO=new LoaiMonAnDAO(context);
    }

    @Override
    public int getCount() {
        return loaiMonAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return loaiMonAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return loaiMonAnDTOList.get(position).getMaLoai();
    }

    public class ViewHolderHienThiLoaiThucDon
    {
        ImageView imgHinhLoaiThucDon;
        TextView txtTenLoaiThucDon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view =convertView;
       if (view==null)
       {
           viewHolderHienThiLoaiThucDon=new ViewHolderHienThiLoaiThucDon();
           LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view=inflater.inflate(layout,parent,false);

           viewHolderHienThiLoaiThucDon.imgHinhLoaiThucDon=view.findViewById(R.id.imgHienThiMonAn);
           viewHolderHienThiLoaiThucDon.txtTenLoaiThucDon=view.findViewById(R.id.txtTenLoaiThucDon);

           view.setTag(viewHolderHienThiLoaiThucDon);
       }
       else
       {
           viewHolderHienThiLoaiThucDon=(ViewHolderHienThiLoaiThucDon)view.getTag();

       }

       LoaiMonAnDTO loaiMonAnDTO=loaiMonAnDTOList.get(position);
       int maloai=loaiMonAnDTO.getMaLoai();
       String hinhanh=loaiMonAnDAO.LayHinhLoaiMonAn(maloai);

       Uri uri =Uri.parse(hinhanh);
        viewHolderHienThiLoaiThucDon.txtTenLoaiThucDon.setText(loaiMonAnDTO.getTenLoai());
        viewHolderHienThiLoaiThucDon.imgHinhLoaiThucDon.setImageURI(uri);


        return view;
    }
}
