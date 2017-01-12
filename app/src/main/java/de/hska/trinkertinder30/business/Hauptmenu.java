package de.hska.trinkertinder30.business;


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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hska.trinkertinder30.R;
import de.hska.trinkertinder30.database.DatabaseHelper;
import de.hska.trinkertinder30.domain.Kontakt;
import de.hska.trinkertinder30.domain.Veranstaltung;


/**
 * Hauptmenü, von hier aus starten die Navigationsmöglichkeiten zu Profil, Veranstaltung suchen und Veranstaltung erstellen. Außerdem
 * wird eine Vorschau der zuletzt erstellten Veranstaltungen gezeigt
 *
 * @Version 1.0
 */
public class Hauptmenu extends AppCompatActivity {

    public Button erstellenBtn;
    public Button suchenBtn;
    public Button profilBtn;
    Kontakt contact = new Kontakt();
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauptmenu);

        final ListView listView = (ListView)findViewById(R.id.LVNews);

        helper = new DatabaseHelper(this);


        ArrayList<Veranstaltung> veranstaltungen = helper.getAllVeranstaltungen();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);



        if(veranstaltungen.size() != 0) {
            adapter.addAll(veranstaltungen);
            adapter.notifyDataSetChanged();
        }

        listView.setAdapter(adapter);

        String contactUname = contact.getUname();

        erstellenBtn = (Button) findViewById(R.id.veranserstellen);
        suchenBtn = (Button) findViewById(R.id.veransuchen);
        profilBtn = (Button) findViewById(R.id.profil);

        suchenBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(Hauptmenu.this, VeranstaltungSuchenKategorie.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", contact.getUname());
                System.out.println(contact.getUname()+"!!!!!!!");
                myIntent.putExtras(bundle);
                startActivity(myIntent);

            }
        });


        if (contactUname == "Gast") {
            erstellenBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    Toast.makeText(Hauptmenu.this, "Nicht eingeloggt", Toast.LENGTH_LONG).show();

                }
            });
            profilBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    Toast.makeText(Hauptmenu.this, "Nicht eingeloggt", Toast.LENGTH_LONG).show();

                }
            });

        } else {
            erstellenBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    Intent myIntent = new Intent(Hauptmenu.this, VeranstaltungErstellenKategorie.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", contact.getUname());
                    startActivity(myIntent);

                }
            });
            profilBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    Intent myIntent = new Intent(Hauptmenu.this, Profil.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", contact.getUname());
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
    @Override
    public void onBackPressed() {
        String contactUname = contact.getUname();
        if(contactUname =="Gast"){
            super.onBackPressed();
        }else
        Toast.makeText(getApplicationContext(), "Bitte unter Profil>Logout ausloggen", Toast.LENGTH_LONG).show();
    }

    public void clickItem(MenuItem item) {
        Intent intent = new Intent(Hauptmenu.this, Profil.class);
        startActivity(intent);
    }


}
