package UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.abcvanstock.R;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import Components.Part;
import Database.Repository;

public class NewPart extends AppCompatActivity {

    TextView partNumberTextView;
    TextView descriptionTextView;
    TextView costTextView;
    TextView productTextView;
    TextView vehicleTextView;
    TextView quantityTextView;
    EditText partNumberEditText;
    EditText descriptionEditText;
    EditText costEditText;
    EditText productEditText;
    EditText vehicleEditText;
    EditText quantityEditText;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_edit_layout);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Parts");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        partNumberTextView = findViewById(R.id.textViewOne);
        descriptionTextView = findViewById(R.id.textViewTwo);
        costTextView = findViewById(R.id.textViewThree);
        productTextView = findViewById(R.id.textViewFour);
        vehicleTextView = findViewById(R.id.textViewFive);
        vehicleTextView.setVisibility(View.GONE);
        quantityTextView = findViewById(R.id.textViewSix);
        quantityTextView.setVisibility(View.GONE);
        partNumberEditText = findViewById(R.id.editTextOne);
        partNumberEditText.setText("0");
        partNumberEditText.setFocusable(false);
        partNumberEditText.setEnabled(false);
        descriptionEditText = findViewById(R.id.editTextTwo);
        costEditText = findViewById(R.id.editTextThree);
        productEditText = findViewById(R.id.editTextFour);
        vehicleEditText = findViewById(R.id.editTextFive);
        vehicleEditText.setVisibility(View.GONE);
        quantityEditText = findViewById(R.id.editTextSix);
        quantityEditText.setVisibility(View.GONE);
        partNumberTextView.setText("Part Number");
        descriptionTextView.setText("Description of Part");
        costTextView.setText("Cost in USD");
        productTextView.setText("Associated Product ID");

    }

    public boolean checkForDescription(){
        if(TextUtils.isEmpty(descriptionEditText.getText().toString())){
            Toast.makeText(this, "Please enter a description", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveNewPart(View view) {
        Repository repository = new Repository(getApplication());
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        if (checkForDescription()) {
            Part part = new Part(Integer.parseInt(String.valueOf(partNumberEditText.getText())), dateTime.format(dateTimeFormatter));
            part.setDescription(String.valueOf(descriptionEditText.getText()));

            if (!TextUtils.isEmpty(String.valueOf(costEditText.getText()))) {
                part.setCost(Double.parseDouble(String.valueOf(costEditText.getText())));
            }
            if (!TextUtils.isEmpty(String.valueOf(productEditText.getText()))) {
                part.setAssociatedProduct(Integer.parseInt(String.valueOf(productEditText.getText())));
            }
            part.setLastUpdated(dateTime.format(dateTimeFormatter));

            repository.insert(part);

            Intent intent = new Intent(this, PartScreen.class);
            startActivity(intent);

        }
    }
}
