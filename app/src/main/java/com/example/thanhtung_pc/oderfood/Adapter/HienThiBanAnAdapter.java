package com.example.thanhtung_pc.oderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanhtung_pc.oderfood.DTO.BanAnDTO;
import com.example.thanhtung_pc.oderfood.FragmentApp.HienThiBanAnFragment;
import com.example.thanhtung_pc.oderfood.R;

import java.util.List;

public class    HienThiBanAnAdapter extends BaseAdapter implements View.OnClickListener {

    Context context;
    int layout;
    List<BanAnDTO> banAnDTO;
    ViewHolderBanAn viewHolderBanAn;
    public HienThiBanAnAdapter(Context context, int layout, List<BanAnDTO> banAnDTO)

    {

        this.context=context;
        this.layout=layout;
        this.banAnDTO=banAnDTO;
    }

    @Override
    public int getCount() {
        return banAnDTO.size();
    }

    @Override
    public Object getItem(int position) {
        return banAnDTO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banAnDTO.get(position).getMaBan();
    }




    public  class ViewHolderBanAn
    {
         ImageView imgBanAn,imgGoiMon,imgThanhToan,imgAnButton;
         TextView txtTenBanAn;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=convertView;
        if (view== null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderBanAn= new ViewHolderBanAn();
            view=inflater.inflate(R.layout.custom_layout_hienthibanan,parent,false);
            viewHolderBanAn.imgBanAn=view.findViewById(R.id.imgBanAn);
            viewHolderBanAn.imgGoiMon=view.findViewById(R.id.imgGoiMon);
            viewHolderBanAn.imgThanhToan=view.findViewById(R.id.imgThanhToan);
            viewHolderBanAn.imgAnButton=view.findViewById(R.id.imgAnButton);
            viewHolderBanAn.txtTenBanAn=view.findViewById(R.id.txtTenBanAn);

            view.setTag(viewHolderBanAn);
        }
        else
        {
            viewHolderBanAn=(ViewHolderBanAn)view.getTag();

        }

        if (banAnDTO.get(position).isDuocChon())
        {
            HienThiButton();
        }
        else
        {

           AnButton();

        }
          BanAnDTO banAndto=banAnDTO.get(position);
          viewHolderBanAn.txtTenBanAn.setText(banAndto.getTenBan());
viewHolderBanAn.imgBanAn.setTag(position);
          viewHolderBanAn.imgBanAn.setOnClickListener(this);
        return view;

    }

    private void HienThiButton()
    {
        viewHolderBanAn.imgThanhToan.setVisibility(View.VISIBLE);
        viewHolderBanAn.imgGoiMon.setVisibility(View.VISIBLE);
        viewHolderBanAn.imgAnButton.setVisibility(View.VISIBLE);
    }
    private void AnButton()
    {
        viewHolderBanAn.imgThanhToan.setVisibility(View.INVISIBLE);
        viewHolderBanAn.imgGoiMon.setVisibility(View.INVISIBLE);
        viewHolderBanAn.imgAnButton.setVisibility(View.INVISIBLE);
    }
    public void onClick(View v) {
        int id = v.getId();
        viewHolderBanAn = (ViewHolderBanAn) ((View) v.getParent()).getTag();
        switch (id)
        {
            case R.id.imgBanAn:
                String tenban=viewHolderBanAn.txtTenBanAn.getText().toString();
                int vitri= (int) v.getTag();
                banAnDTO.get(vitri).setDuocChon(true);
HienThiButton();
                viewHolderBanAn.imgGoiMon.setVisibility(View.VISIBLE);
                 break;
        }


    }
}
