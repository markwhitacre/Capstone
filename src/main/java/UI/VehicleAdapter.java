package UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abcvanstock.R;

import java.util.List;

import Components.Vehicle;
import Database.Repository;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    private List<Vehicle> allVehicles;
    private final Context context;
    private final LayoutInflater vehicleInflater;
    private Repository repository;

    class VehicleViewHolder extends RecyclerView.ViewHolder{
        private final TextView unitNumber;
        private final TextView bodyStyle;
        private VehicleViewHolder(View vehicleView){
            super(vehicleView);
            unitNumber = vehicleView.findViewById(R.id.textViewOne);
            bodyStyle = vehicleView.findViewById(R.id.textViewSecondary);
            vehicleView.setOnClickListener(v ->{
                    int position = getAdapterPosition();
                  Vehicle vehicle = allVehicles.get(position);
                Intent intent = new Intent(context, ProductScreen.class);
                repository.setCurrentVehicle(vehicle.getUnitNumber());
                context.startActivity(intent);
            });
        }
    }





    public VehicleAdapter(Context context, Repository repository){
        vehicleInflater=LayoutInflater.from(context);
        this.context=context;
        this.repository = repository;
    }

    @NonNull
    @Override
    public VehicleAdapter.VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vehicleView=vehicleInflater.inflate(R.layout.view_select_layout,parent,false);

        return new VehicleViewHolder(vehicleView);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.VehicleViewHolder holder, int position) {
        Vehicle current = allVehicles.get(position);
        holder.unitNumber.setText(String.valueOf(current.getUnitNumber()));
        holder.bodyStyle.setText(current.getBodyStyle());
    }

    public void setAllVehicles(List<Vehicle> vehicles){
        allVehicles=vehicles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allVehicles.size();
    }
}
