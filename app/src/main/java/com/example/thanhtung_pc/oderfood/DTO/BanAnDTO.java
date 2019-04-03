package com.example.thanhtung_pc.oderfood.DTO;

public class BanAnDTO {
    int MaBan;
  String TenBan;
  boolean duocChon;

    public boolean isDuocChon() {
        return duocChon;
    }

    public void setDuocChon(boolean duocChon) {
        this.duocChon = duocChon;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String tenBan) {
        TenBan = tenBan;
    }
}
