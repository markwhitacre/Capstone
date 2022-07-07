package UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.abcvanstock.R;

import java.util.List;

import Components.Part;
import Database.Repository;

public class OptionsScreen extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        Repository repository = new Repository(getApplication());
        List<Part> allParts = repository.getAllParts();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_screen);
    }

    public void goToVehicles(View view) {
        Intent intent = new Intent(this, VehicleScreen.class);
        startActivity(intent);
    }

    public void getReport(View view) {
        Intent intent = new Intent(this, Reports.class);
        startActivity(intent);
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
