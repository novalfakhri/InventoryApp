package com.novalfakhri.inventoryapp.database;

import android.text.Editable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.text.DateFormat;

/**
 * Created by Toshiba on 10/28/2017.
 */
@Entity(nameInDb = "Inventory")
public class Inventory {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "Item Name")
    private String itemName;
    @Property(nameInDb = "Date")
    private String date;
    @Property(nameInDb = "Quantity")
    private int quantity;
    @Property(nameInDb = "Supplier")
    private String supplier;
    @Property(nameInDb = "Note")
    private String note;
    @Property(nameInDb = "AdminName")
    private String adminName;
    @Generated(hash = 441084400)
    public Inventory(Long id, String itemName, String date, int quantity, String supplier,
                     String note, String adminName) {
        this.id = id;
        this.itemName = itemName;
        this.date = date;
        this.quantity = quantity;
        this.supplier = supplier;
        this.note = note;
        this.adminName = adminName;
    }

    @Generated(hash = 375708430)
    public Inventory() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getItemName() {
        return this.itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getSupplier() {
        return this.supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public String getNote() {
        return this.note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getAdminName() {
        return this.adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

}
