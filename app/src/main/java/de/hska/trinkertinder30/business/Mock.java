package de.hska.trinkertinder30.business;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import de.hska.trinkertinder30.R;
import de.hska.trinkertinder30.domain.Kontakt;

/**
 * Diese Klasse dient lediglich für eine Darstellung, wenn keine Einträge in einer Kategorie erstellt wurde
 *
 * @Version 1.0
 */
public class Mock extends AppCompatActivity {

    Kontakt kontakt = new Kontakt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock);

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
        Intent intent = new Intent(Mock.this, Profil.class);
        startActivity(intent);
    }

}
