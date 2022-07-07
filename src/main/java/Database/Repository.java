package Database;

import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Components.Part;
import Components.Product;
import Components.VanStock;
import Components.Vehicle;
import DAO.PartDAO;
import DAO.ProductDAO;
import DAO.VanStockDAO;
import DAO.VehicleDAO;


public class Repository{
    private PartDAO partDAO;
    private ProductDAO productDAO;
    private VehicleDAO vehicleDAO;
    private VanStockDAO vanStockDAO;
    private List<Part> allParts;
    private List<Product> allProducts;
    private List<Vehicle> allVehicles;
    private List<VanStock> allVanStock;
    private static int currentVehicle;
    private static final int NUMBER_OF_THREADS = 5;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        DatabaseBuilder db=DatabaseBuilder.getInstance(application);
        partDAO = db.partDAO();
        productDAO = db.productDAO();
        vehicleDAO = db.vehicleDAO();
        vanStockDAO = db.vanStockDAO();
    }

    public void deleteAllVehicles() {
        databaseExecutor.execute(() -> {
            vehicleDAO.deleteAllVehicles();
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentVehicle(){
        return currentVehicle;

    }

    public void setCurrentVehicle(int vehicle){
        currentVehicle = vehicle;
    }


    public List<Part> getAllParts() {
        databaseExecutor.execute(()->{
            allParts = partDAO.getAllParts();
        });

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allParts;
    }

    public List<Vehicle> getAllVehicles() {
        databaseExecutor.execute(()->{
            allVehicles = vehicleDAO.getAllVehicles();});


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allVehicles;
    }

    public List<Product> getAllProducts() {
        databaseExecutor.execute(()-> {
            allProducts = productDAO.getAllProducts();});



        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return allProducts;
    }

    public List<VanStock> getAllVanStock() {
        databaseExecutor.execute(()-> {
            allVanStock = vanStockDAO.getAllVanStock();});



        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return allVanStock;
    }

    public void insert (Part part) {
        databaseExecutor.execute(() -> {

            partDAO.insert(part);
        });

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert (Product product) {
        databaseExecutor.execute(()->{
            productDAO.insert(product);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


    }


    public void insert (Vehicle vehicle) {
        databaseExecutor.execute(()->{
            vehicleDAO.insert(vehicle);});

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void insert (VanStock vanStock) {
        databaseExecutor.execute(()->{
            vanStockDAO.insert(vanStock);});

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update (Part part) {
        databaseExecutor.execute(()->{
            partDAO.update(part);});

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void update (Product product) {
        databaseExecutor.execute(()->{
            productDAO.update(product);});

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update (Vehicle vehicle) {
        databaseExecutor.execute(()->{
            vehicleDAO.update(vehicle);});

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update (VanStock vanStock) {
        databaseExecutor.execute(()->{
            vanStockDAO.update(vanStock);});

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void delete (Part part) {
        databaseExecutor.execute(()->{

            partDAO.delete(part);});

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void delete (Product product) {
        databaseExecutor.execute(()->{

            productDAO.delete(product);});

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void delete (Vehicle vehicle) {
        databaseExecutor.execute(()->{

            vehicleDAO.delete(vehicle);});

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    public void delete (VanStock vanStock) {
        databaseExecutor.execute(()->{
            vanStockDAO.delete(vanStock);});

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void deleteAllProducts() {
        databaseExecutor.execute(()->{
            productDAO.deleteAllProducts();});
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllParts() {
        databaseExecutor.execute(()->{
            partDAO.deleteAllParts();});
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllVanStock() {
        databaseExecutor.execute(()->{
            vanStockDAO.deleteAllVanStock();});
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
