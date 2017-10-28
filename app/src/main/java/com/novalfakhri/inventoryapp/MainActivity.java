package com.novalfakhri.inventoryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.novalfakhri.inventoryapp.database.DaoSession;
import com.novalfakhri.inventoryapp.database.Inventory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements InventoryDialog.InvDialogCallback, MyAdapter.ItemListener {
    private RecyclerView recyclerView;
    private TextView user;
    private String admin;
    private MyAdapter adapter;
    private Button btnAdd;
    private InventoryDialog inventoryDialog;
    private DaoSession daoSession;
    private List<Inventory> inventoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAdmin();
        setbtnAdd();
        setRecyclerView();
        setDaoSession();
    }

    @Override
    public void onItemLongClick(int position) {
        Inventory inventory = adapter.getInventoryList().get(position);
        inventoryDialog = InventoryDialog.newInstance(false, inventory.getAdminName(),
                inventory.getId(), inventory.getItemName(), inventory.getDate(), inventory.getQuantity(),
                inventory.getSupplier(), inventory.getNote());
        inventoryDialog.setDialogCallback(this);

        if (!inventoryDialog.isVisible()) {
            inventoryDialog.show(getFragmentManager(), inventoryDialog.getTag());
        }
    }
    private void setAdmin(){
        user = findViewById(R.id.user_admin);
        admin = getIntent().getStringExtra("admin");
        user.setText(admin);
    }
    private void setbtnAdd(){
        btnAdd = findViewById(R.id.btn_add_new_item);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogFragment(true);

            }
        });
    }
    private void setRecyclerView(){
        recyclerView = findViewById(R.id.rv_item_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
        adapter = new MyAdapter(MainActivity.this, this);
        recyclerView.setAdapter(adapter);
    }
    private void setDaoSession(){
        daoSession = ((MainApp) getApplication()).getDaoSession();
        inventoryList = new ArrayList<>();
        inventoryList = daoSession.getInventoryDao().loadAll();
        adapter.updateInventoryList(inventoryList);
    }

    @Override
    public void onSaveClick(Inventory inventory) {
        daoSession.getInventoryDao().insert(inventory);
        adapter.updateInventoryList(
                daoSession.getInventoryDao().loadAll()
        );
    }

    @Override
    public void onDeleteClick(Long id) {
        for (Inventory inventory : daoSession.getInventoryDao().loadAll()) {
            if (id.equals(inventory.getId())) {
                daoSession.getInventoryDao().delete(inventory);
                adapter.updateInventoryList(
                        daoSession.getInventoryDao().loadAll()
                );
                break;
            }
        }
    }

    @Override
    public void onUpdateClick(Long id, String itemName, String date, int quantity, String supplier, String note) {
        for (Inventory inventory : daoSession.getInventoryDao().loadAll()) {
            if (id.equals(inventory.getId())) {
                inventory.setItemName(itemName);
                inventory.setDate(date);
                inventory.setQuantity(quantity);
                inventory.setSupplier(supplier);
                inventory.setNote(note);
                daoSession.getInventoryDao().update(inventory);
                adapter.updateInventoryList(
                        daoSession.getInventoryDao().loadAll()
                );
                break;
            }
        }

    }
    private void showDialogFragment(boolean isAdd) {
        inventoryDialog = InventoryDialog.newInstance(isAdd, admin, null, "", "", 0, "","");
        inventoryDialog.setDialogCallback(this);

        if (!inventoryDialog.isVisible()) {
            inventoryDialog.show(getFragmentManager(), inventoryDialog.getTag());
        }
    }

}
