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

import java.util.List;

import de.hska.trinkertinder30.R;
import de.hska.trinkertinder30.database.DatabaseHelper;
import de.hska.trinkertinder30.domain.Kontakt;

/**
 * Klasse für die Darstellung der Profilansicht, mit den Buttons für Ausloggen und Daten ändern (wobei Daten ändern noch nicht implementiert ist)
 *
 * @Version 1.0
 */
public class Profil extends AppCompatActivity {

    private Button logoutButton;

    DatabaseHelper helper = new DatabaseHelper(this);

    Kontakt kontakt = new Kontakt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        TextView tvUsername = (TextView)findViewById(R.id.TVUsername);
        TextView tvNachname = (TextView)findViewById(R.id.TVNachname);
        TextView tvVorname = (TextView)findViewById(R.id.TVVorname);
        TextView tvEmail = (TextView)findViewById(R.id.TVEmail);

        List<Kontakt> contacts = helper.getContactToShow(kontakt.getUname());

        String namestr = contacts.get(0).getName().toString();
        String vornamestr = contacts.get(0).getVorname().toString();
        String emailstr = contacts.get(0).getEmail().toString();

        tvUsername.setText(kontakt.getUname());
        tvNachname.setText(namestr);
        tvVorname.setText(vornamestr);
        tvEmail.setText(emailstr);

        logoutButton = (Button) findViewById(R.id.BtnLogout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(Profil.this, MainActivity.class);
                Toast.makeText(Profil.this, "Sie sind jetzt ausgeloggt", Toast.LENGTH_SHORT).show();
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


        getMenuInflater().inflate(R.menu.menugreen, menu);

        return true;

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        String username = kontakt.getUname();
        MenuItem bedMenuItem = menu.findItem(R.id.MItemUser);
        bedMenuItem.setTitle(username);

        return super.onPrepareOptionsMenu(menu);

    }
    public void clickItem(MenuItem item){

    }
}
