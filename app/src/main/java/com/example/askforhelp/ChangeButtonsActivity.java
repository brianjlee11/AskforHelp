package com.example.askforhelp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeButtonsActivity extends AppCompatActivity {

    TextView[] buttons = new TextView[4];
    TextView firstButton;
    TextView secondButton;
    TextView thirdButton;
    TextView fourthButton;

    Button deleteFirstButton;
    Button deleteSecondButton;
    Button deleteThirdButton;
    Button deleteFourthButton;
    Button saveNewButton;

    Button homeButton;


    EditText setNewButton;

    SharedPreferences sharedPreferences;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_buttons);

        setVariables();
        addButtonsToArray();
        setButtonNames();
        setClickListener();
    }

    public void addButtonsToArray() {
        buttons[0] = firstButton;
        buttons[1] = secondButton;
        buttons[2] = thirdButton;
        buttons[3] = fourthButton;
    }

    public void setVariables() {
        firstButton = findViewById(R.id.firstButton);
        secondButton = findViewById(R.id.secondButton);
        thirdButton = findViewById(R.id.thirdButton);
        fourthButton = findViewById(R.id.fourthButton);

        deleteFirstButton = findViewById(R.id.deleteFirstButton);
        deleteSecondButton = findViewById(R.id.deleteSecondButton);
        deleteThirdButton = findViewById(R.id.deleteThirdButton);
        deleteFourthButton = findViewById(R.id.deleteFourthButton);

        saveNewButton = findViewById(R.id.saveNewButton);
        setNewButton = findViewById(R.id.setNewButton);

        homeButton = findViewById(R.id.homeButtonRequests);

        context = ChangeButtonsActivity.this;
    }

    public void setButtonNames() {
        try {
            sharedPreferences = context.getSharedPreferences("com.serviceproject.gryffgryff.askforhelp.PREFERENCES", Context.MODE_PRIVATE);
            firstButton.setText(sharedPreferences.getString("first_text", ""));
            secondButton.setText(sharedPreferences.getString("second_text", ""));
            thirdButton.setText(sharedPreferences.getString("third_text", ""));
            fourthButton.setText(sharedPreferences.getString("fourth_text", ""));
        } catch (Exception e) {
            firstButton.setText("");
            secondButton.setText("");
            thirdButton.setText("");
            fourthButton.setText("");
        }
    }

    public void setClickListener() {
        deleteFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bumpUpNames(0);
            }
        });

        deleteSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bumpUpNames(1);
            }
        });

        deleteThirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bumpUpNames(2);
            }
        });

        deleteFourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bumpUpNames(3);
            }
        });

        saveNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewButton();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeButtonsActivity.this, ChooseRequestsActivity.class);
                setNewButtonNames();
                ChangeButtonsActivity.this.startActivity(intent);
            }
        });
    }

    public void bumpUpNames(int numButtons) {
        while (numButtons < 3) {
            buttons[numButtons].setText(buttons[numButtons+1].getText().toString());
            numButtons++;
        }
        buttons[3].setText("");
        saveNewButton.setEnabled(true);
    }

    public void addNewButton() {
        //get the text the user typed in using getText()
        String buttonName = setNewButton.getText().toString();
        int button = 0;
        //loop through the buttons
        while (button <= 3) {
            //check if the button has already been set to a text
            if (buttons[button].getText().toString() == "") {
                if(button == 3) {
                    saveNewButton.setEnabled(false);
                }
                //if the button is not set yet, set it to the text you got from the user
                buttons[button].setText(buttonName);
                button = 100;
            }
            button++;
        }
        setNewButton.setText("");
    }

    public void setNewButtonNames() {
        try {
            sharedPreferences = context.getSharedPreferences("com.serviceproject.gryffgryff.askforhelp.PREFERENCES", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("first_text", firstButton.getText().toString());
            editor.putString("second_text", secondButton.getText().toString());
            editor.putString("third_text", thirdButton.getText().toString());
            editor.putString("fourth_text", fourthButton.getText().toString());
            editor.apply();
        } catch (Exception e) {
            Toast.makeText(context, "editing shared preferences file failed", Toast.LENGTH_LONG).show();
        }
    }
}