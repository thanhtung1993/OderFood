package com.example.thanhtung_pc.oderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thanhtung_pc.oderfood.DTO.NhanVienDTO;
import com.example.thanhtung_pc.oderfood.R;

import java.util.List;

public class HienThiNhanVienAdapter extends BaseAdapter {
     Context context;
     int layout;
     List<NhanVienDTO> nhanVienDTOList;

    ViewHolderNhanVien viewHolderNhanVien;


    public HienThiNhanVienAdapter(Context context, int layout, List<NhanVienDTO> nhanVienDTOList) {
        this.context = context;
        this.layout = layout;
        this.nhanVienDTOList = nhanVienDTOList;
    }

    @Override
    public int getCount() {
        return nhanVienDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanVienDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return nhanVienDTOList.get(position).getMANV();
    }

    public class ViewHolderNhanVien
    {
        ImageView imgHinhNhanVien;
        TextView txtTenNhanVien,txtCMND;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if (view==null)
        {
            viewHolderNhanVien=new ViewHolderNhanVien();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,parent,false);

            viewHolderNhanVien.imgHinhNhanVien=view.findViewById(R.id.imgHinhNhanVien);
            viewHolderNhanVien.txtTenNhanVien=view.findViewById(R.id.txtTenNhanVien);
            viewHolderNhanVien.txtCMND=view.findViewById(R.id.txtCMND);

            view.setTag(viewHolderNhanVien);
        }
        else
        {
            viewHolderNhanVien =(ViewHolderNhanVien)view.getTag();
        }


        NhanVienDTO nhanVienDTO=nhanVienDTOList.get(position);
        viewHolderNhanVien.txtTenNhanVien.setText(nhanVienDTO.getTENDN());
        viewHolderNhanVien.txtCMND.setText(String.valueOf(nhanVienDTO.getCMND()));
        return view;
    }
}
