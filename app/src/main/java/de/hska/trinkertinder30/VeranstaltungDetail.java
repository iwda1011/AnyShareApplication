package de.hska.trinkertinder30;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidiwertowski on 21.12.16.
 */

public class VeranstaltungDetail extends AppCompatActivity {

    DatabaseHelperVeranstaltung helper = new DatabaseHelperVeranstaltung(this);
    Contact contact = new Contact();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veranstaltungdetail);

        TextView tvPfad = (TextView) findViewById(R.id.TVPfad);
        TextView tvBeschreibung = (TextView) findViewById(R.id.TVBeschreibung);
        TextView tvDetail = (TextView) findViewById(R.id.TVDetail);
        TextView tvUser = (TextView) findViewById(R.id.TVUser);

        Bundle bundle = getIntent().getExtras();
        String beschreibungspfad = bundle.getString("beschreibungspfad");
        String beschreibung = bundle.getString("beschreibung");
        String kategoriename = bundle.getString("kategorie");

        ArrayList<String> details = helper.getDetailsAndUser(beschreibung, kategoriename);

        tvPfad.setText(beschreibungspfad);
        tvBeschreibung.setText(beschreibung);
        tvDetail.setText(details.get(0));
        tvUser.setText(details.get(1));


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
        Intent intent = new Intent(VeranstaltungDetail.this, Profil.class);
        startActivity(intent);
    }

}
