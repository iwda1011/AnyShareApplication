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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by davidiwertowski on 21.12.16.
 */

public class VeranstaltungErstellen extends AppCompatActivity {

    DatabaseHelperVeranstaltung helper = new DatabaseHelperVeranstaltung(this);
    public Button button1;
    public Contact contact = new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veranstaltungerstellen);

        TextView tvKatWaehlen = (TextView) findViewById(R.id.TVKategorieWaehlen);


        Bundle zielkorb = getIntent().getExtras();
        String text2 = zielkorb.getString("spinnerkategorien");
        String text3 = "Veranstaltung erstellen > ";



        tvKatWaehlen.setText(text3 + text2);

        button1 = (Button) findViewById(R.id.BtnVerErstellen);

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                Bundle bundle = getIntent().getExtras();
                String spinnerkategorien = bundle.getString("spinnerkategorien");

                EditText beschreibung = (EditText)findViewById(R.id.ETBeschreibung);
                EditText detail = (EditText)findViewById(R.id.ETDetailbeschreibung);


                String beschreibungstr = beschreibung.getText().toString();
                String detailstr = detail.getText().toString();
                String userstr = contact.getUname();


                if(beschreibungstr.isEmpty()){
                    Toast passalert = Toast.makeText(VeranstaltungErstellen.this, "Beschreibung darf nicht leer sein", Toast.LENGTH_SHORT);
                    passalert.show();
                }else {
                    Veranstaltung veranstaltung = new Veranstaltung(beschreibungstr, detailstr, userstr, spinnerkategorien);

                    helper.insertVeranstaltung(veranstaltung);

                    Intent intent = new Intent(getApplicationContext(), Home.class);
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
        Intent intent = new Intent(VeranstaltungErstellen.this, Profil.class);
        startActivity(intent);
    }

}
