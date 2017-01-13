package de.hska.trinkertinder30.business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hska.trinkertinder30.R;
import de.hska.trinkertinder30.database.DatabaseHelper;
import de.hska.trinkertinder30.domain.Kontakt;

/**
 * Anmeldefenster um einen neuen Benutzer anlegen zu können. Der neue Nutzer wird mithilfe des DatabaseHelper in die Datenbank gespeichert
 *
 * @Version 1.0
 */
public class Anmeldung extends Activity {

    public Button anmeldeButton;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmeldung);

        anmeldeButton = (Button) findViewById(R.id.BTNSignup);

        anmeldeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                EditText username = (EditText) findViewById(R.id.TFUser);
                EditText nachname = (EditText) findViewById(R.id.TFName);
                EditText vorname = (EditText) findViewById(R.id.TFVorname);
                EditText email = (EditText) findViewById(R.id.TFEmail);
                EditText passwort = (EditText) findViewById(R.id.TFPassword1);
                EditText passwortWdh = (EditText) findViewById(R.id.TFPassword2);

                String namestr = nachname.getText().toString();
                String vornamestr = vorname.getText().toString();
                String emailstr = email.getText().toString();
                String usernamestr = username.getText().toString();
                String passwortstr = passwort.getText().toString();
                String passwortWdhstr = passwortWdh.getText().toString();

                if(usernamestr.substring(usernamestr.length()-1).contains(" ")){
                    usernamestr = usernamestr.substring(0,usernamestr.length()-1);
                }

                if (usernamestr.isEmpty()) {
                    Toast.makeText(Anmeldung.this, "Kein Username eingegeben", Toast.LENGTH_SHORT).show();
                } else if (namestr.isEmpty()) {
                    Toast.makeText(Anmeldung.this, "Kein Name eingegeben", Toast.LENGTH_SHORT).show();
                } else if (vornamestr.isEmpty()) {
                    Toast.makeText(Anmeldung.this, "Kein Vorname eingegeben", Toast.LENGTH_SHORT).show();
                } else if (emailstr.isEmpty()) {
                    Toast.makeText(Anmeldung.this, "Keine E-Mail-Adresse eingegeben", Toast.LENGTH_SHORT).show();
                } else if (passwortstr.isEmpty()) {
                    Toast.makeText(Anmeldung.this, "Kein Passwort eingegeben", Toast.LENGTH_SHORT).show();
                } else if (!passwortstr.equals(passwortWdhstr)) {
                    Toast.makeText(Anmeldung.this, "Passwort stimmt nicht überein!", Toast.LENGTH_SHORT).show();
                } else {
                    Kontakt contact = new Kontakt();

                    contact.setName(namestr);
                    contact.setVorname(vornamestr);
                    contact.setEmail(emailstr);
                    contact.setUname(usernamestr);
                    contact.setPass(passwortstr);

                    helper.insertContact(contact);

                    Intent myIntent = new Intent(Anmeldung.this, Hauptmenu.class);

                    startActivity(myIntent);
                }
            }
        });
    }
}