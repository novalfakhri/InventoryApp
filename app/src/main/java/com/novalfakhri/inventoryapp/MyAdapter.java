package com.novalfakhri.inventoryapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.novalfakhri.inventoryapp.database.Inventory;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Toshiba on 10/28/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Inventory> inventoryList;
    private Context context;
    private ItemListener itemListener;

    public MyAdapter(Context context, ItemListener itemListener) {
        inventoryList = new ArrayList<>();
        this.context = context;
        this.itemListener = itemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        return new MyViewHolder(rootView, itemListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (inventoryList != null) {
            Inventory inventory = inventoryList.get(position);
            String itemName = inventory.getItemName();
            String date = inventory.getDate();
            int quantity = inventory.getQuantity();
            String supplier = inventory.getSupplier();
            String note = inventory.getNote();
            String adminName = inventory.getAdminName();
            Log.d(TAG, "value admin = " + adminName);
            Log.d(TAG, "value name = " + itemName);
            Log.d(TAG, "value date = " + date);
            Log.d(TAG, "value quantity = " + quantity);
            Log.d(TAG, "value supplier = " + supplier);
            Log.d(TAG, "value note= " + note);
            holder.invName.setText(itemName);
            holder.invDate.setText(date);
            holder.invQuantity.setText("Qty : " + String.valueOf(quantity));
            holder.invSupplier.setText("Supplier : " + supplier);
            holder.invNote.setText("Note : " + note);
            holder.invAdmin.setText("Added by : " + adminName);
        }
    }

    @Override
    public int getItemCount() {
        return (inventoryList != null) ? inventoryList.size() : 0;
    }

    public void updateInventoryList(List<Inventory> inventoryList) {
        this.inventoryList.clear();
        this.inventoryList.addAll(inventoryList);
        notifyDataSetChanged();
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private View rootView;
        private TextView invName, invDate, invQuantity, invSupplier, invNote, invAdmin;
        private ItemListener itemListener;

        public MyViewHolder(View itemView, final ItemListener itemListener) {
            super(itemView);
            this.rootView = itemView;
            invName = itemView.findViewById(R.id.tv_item_name);
            invDate = itemView.findViewById(R.id.tv_date);
            invQuantity = itemView.findViewById(R.id.tv_quantity);
            invSupplier = itemView.findViewById(R.id.tv_supplier);
            invNote = itemView.findViewById(R.id.tv_note);
            invAdmin = itemView.findViewById(R.id.tv_admin);
            rootView.setOnLongClickListener(this);
            this.itemListener = itemListener;
        }

        @Override
        public boolean onLongClick(View view) {
            itemListener.onItemLongClick(getAdapterPosition());
            return false;
        }
    }

    interface ItemListener {
        void onItemLongClick(int position);
    }
}
