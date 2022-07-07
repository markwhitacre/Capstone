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

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import Components.Part;
import Database.Repository;

public class Reports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Part Report");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        findViewById(R.id.newPartButton).setVisibility(View.GONE);
        findViewById(R.id.newPartButton).setEnabled(false);
        findViewById(R.id.viewStock).setVisibility(View.GONE);
        Repository repository = new Repository(getApplication());
        List<Part> allParts = repository.getAllParts();
        List<Part> parts = new ArrayList<>();
        for (Part part : allParts) {
            ZonedDateTime partDateTime = ZonedDateTime.parse(part.getLastUpdated(), DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG));
            if (partDateTime.isAfter(ZonedDateTime.now().minusDays(30))) {
                parts.add(part);
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final ReportsAdapter adapter = new ReportsAdapter(this);
        adapter.setAllParts(parts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
