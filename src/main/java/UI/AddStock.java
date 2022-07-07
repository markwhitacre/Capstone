package UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.abcvanstock.R;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import Components.VanStock;
import Database.Repository;

public class AddStock extends AppCompatActivity {

    TextView partNumberTextView;
    TextView partDescriptionTextView;
    Button increaseButton;
    Button decreaseButton;
    TextView quantityTextView;
    Button addPart;
    int quantity;
    int unitID;
    int partNumber;
    String description;
    String updated;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part_row_layout);
        quantity = 0;
        partNumberTextView = findViewById(R.id.partNumberTextView);
        partDescriptionTextView = findViewById(R.id.partDescriptionTextView);
        increaseButton = findViewById(R.id.increaseButton);
        decreaseButton = findViewById(R.id.decreaseButton);
        quantityTextView = findViewById(R.id.quantityTextView);
        addPart = findViewById(R.id.addButton);
        Repository repository = new Repository(getApplication());
        Intent intent = getIntent();
        partNumber = intent.getIntExtra("partNumber",-1);
        System.out.println(partNumber);
        description = intent.getStringExtra("description");
        System.out.println(description);
        unitID = repository.getCurrentVehicle();
        partNumberTextView.setText(String.valueOf(partNumber));
        partDescriptionTextView.setText(description);
        quantityTextView.setText(String.valueOf(quantity));
    }


    public void addPartToStock(View view) {
        Repository repository = new Repository(getApplication());
        partNumber = Integer.parseInt(String.valueOf(partNumberTextView.getText()));
        quantity = Integer.parseInt(String.valueOf(quantityTextView.getText()));
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        updated = dateTime.format(dateTimeFormatter);
        VanStock vanStock = new VanStock( partNumber, unitID, quantity, updated);
        repository.insert(vanStock);
        Intent intent = new Intent(this, PartScreen.class);
        startActivity(intent);
    }

    public void increaseQty(View view) {
        quantity = Integer.parseInt(quantityTextView.getText().toString());
        quantity++;
        quantityTextView.setText(String.valueOf(quantity));
    }

    public void decreaseQty(View view) {
        quantity = Integer.parseInt(quantityTextView.getText().toString());
        if( quantity > 0){
            quantity--;
            quantityTextView.setText(String.valueOf(quantity));
        }

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
}
