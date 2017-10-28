package com.novalfakhri.inventoryapp;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.novalfakhri.inventoryapp.database.Inventory;

import static android.content.ContentValues.TAG;

/**
 * Created by Toshiba on 10/28/2017.
 */

public class InventoryDialog extends DialogFragment {
    private Button btnSave, btnUpdate, btnDelete;
    private EditText etItemName, etDate, etQuantity, etSupplier, etNote;
    private Long id;
    private String adminName;
    private InvDialogCallback dialogCallback;

    public static InventoryDialog newInstance(boolean isAdd, String admin, Long id, String itemName, String date,
                                                  int quantity, String supplier, String note) {
        Bundle bundle = new Bundle();
        if (id != null) {
            bundle.putLong("inventoryId", id);
        }
        bundle.putBoolean("isAdd", isAdd);
        bundle.putString("admin", admin);
        bundle.putString("itemName", itemName);
        bundle.putString("date", date);
        bundle.putInt("quantity", quantity);
        bundle.putString("supplier", supplier);
        bundle.putString("note", note);
        InventoryDialog fragment = new InventoryDialog();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setDialogCallback(InvDialogCallback dialogCallback) {
        this.dialogCallback = dialogCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Light);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_add_item, container, false);
        btnSave = rootView.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCallback.onSaveClick(new Inventory(null, etItemName.getText().toString(),
                        etDate.getText().toString(), Integer.parseInt(etQuantity.getText().toString()),
                        etSupplier.getText().toString(), etNote.getText().toString(), adminName));
                dismiss();
            }
        });
        btnDelete = rootView.findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCallback.onDeleteClick(id);
                dismiss();
            }
        });
        btnUpdate = rootView.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCallback.onUpdateClick(id, etItemName.getText().toString(),
                        etDate.getText().toString(), Integer.parseInt(etQuantity.getText().toString()),
                        etSupplier.getText().toString(), etNote.getText().toString());
            }
        });
        etItemName = rootView.findViewById(R.id.input_item_name);
        etDate = rootView.findViewById(R.id.input_date);
        etQuantity = rootView.findViewById(R.id.input_quantity);
        etSupplier = rootView.findViewById(R.id.input_supplier);
        etNote = rootView.findViewById(R.id.input_note);
        adminName = getArguments().getString("admin");
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String adminName = bundle.getString("admin", "");
            String itemName = bundle.getString("itemName", "");
            String date = bundle.getString("date", "");
            int quantity = bundle.getInt("quantity", 0);
            String supplier = bundle.getString("supplier", "");
            String note = bundle.getString("note", "");
            Log.d(TAG, "value admin = " + adminName);
            Log.d(TAG, "value name = " + itemName);
            Log.d(TAG, "value date = " + date);
            Log.d(TAG, "value quantity = " + quantity);
            Log.d(TAG, "value supplier = " + supplier);
            Log.d(TAG, "value note= " + note);
            etItemName.setText(itemName);
            etDate.setText(date);
            etQuantity.setText(String.valueOf(quantity));
            etSupplier.setText(supplier);
            etNote.setText(note);

            boolean isAdd = bundle.getBoolean("isAdd", false);
            btnSave.setVisibility(
                    (isAdd) ? View.VISIBLE : View.GONE
            );
            btnDelete.setVisibility(
                    (isAdd) ? View.GONE : View.VISIBLE
            );

            btnUpdate.setVisibility(
                    (isAdd) ? View.GONE : View.VISIBLE
            );

            Long _id = bundle.getLong("inventoryId");
            if (_id != 0) {
                this.id = _id;
            }
        }
    }

    public interface InvDialogCallback {
        void onSaveClick(Inventory inventory);
        void onDeleteClick(Long id);
        void onUpdateClick(Long id, String itemName, String date, int quantity, String supplier, String note);
    }

}
