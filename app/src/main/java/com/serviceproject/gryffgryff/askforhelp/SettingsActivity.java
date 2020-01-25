package com.serviceproject.gryffgryff.askforhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button changeButton;
    Button editReceivers;
    Bundle buttonNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setVariables();
        setClickListener();
    }

    public void setVariables() {
        changeButton = (Button) findViewById(R.id.changeButtons);
        editReceivers = (Button) findViewById(R.id.editReceivers);
        buttonNames = getIntent().getExtras();
    }

    public void setClickListener() {
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, ChangeButtonsActivity.class);
                intent.putExtras(buttonNames);
                SettingsActivity.this.startActivity(intent);
            }
        });

        editReceivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, EditRecievers.class);
                SettingsActivity.this.startActivity(intent);
            }
        });
    }
}