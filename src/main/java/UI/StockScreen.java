package UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abcvanstock.R;

import java.util.ArrayList;
import java.util.List;

import Components.Part;
import Components.VanStock;
import Database.Repository;

public class StockScreen extends AppCompatActivity {

    int unitNumber;
    private List<VanStock> allStock;
    private List<VanStock> mVanStock;
    StockAdapter stockAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Current Stock");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        findViewById(R.id.newPartButton).setVisibility(View.GONE);
        findViewById(R.id.viewStock).setVisibility(View.GONE);
        Repository repository = new Repository(getApplication());
        unitNumber = repository.getCurrentVehicle();
        System.out.println("Unit Number: " + unitNumber);
        stockAdapter = new StockAdapter(this, repository);
        List<Part> partList = repository.getAllParts();
        allStock = repository.getAllVanStock();
        ArrayList<VanStock> vanStockArrayList = new ArrayList<>();
        for (VanStock v : allStock
        ) {
            if (v.getAssociatedVehicle() == unitNumber) {
                vanStockArrayList.add(v);
            }
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        if (!vanStockArrayList.isEmpty()) {
            stockAdapter.setAllVanStock(vanStockArrayList);
            stockAdapter.setAllParts(partList);
            recyclerView.setAdapter(stockAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else {
            Toast.makeText(this, "Stock is Empty", Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(this, PartScreen.class);
            startActivity(intent1);

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