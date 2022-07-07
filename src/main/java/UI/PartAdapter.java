package UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abcvanstock.R;

import java.util.ArrayList;
import java.util.List;

import Components.Part;

public class PartAdapter extends RecyclerView.Adapter<PartAdapter.PartViewHolder> {


    class PartViewHolder extends RecyclerView.ViewHolder{
        private final TextView partNumber;
        private final TextView description;
        private PartViewHolder(View partView){
            super(partView);
            partNumber = partView.findViewById(R.id.textViewOne);
            description = partView.findViewById(R.id.textViewSecondary);
            partListCopy = new ArrayList<>();
            partListCopy.addAll(allParts);
            itemView.setOnClickListener(v -> {
                int position=getAdapterPosition();
                Part current=allParts.get(position);
                Intent intent= new Intent(context, AddStock.class);
                intent.putExtra("description",current.getDescription());
                intent.putExtra("partNumber", current.getPartNumber());
                context.startActivity(intent);
            });
        }
    }

    private List<Part> allParts;
    private List<Part> partListCopy;
    private final Context context;
    private final LayoutInflater partInflater;

    public PartAdapter(Context context){
        partInflater=LayoutInflater.from(context);
        this.context=context;

    }

    @NonNull
    @Override
    public PartAdapter.PartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=partInflater.inflate(R.layout.view_select_layout,parent,false);

        return new PartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartAdapter.PartViewHolder holder, int position) {
        Part current = allParts.get(position);
        holder.partNumber.setText(String.valueOf(current.getPartNumber()));
        holder.description.setText(current.getDescription());
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

    }

    public void filter(String query) {;
        allParts.clear();
        if(query.isEmpty()){
            allParts.addAll(partListCopy);
        } else{
            query = query.toLowerCase();
            for(Part p: partListCopy){
                try {
                    if (p.getPartNumber() == Integer.parseInt(query)) {
                        allParts.add(p);
                    }
                }
                    catch(NumberFormatException e){
                        if(p.getDescription().toLowerCase().contains(query)){
                            allParts.add(p);
                        }
                    }
            }
        }

        notifyDataSetChanged();
    }

    public void setAllParts(List<Part> parts){
        allParts=parts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allParts.size();
    }
}


