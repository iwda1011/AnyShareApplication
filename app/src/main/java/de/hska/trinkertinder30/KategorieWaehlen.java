package de.hska.trinkertinder30;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by davidiwertowski on 19.12.16.
 */

public class KategorieWaehlen extends AppCompatActivity {

    DatabaseHelperKategorien helper = new DatabaseHelperKategorien(this);
    public Button waehlenBtn;
    public Contact contact = new Contact();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategoriewaehlen);

        ArrayList<String> array_kategorien = helper.getAllKategorien();

        Spinner spinner = (Spinner)findViewById(R.id.SPKategorien);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_row, array_kategorien);

        spinner.setAdapter(adapter);

        waehlenBtn = (Button) findViewById(R.id.BtnKatWahlen);

        waehlenBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){

                Spinner spinner = (Spinner)findViewById(R.id.SPKategorien);
                String spinnerkategorien = spinner.getSelectedItem().toString();

                Bundle bundle = new Bundle();
                bundle.putString("spinnerkategorien", spinnerkategorien);

                Intent myIntent = new Intent(KategorieWaehlen.this, VeranstaltungErstellen.class);
                myIntent.putExtras(bundle);
                startActivity(myIntent);

            }
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.custom_logo);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        String username = contact.getUname();
        if (username == "Gast") {
            getMenuInflater().inflate(R.menu.menured, menu);
        } else {
            getMenuInflater().inflate(R.menu.menugreen, menu);
        }

        return true;

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        String username = contact.getUname();
        MenuItem bedMenuItem = menu.findItem(R.id.MItemUser);
        bedMenuItem.setTitle(username);

        return super.onPrepareOptionsMenu(menu);

    }
    public void clickItem(MenuItem item) {
        Intent intent = new Intent(KategorieWaehlen.this, Profil.class);
        startActivity(intent);
    }



}
