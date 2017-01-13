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
import android.widget.Spinner;

import java.util.ArrayList;

import de.hska.trinkertinder30.R;
import de.hska.trinkertinder30.database.DatabaseHelper;
import de.hska.trinkertinder30.domain.Kontakt;

/**
 * Erste Ansicht des Veranstaltung erstellen-Prozesses
 * Mit dieser Klasse wird die Ansicht ermöglicht, damit die passende Kategorie ausgewählt werden kann, welche man für die jeweilige Veranstaltung auswählen möchte
 *
 * @Version 1.0
 */
public class VeranstaltungErstellenKategorie extends AppCompatActivity {

    public Button waehlenButton;

    DatabaseHelper helper = new DatabaseHelper(this);

    public Kontakt kontakt = new Kontakt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstellenkategoriewaehlen);

        ArrayList<String> array_kategorien = helper.getAllKategorien();

        Spinner spinner = (Spinner)findViewById(R.id.SPKategorien);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_row, array_kategorien);

        spinner.setAdapter(adapter);

        waehlenButton = (Button) findViewById(R.id.BtnKatWahlen);

        waehlenButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){

                Spinner spinner = (Spinner)findViewById(R.id.SPKategorien);
                String spinnerkategorien = spinner.getSelectedItem().toString();

                Bundle bundle = new Bundle();
                bundle.putString("spinnerkategorien", spinnerkategorien);

                Intent myIntent = new Intent(VeranstaltungErstellenKategorie.this, VeranstaltungErstellenAbschluss.class);
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

        String username = kontakt.getUname();
        if (username == "Gast") {
            getMenuInflater().inflate(R.menu.menured, menu);
        } else {
            getMenuInflater().inflate(R.menu.menugreen, menu);
        }

        return true;

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        String username = kontakt.getUname();
        MenuItem bedMenuItem = menu.findItem(R.id.MItemUser);
        bedMenuItem.setTitle(username);

        return super.onPrepareOptionsMenu(menu);

    }
    public void clickItem(MenuItem item) {
        Intent intent = new Intent(VeranstaltungErstellenKategorie.this, Profil.class);
        startActivity(intent);
    }



}
