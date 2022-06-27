package com.dolla.fragmentsrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClicked {

    TextView tvName, tvTelephone;
    EditText etName, etTelephone;
    Button btAddPerson;
    ImageView leftAdd, clear;
    ListFrag listFrag;
    FragmentManager fragmentManager;
    View portrait, landscape;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        tvTelephone = findViewById(R.id.tvTelephone);
        etName = findViewById(R.id.etName);
        etTelephone = findViewById(R.id.etTelephone);
        btAddPerson = findViewById(R.id.btAddPerson);
        leftAdd = findViewById(R.id.leftAdd);
        clear = findViewById(R.id.clear);
        portrait = findViewById(R.id.layout_portrait);
        landscape = findViewById(R.id.layout_land);

        fragmentManager = this.getSupportFragmentManager();
        listFrag = (ListFrag) fragmentManager.findFragmentById(R.id.listFrag);


        //at Portrait mode
        if (portrait != null) {
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.listFrag))
                    .hide(manager.findFragmentById(R.id.detailFrag))
                    .hide(manager.findFragmentById(R.id.addPersonFrag))
                    .setReorderingAllowed(true).addToBackStack(null).commit();
        }


        //at Landscape mode
        if (landscape != null) {
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.listFrag))
                    .show(manager.findFragmentById(R.id.detailFrag))
                    .show(manager.findFragmentById(R.id.addPersonFrag))
                    .setReorderingAllowed(true).addToBackStack(null).commit();
        }


        leftAdd.setOnClickListener(view -> {
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.listFrag))
                    .hide(manager.findFragmentById(R.id.detailFrag))
                    .show(manager.findFragmentById(R.id.addPersonFrag))
                    .setReorderingAllowed(true).addToBackStack(null).commit();
        });


        btAddPerson.setOnClickListener(view -> {
            String name = etName.getText().toString().trim();
            String telephone = etTelephone.getText().toString().trim();
            setPersonToList(name, telephone);
        });


        clear.setOnClickListener(view -> {
            if (etName.getText().toString().trim().isEmpty() && etTelephone.getText().toString().trim().isEmpty()) {
                etName.setText(null);
                etTelephone.setText(null);
            } else {
                etName.setText(null);
                etTelephone.setText(null);
                Toast.makeText(this, "Clear All!", Toast.LENGTH_SHORT).show();
                closeKeyboard();
            }
        });

    }


    @Override
    public void onItemClicked(int index) {

        tvName.setText(ApplicationClass.people.get(index).getName());
        tvTelephone.setText(ApplicationClass.people.get(index).getTelephone());

        if (findViewById(R.id.layout_portrait) != null) {
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .hide(manager.findFragmentById(R.id.listFrag))
                    .show(manager.findFragmentById(R.id.detailFrag))
                    .hide(manager.findFragmentById(R.id.addPersonFrag))
                    .setReorderingAllowed(true).addToBackStack(null).commit();
        }

    }


    public void setPersonToList(String name, String telephone) {

        if (name.isEmpty() || telephone.isEmpty())
            Toast.makeText(MainActivity.this, "Please Fill All The Fields", Toast.LENGTH_SHORT).show();
        else {
            ApplicationClass.people.add(new Person(name, telephone));
            Toast.makeText(MainActivity.this, "Successfully Added!!", Toast.LENGTH_SHORT).show();
            etName.setText(null);
            etTelephone.setText(null);
            listFrag.notifyDataChanged();
            if (landscape != null)
                onItemClicked(ApplicationClass.people.size() - 1);
        }
        closeKeyboard();
    }


    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

}