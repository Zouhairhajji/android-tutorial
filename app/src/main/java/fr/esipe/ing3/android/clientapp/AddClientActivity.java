package fr.esipe.ing3.android.clientapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddClientActivity extends AppCompatActivity {

    public static final String ADD_CLIENT_ACTION = "add_client";
    Button datebutton;
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText emailEditText;
    Spinner spinnerNiveau;
    SeekBar sb;
    TextView sb_value;
    RadioGroup radioGroup;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;
    int selectedId;
    String actifState;
    private Calendar calendar = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final String TAG = "AddClientActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclientactivity);

        lastNameEditText =  (EditText) findViewById(R.id.lastName_editText);
        firstNameEditText = (EditText) findViewById(R.id.firstName_editText);
        emailEditText = (EditText) findViewById(R.id.email_editText);

/*
        sb= (SeekBar) findViewById(R.id.seekBar);
        sb_value= (TextView) findViewById(R.id.sb_value);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sb_value.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
*/
        datebutton = (Button) findViewById(R.id.datebutton);

        spinnerNiveau = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.listeNiveau,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerNiveau.setAdapter(adapter);


        final Switch yesNoSwitch = (Switch) findViewById(R.id.switch1);
        yesNoSwitch.setTextOn("Oui");
        yesNoSwitch.setTextOff("Non");
        yesNoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                actifState = isChecked ? yesNoSwitch.getTextOn().toString() : yesNoSwitch.getTextOff().toString();
            }
        });

        maleRadioButton = (RadioButton) findViewById(R.id.radioButton2);
        femaleRadioButton = (RadioButton) findViewById(R.id.radioButton);
      /*  radioGroup = (RadioGroup) findViewById(R.id.radioGroupSex);
        selectedId = radioGroup.getCheckedRadioButtonId(); */

        calendar = Calendar.getInstance();


    }

    public void onAddButtonClick(View view) {



        String lastname = lastNameEditText.getText().toString();
        Client c = new Client();
        c.setLastname(lastname);
        Log.d(TAG,"test log ->"+c.getLastname());
        Client.getClients().add(c);
       // Client.setClient(c);
        sendBroadcast(new Intent(ADD_CLIENT_ACTION));
        finish();

       //ListClientFragment.clients.add(c);
/*
        String nom = lastNameEditText.getText().toString();
        String prenom = firstNameEditText.getText().toString();
        String age = sb_value.getText().toString();
        String email = emailEditText.getText().toString();
        String niveau = (String) spinnerNiveau.getSelectedItem();

        Log.d(TAG,"nom : [" + nom +"]");
        Log.d(TAG,"prenom : [" + prenom+"]");
        Log.d(TAG,"age : [" + age+"]");
        Log.d(TAG,"email : [" + email+"]");
        Log.d(TAG,"niveau :  ["+niveau+"]");
        Log.d(TAG,"actif : [" +actifState+"]");

        if(maleRadioButton.isChecked()) {
            Log.d(TAG,"sexe : [" + maleRadioButton.getText().toString()+"]"); }
        else if(femaleRadioButton.isChecked()) {
            Log.d(TAG, "sexe : [" + femaleRadioButton.getText().toString()+"]");
        }*/
    }

    public void onDateButtonClick(View view) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        String date = dateFormat.format(calendar.getTime());
                        datebutton.setText(date);
                    }
                }, calendar.get(calendar.YEAR),calendar.get(calendar.YEAR),calendar.get(calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    }




