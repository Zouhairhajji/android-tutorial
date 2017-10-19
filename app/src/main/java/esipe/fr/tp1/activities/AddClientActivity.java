package esipe.fr.tp1.activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import esipe.fr.tp1.R;
import esipe.fr.tp1.beans.Client;
import esipe.fr.tp1.fragements.ListClientActivity;

public class AddClientActivity extends AppCompatActivity {

    private Button btnSubmitForm;
    private Button btnChooseBirthDay;
    private EditText tvFirstName;
    private EditText tvLastName;
    private Spinner spSexe;

    private static final String TAG = "AddClientActivity"; // generer avec logt

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Calendar calendar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvFirstName = (EditText) findViewById(R.id.etFirstNom);
        this.tvLastName = (EditText) findViewById(R.id.etLastPrenom);
        this.btnSubmitForm = (Button) findViewById(R.id.submitForm);
        this.btnChooseBirthDay = (Button) findViewById(R.id.btnChooseBirthDay);
        this.spSexe = (Spinner) findViewById(R.id.spSexe);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spSexe.setAdapter(adapter);

        calendar = Calendar.getInstance();
    }


    public void onValiderSubmit(View view) {
        String log = String.format("User %s %s , sexe %s",
                this.tvFirstName.getText().toString(),
                this.tvLastName.getText().toString(),
                this.spSexe.getSelectedItem().toString());
        Log.d(TAG, "onValiderSubmit: " + log);

        Client client = new Client();
        client.setFirstName(this.tvFirstName.getText().toString());
        client.setLastName(this.tvLastName.getText().toString());
        client.setSexe(this.spSexe.getSelectedItem().toString().trim().toLowerCase().equalsIgnoreCase("masculin"));
        client.setBidrthday(calendar.getTime());
        ListClientActivity.clients.add(client);
        finish();
        Toast.makeText(getApplicationContext(), "le compte a ete bien ajouté dans la base de données", Toast.LENGTH_SHORT).show();
    }

    public void onBirthDayClick(View view) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(year, month, day);
                btnChooseBirthDay.setText(dateFormat.format(calendar.getTime()));
            }
        }, year, month, day).show();
    }
}
