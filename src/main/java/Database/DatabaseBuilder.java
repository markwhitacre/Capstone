package Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.Executors;

import Components.Part;
import Components.Product;
import Components.VanStock;
import Components.Vehicle;
import DAO.PartDAO;
import DAO.ProductDAO;
import DAO.VanStockDAO;
import DAO.VehicleDAO;

@Database(entities = {Part.class, Product.class,Vehicle.class, VanStock.class}, version = 1,exportSchema = false)

public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract PartDAO partDAO();
    public abstract ProductDAO productDAO();
    public abstract VehicleDAO vehicleDAO();
    public abstract VanStockDAO vanStockDAO();

public static DatabaseBuilder INSTANCE;

    private String getDateTimeString(){
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        return dateTime.format(dateTimeFormatter);
    }

public synchronized static DatabaseBuilder getInstance(Context context){
    if (INSTANCE == null) {
        INSTANCE = buildDatabase(context);

    }
    return INSTANCE;
}

private static DatabaseBuilder buildDatabase(final Context context) {
    ZonedDateTime dateTime = ZonedDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
    String updated = dateTime.format(dateTimeFormatter);
    Vehicle vehicle = new Vehicle(0,"1234",2022, "Ford", "F-150", "1/2 Ton Truck", updated );
    Product product = new Product(0,"Switchgear", updated);
    Part part = new Part(0,updated);
    part.setDescription("Screw");


    return Room.databaseBuilder(context,
            DatabaseBuilder.class,
            "VanStockDatabase")
            .addCallback(new Callback() {
        @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Executors.newSingleThreadScheduledExecutor().execute(() -> {

                getInstance(context).partDAO().insert(part);
                part.setAssociatedProduct(1);
                part.setDescription("Screw");
                getInstance(context).productDAO().insert(product);
                getInstance(context).vehicleDAO().insert(vehicle);
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    })
            .build();
}

}


