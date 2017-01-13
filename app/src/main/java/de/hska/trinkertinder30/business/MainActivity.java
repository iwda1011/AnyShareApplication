package de.hska.trinkertinder30.business;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hska.trinkertinder30.R;
import de.hska.trinkertinder30.database.DatabaseHelper;
import de.hska.trinkertinder30.domain.Kontakt;

/**
 * MainActivity-Klasse, Startpunkt/-klasse der App, Weiterleitung zum Haupmenü-Bildschirm durch "Login" oder "Weiter als Gast",
 * Weiterleitung zu Anmelde-Bildschirm
 *
 * @Version 1.0
 */
public class MainActivity extends AppCompatActivity {

    public Button loginButton;
    public Button anmeldeButton;
    public Button gastButton;

    Kontakt kontakt;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);

        loginButton = (Button) findViewById(R.id.BTNLogin);
        anmeldeButton = (Button) findViewById(R.id.BTNSignStart);
        gastButton = (Button) findViewById(R.id.BTNGast);

        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                EditText username = (EditText)findViewById(R.id.TFUsername);
                String usernamestr = username.getText().toString();

                EditText passwort = (EditText)findViewById(R.id.TFPassword2);
                String passwortstr =passwort.getText().toString();

                String passwordtest = helper.searchPassword(usernamestr);
                if(passwortstr.equals(passwordtest)){

                    Intent myIntent = new Intent(MainActivity.this, Hauptmenu.class);
                    Kontakt contact = new Kontakt();
                    contact.setUname(usernamestr);

                    startActivity(myIntent);

                } else {

                    Toast passwordAlert = Toast.makeText(MainActivity.this, "Passwort/Nutzername stimmt nicht überein", Toast.LENGTH_SHORT);
                    passwordAlert.show();
                }
            }
        });

        anmeldeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                Intent myIntent = new Intent(MainActivity.this, Anmeldung.class);

                startActivity(myIntent);

            }
        });

        gastButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                kontakt = new Kontakt();
                kontakt.setUname("Gast");
                Intent myIntent = new Intent(MainActivity.this, Hauptmenu.class);
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

        getMenuInflater().inflate(R.menu.menugrey, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem bedMenuItem = menu.findItem(R.id.MItemUserGrey);
        return super.onPrepareOptionsMenu(menu);
    }


}
