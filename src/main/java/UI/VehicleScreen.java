package UI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abcvanstock.R;

import java.util.List;

import Components.Vehicle;
import Database.Repository;

public class VehicleScreen extends AppCompatActivity {

 @Override
 protected void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Your Vehicles");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        findViewById(R.id.newPartButton).setVisibility(View.GONE);
        findViewById(R.id.newPartButton).setEnabled(false);
        findViewById(R.id.viewStock).setVisibility(View.GONE);
        Repository repository = new Repository(getApplication());
        List<Vehicle> allVehicles = repository.getAllVehicles();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final VehicleAdapter vehicleAdapter = new VehicleAdapter(this, repository);
        vehicleAdapter.setAllVehicles(allVehicles);
        recyclerView.setAdapter(vehicleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
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
