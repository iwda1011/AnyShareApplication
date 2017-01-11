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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidiwertowski on 21.12.16.
 */

public class VeranstaltungSuchen extends AppCompatActivity {

    private DatabaseHelperVeranstaltung databaseHelperVeranstaltung;
    Contact contact = new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veranstaltungsuchen);


        TextView tvPfad = (TextView) findViewById(R.id.TVPfad2);


        Bundle bundle = getIntent().getExtras();
        final String kategoriename = bundle.getString("kategoriename");
        final String pfadname = "Kategorie > " + kategoriename;
        tvPfad.setText(pfadname);

        final ListView listView = (ListView)findViewById(R.id.LVBeschreibungen);

        databaseHelperVeranstaltung = new DatabaseHelperVeranstaltung(this);

        /**Hier werden alle Unterkategorien gesammelt
         * TODO zusätzlich IDs sammeln und unten übergeben im Intent*/
        ArrayList<String> array_kategorien = databaseHelperVeranstaltung.getAllBeschreibungen(kategoriename);

        if (array_kategorien.size() == 0){

            Intent myIntent = new Intent(VeranstaltungSuchen.this, Mock.class);
            startActivity(myIntent);
            finish();

        } else {

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

            if (array_kategorien.size() != 0) {
                adapter.addAll(array_kategorien);
                adapter.notifyDataSetChanged();
            }

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Intent myIntent = new Intent(VeranstaltungSuchen.this, VeranstaltungDetail.class);

                    String beschreibung = (String) listView.getItemAtPosition(position);
                    Bundle bundle = new Bundle();

                    bundle.putString("beschreibungspfad", pfadname + " > " + beschreibung);
                    bundle.putString("beschreibung", beschreibung);
                    bundle.putString("kategorie", kategoriename);

                    myIntent.putExtras(bundle);

                    startActivity(myIntent);

                }
            });
        }

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
        Intent intent = new Intent(VeranstaltungSuchen.this, Profil.class);
        startActivity(intent);
    }
}
