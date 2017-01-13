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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.hska.trinkertinder30.R;
import de.hska.trinkertinder30.database.DatabaseHelper;
import de.hska.trinkertinder30.domain.Kontakt;
import de.hska.trinkertinder30.domain.Veranstaltung;

/**
 * Zweite/finale Ansicht des Veranstaltung erstellen-Prozesses
 * In dieser Klasse werden die Informationen gesetzt, nachdem die Kategorie ausgewählt wurde, damit eine Veranstaltung erstellt werden kann
 * Nach Bestätigung durch den Erstellen-Button, wird die Veranstaltung in der Datenbank gespeichert
 *
 * @Version 1.0
 */
public class VeranstaltungErstellenAbschluss extends AppCompatActivity {

    public Button erstellenButton;

    DatabaseHelper helper = new DatabaseHelper(this);

    public Kontakt kontakt = new Kontakt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erstellenabschluss);

        TextView tvKatWaehlen = (TextView) findViewById(R.id.TVKategorieWaehlen);

        Bundle bundle = getIntent().getExtras();
        String kategorien = bundle.getString("spinnerkategorien");
        String mockstr = "Veranstaltung erstellen > ";

        tvKatWaehlen.setText(mockstr + kategorien);

        erstellenButton = (Button) findViewById(R.id.BtnVerErstellen);

        erstellenButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                Bundle bundle = getIntent().getExtras();
                String spinnerkategorien = bundle.getString("spinnerkategorien");

                EditText beschreibung = (EditText)findViewById(R.id.ETBeschreibung);
                EditText detail = (EditText)findViewById(R.id.ETDetailbeschreibung);

                String beschreibungstr = beschreibung.getText().toString();
                String detailstr = detail.getText().toString();
                String userstr = kontakt.getUname();

                if(beschreibungstr.isEmpty()){
                    Toast passalert = Toast.makeText(VeranstaltungErstellenAbschluss.this, "Beschreibung darf nicht leer sein", Toast.LENGTH_SHORT);
                    passalert.show();
                }else {
                    Veranstaltung veranstaltung = new Veranstaltung(beschreibungstr, detailstr, userstr, spinnerkategorien);

                    helper.insertVeranstaltung(veranstaltung);

                    Intent intent = new Intent(getApplicationContext(), Hauptmenu.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Veranstaltung erstellt", Toast.LENGTH_LONG).show();
                }

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
        Intent intent = new Intent(VeranstaltungErstellenAbschluss.this, Profil.class);
        startActivity(intent);
    }

}
