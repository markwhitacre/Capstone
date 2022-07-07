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

import java.util.List;

import Components.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    class ProductViewHolder extends RecyclerView.ViewHolder{
        private final TextView productID;
        private final TextView productName;
        private ProductViewHolder(View productView){
            super(productView);
            productID = productView.findViewById(R.id.textViewOne);
            productName = itemView.findViewById(R.id.textViewSecondary);
            itemView.setOnClickListener(v -> {
                int position=getAdapterPosition();
                final Product current=allProducts.get(position);
                Intent intent= new Intent(context, PartScreen.class);
                intent.putExtra("productID", current.getProductID());
                context.startActivity(intent);
            });
        }
    }

    private List<Product> allProducts;
    private final Context context;
    private final LayoutInflater productInflater;




    public ProductAdapter(Context context){
        productInflater=LayoutInflater.from(context);
        this.context=context;

    }



    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=productInflater.inflate(R.layout.view_select_layout,parent,false);

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product current = allProducts.get(position);
        holder.productID.setText(String.valueOf(current.getProductID()));
        holder.productName.setText(current.getProductName());
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

    }

    public void setAllProducts(List<Product> products){
        allProducts=products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }


}
