package fr.esipe.creteil.activities;

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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.esipe.creteil.beans.Client;
import fr.esipe.creteil.configs.RetrofitAPI;
import fr.esipe.creteil.retrofitInterfaces.ClientRestService;
import fr.esipe.ing3.android.clientapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClientActivity extends AppCompatActivity {

    public static final String EXTRA_CLIENT_ADDED = "add_client";

    private Button datebutton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private Spinner spinnerNiveau;
    private SeekBar sb;
    private TextView sb_value;
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private int selectedId;
    private String actifState;
    private Calendar calendar = null;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private ClientRestService clientRestService;
    private static final String TAG = "AddClientActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclientactivity);

        lastNameEditText =  (EditText) findViewById(R.id.lastName_editText);
        firstNameEditText = (EditText) findViewById(R.id.firstName_editText);
        emailEditText = (EditText) findViewById(R.id.email_editText);
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

        this.clientRestService = RetrofitAPI.createService(ClientRestService.class);
    }

    public void onAddButtonClick(View view) {

        Client c = new Client();
        c.setLastname(lastNameEditText.getText().toString());
        c.setFirstname(firstNameEditText.getText().toString());



        //Client.getClients().add(c);
        this.clientRestService.addClient(c).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddClientActivity.this, "Added", Toast.LENGTH_SHORT).show();
                sendBroadcast(new Intent(EXTRA_CLIENT_ADDED));
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddClientActivity.this, "Cannot connect to the server" + t, Toast.LENGTH_SHORT).show();
            }
        });


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




