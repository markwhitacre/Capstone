package UI;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abcvanstock.R;

import java.util.List;

import Components.Part;

public class ReportsAdapter extends RecyclerView.Adapter<ReportsAdapter.ReportsViewHolder> {

    class ReportsViewHolder extends RecyclerView.ViewHolder{
        private final TextView description;
        private final TextView lastUpdated;
        private ReportsViewHolder(View reportsView){
            super(reportsView);
            description = reportsView.findViewById(R.id.textViewOne);
            lastUpdated = reportsView.findViewById(R.id.textViewSecondary);
        }
    }

    private List<Part> allReports;
    private final Context context;
    private final LayoutInflater productInflater;



    public ReportsAdapter(Context context){
        productInflater=LayoutInflater.from(context);
        this.context=context;
    }



    @NonNull
    @Override
    public ReportsAdapter.ReportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=productInflater.inflate(R.layout.view_select_layout,parent,false);

        return new ReportsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsAdapter.ReportsViewHolder holder, int position) {
        Part current = allReports.get(position);
        holder.description.setText(String.valueOf(current.getDescription()));
        holder.lastUpdated.setText(current.getLastUpdated());
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

    }

    public void setAllParts(List<Part> parts){
        allReports=parts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allReports.size();
    }


}
