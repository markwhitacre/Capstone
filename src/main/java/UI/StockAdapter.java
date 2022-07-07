package UI;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abcvanstock.R;

import java.util.List;

import Components.Part;
import Components.VanStock;
import Database.Repository;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockViewHolder> {

    private List<VanStock> allVanStock;
    private List<Part> allParts;
    private final Context context;
    private final LayoutInflater stockInflater;
    private int unitID;
    int quantity;
    private Repository updateRepository;


    class StockViewHolder extends RecyclerView.ViewHolder {
        TextView partNumberTextView;
        TextView partDescriptionTextView;
        Button increaseButton;
        Button decreaseButton;
        TextView quantityTextView;
        Button update;
        int quantity;

        private StockViewHolder(View stockView) {
            super(stockView);
            partNumberTextView = stockView.findViewById(R.id.partNumberTextView);
            partDescriptionTextView = stockView.findViewById(R.id.partDescriptionTextView);
            increaseButton = stockView.findViewById(R.id.increaseButton);
            decreaseButton = stockView.findViewById(R.id.decreaseButton);
            quantityTextView = stockView.findViewById(R.id.quantityTextView);
            update = stockView.findViewById(R.id.updateButton);

        }
    }



    public StockAdapter(Context context, Repository repository) {
        stockInflater = LayoutInflater.from(context);
        this.context = context;
        unitID = repository.getCurrentVehicle();
        updateRepository = repository;

    }


    @NonNull
    @Override
    public StockAdapter.StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stockView = stockInflater.inflate(R.layout.update_stock_layout, parent, false);
        return new StockAdapter.StockViewHolder(stockView);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.StockViewHolder holder, int position) {
        VanStock current = allVanStock.get(position);
        String description;
        for (Part p:allParts
             ) {
            if (p.getPartNumber() == current.getPartNumber()){
               description = p.getDescription();
               holder.partDescriptionTextView.setText(description);
               break;
            }
            else{
                description = "Missing Description";
                holder.partDescriptionTextView.setText(description);
            }
        }
        holder.partNumberTextView.setText(String.valueOf(current.getPartNumber()));
        holder.quantityTextView.setText(String.valueOf(current.getQuantity()));
        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(holder.quantityTextView.getText().toString());
                quantity++;
                holder.quantityTextView.setText(String.valueOf(quantity));


            }
        });

        holder.decreaseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(Integer.parseInt(holder.quantityTextView.getText().toString()) > 0){
                    quantity = Integer.parseInt(holder.quantityTextView.getText().toString());
                    quantity--;
                    holder.quantityTextView.setText(String.valueOf(quantity));
                }
            }

        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(String.valueOf(holder.quantityTextView.getText()));
                if(quantity <= 0){
                    allVanStock.remove(current);
                    updateRepository.delete(current);
                    notifyItemRemoved(holder.getAdapterPosition());

                }
                else{
                    current.setQuantity(quantity);
                    updateRepository.update(current);
                    notifyItemChanged(holder.getAdapterPosition());


                }

            }
        });

    }

    public void setAllParts(List<Part> parts) {
        allParts = parts;
    }


    public void setAllVanStock(List<VanStock> stock) {
        allVanStock = stock;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
            return allVanStock.size();
    }



}

