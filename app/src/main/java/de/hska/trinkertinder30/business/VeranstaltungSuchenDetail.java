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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hska.trinkertinder30.R;
import de.hska.trinkertinder30.database.DatabaseHelper;
import de.hska.trinkertinder30.domain.Kontakt;

/**
 * Dritte/finale Ansicht des Veranstaltung suchen-Prozesses
 * Mit dieser Klasse wird die Ansicht realisiert, damit man die ausgewählte Veranstaltung anfragen/ablehnen kann
 *
 * @Version 1.0
 */
public class VeranstaltungSuchenDetail extends AppCompatActivity {

    public Button zurueckButton;
    public Button anfragenButton;

    DatabaseHelper helper = new DatabaseHelper(this);

    Kontakt kontakt = new Kontakt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suchendetail);

        TextView tvPfad = (TextView) findViewById(R.id.TVPfad);
        TextView tvBeschreibung = (TextView) findViewById(R.id.TVBeschreibung);
        TextView tvDetail = (TextView) findViewById(R.id.TVDetail);
        TextView tvUser = (TextView) findViewById(R.id.TVUser);

        Bundle bundle = getIntent().getExtras();

        String beschreibungspfad = bundle.getString("beschreibungspfad");
        String beschreibung = bundle.getString("beschreibung");

        ArrayList<String> details = helper.getDetailsAndUser(beschreibung);

        tvPfad.setText(beschreibungspfad);
        tvBeschreibung.setText(beschreibung);
        tvDetail.setText(details.get(0));
        tvUser.setText(details.get(1));

        anfragenButton = (Button) findViewById(R.id.BTNbestatigen);

        anfragenButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){

                Intent myIntent = new Intent(VeranstaltungSuchenDetail.this, Hauptmenu.class);
                Toast.makeText(getApplicationContext(), "Anfrage gesendet", Toast.LENGTH_SHORT).show();
                startActivity(myIntent);

            }
        });

        zurueckButton = (Button) findViewById(R.id.BTNzuruck);

        zurueckButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View arg0){

                Intent myIntent = new Intent(VeranstaltungSuchenDetail.this, VeranstaltungSuchenKategorie.class);

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
        Intent intent = new Intent(VeranstaltungSuchenDetail.this, Profil.class);
        startActivity(intent);
    }

}
