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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidiwertowski on 23.12.16.
 */

public class Profil extends AppCompatActivity {

    DatabaseHelperKontakte db = new DatabaseHelperKontakte(this);
    Contact contact = new Contact();
    private Button BtnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        TextView tvUsername = (TextView)findViewById(R.id.TVUsername);
        TextView tvNachname = (TextView)findViewById(R.id.TVNachname);
        TextView tvVorname = (TextView)findViewById(R.id.TVVorname);
        TextView tvEmail = (TextView)findViewById(R.id.TVEmail);

        List<Contact> contacts = db.getKontaktToShow(contact.getUname());


        String name = contacts.get(0).getName().toString();
        String vorname = contacts.get(0).getVorname().toString();
        String email = contacts.get(0).getEmail().toString();

        tvUsername.setText(contact.getUname());
        tvNachname.setText(name);
        tvVorname.setText(vorname);
        tvEmail.setText(email);

        BtnLogout = (Button) findViewById(R.id.BtnLogout);

        BtnLogout.setOnClickListener(new View.OnClickListener() {
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

        String username = contact.getUname();
        MenuItem bedMenuItem = menu.findItem(R.id.MItemUser);
        bedMenuItem.setTitle(username);

        return super.onPrepareOptionsMenu(menu);

    }
    public void clickItem(MenuItem item){

    }
}
