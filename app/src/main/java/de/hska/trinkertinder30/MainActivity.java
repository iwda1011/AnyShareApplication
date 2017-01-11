package de.hska.trinkertinder30;

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

public class MainActivity extends AppCompatActivity {

    public Button loginBtn;
    public Button signInBtn;
    public Button gastBtn;

    Contact contact;

    DatabaseHelperKontakte helper = new DatabaseHelperKontakte(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = (Button) findViewById(R.id.BTNLogin);
        signInBtn = (Button) findViewById(R.id.BTNSignStart);
        gastBtn = (Button) findViewById(R.id.BTNGast);


        loginBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){

                EditText username = (EditText)findViewById(R.id.TFUsername);
                String usernamestr = username.getText().toString();

                if(usernamestr.substring(usernamestr.length()-1).contains(" ")){
                    usernamestr = usernamestr.substring(0,usernamestr.length()-1);
                }

                EditText b = (EditText)findViewById(R.id.TFPassword2);
                String pass =b.getText().toString();

                String password = helper.searchPass(usernamestr);
                if(pass.equals(password)){

                    Intent myIntent = new Intent(MainActivity.this, Home.class);
                    Contact contact = new Contact();
                    contact.setUname(usernamestr);

                    startActivity(myIntent);

                } else {

                    Toast passalert = Toast.makeText(MainActivity.this, "Password doesnt match!", Toast.LENGTH_SHORT);
                    passalert.show();
                }

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){


                Intent myIntent = new Intent(MainActivity.this, SignUp.class);

                startActivity(myIntent);

            }
        });

        gastBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){


                contact = new Contact();
                contact.setUname("Gast");
                Intent myIntent = new Intent(MainActivity.this, Home.class);
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

        Contact c = new Contact();
        MenuItem bedMenuItem = menu.findItem(R.id.MItemUserGrey);

        return super.onPrepareOptionsMenu(menu);

    }


}
