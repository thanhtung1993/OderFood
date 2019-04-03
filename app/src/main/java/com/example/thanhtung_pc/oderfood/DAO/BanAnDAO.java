package com.example.thanhtung_pc.oderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thanhtung_pc.oderfood.DTO.BanAnDTO;
import com.example.thanhtung_pc.oderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class BanAnDAO {
    SQLiteDatabase database;
    public BanAnDAO(Context context)
    {
        CreateDatabase createDatabase=new CreateDatabase(context);
        database=createDatabase.open();
    }
    public boolean ThemBanAn(String tenban)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN,tenban);


        long kiemtra = database.insert(CreateDatabase.TB_BANAN,null,contentValues);
        if(kiemtra != 0){
            return true;
        }else{
            return false;
        }




    }
    public List<BanAnDTO> layTatCaBanAn()
    {
        List<BanAnDTO> banAnDTOList=new ArrayList<BanAnDTO>();
        String truyvan="SELECT * FROM "+CreateDatabase.TB_BANAN;
        Cursor cursor=database.rawQuery(truyvan,null);
        cursor.moveToFirst();//trỏ tới vị trí đầu tiên
        while (!cursor.isAfterLast())//Khi nào con trỏ không là vị trí cuối
        {
            BanAnDTO banAnDTO=new BanAnDTO();
            banAnDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_MABAN)));
            banAnDTO.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TENBAN)));

            banAnDTOList.add(banAnDTO);
            cursor.moveToNext();
        }

        return banAnDTOList;

    }
    public boolean CapNhatLaiTenBan(int maban,String tenban){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBAN,tenban);

        long kiemtra = database.update(CreateDatabase.TB_BANAN, contentValues, CreateDatabase.TB_BANAN_MABAN + " = '" + maban + "'", null);

        if(kiemtra !=0){
            return true;
           // Toast.makeText(this,"Bạn Cập Nhật Thành Công",Toast.LENGTH_SHORT).show();
        }else{
            return false;
        }
    }

    public boolean XoaBanAnTheoMa(int maban){
        long kiemtra = database.delete(CreateDatabase.TB_BANAN,CreateDatabase.TB_BANAN_MABAN + " = " + maban,null);
        if(kiemtra !=0){
            return true;
        }else{
            return false;
        }
    }

}
