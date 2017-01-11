package de.hska.trinkertinder30;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by davidiwertowski on 16.12.16.
 */

public class SignUp extends Activity {

    public Button signupBtn;
    DatabaseHelperKontakte helper = new DatabaseHelperKontakte(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup);


        signupBtn = (Button) findViewById(R.id.BTNSignup);

        signupBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                EditText username = (EditText) findViewById(R.id.TFUser);
                EditText name = (EditText) findViewById(R.id.TFName);
                EditText vorname = (EditText) findViewById(R.id.TFVorname);
                EditText email = (EditText) findViewById(R.id.TFEmail);
                EditText pass = (EditText) findViewById(R.id.TFPassword1);
                EditText passWdh = (EditText) findViewById(R.id.TFPassword2);



                String namestr = name.getText().toString();
                String vornamestr = vorname.getText().toString();
                String emailstr = email.getText().toString();
                String usernamestr = username.getText().toString();
                String passstr = pass.getText().toString();
                String pass2str = passWdh.getText().toString();

                if(usernamestr.substring(usernamestr.length()-1).contains(" ")){
                    usernamestr = usernamestr.substring(0,usernamestr.length()-1);
                }

                if (usernamestr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Kein Username eingegeben", Toast.LENGTH_SHORT).show();
                } else if (namestr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Kein Name eingegeben", Toast.LENGTH_SHORT).show();
                } else if (vornamestr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Kein Vorname eingegeben", Toast.LENGTH_SHORT).show();
                } else if (emailstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Keine E-Mail-Adresse eingegeben", Toast.LENGTH_SHORT).show();
                } else if (passstr.isEmpty()) {
                    Toast.makeText(SignUp.this, "Kein Passwort eingegeben", Toast.LENGTH_SHORT).show();
                } else if (!passstr.equals(pass2str)) {
                    Toast.makeText(SignUp.this, "Passwort stimmt nicht überein!", Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact();
                    contact.setName(namestr);
                    contact.setVorname(vornamestr);
                    contact.setEmail(emailstr);
                    contact.setUname(usernamestr);

                    contact.setPass(passstr);

                    helper.insertContact(contact);

                    Intent myIntent = new Intent(SignUp.this, Home.class);

                    startActivity(myIntent);
                }
            }
        });
    }
}
