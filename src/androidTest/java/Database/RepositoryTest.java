package Database;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import Components.Part;
import Components.Product;
import Components.VanStock;
import Components.Vehicle;
import DAO.PartDAO;
import DAO.ProductDAO;
import DAO.VanStockDAO;
import DAO.VehicleDAO;

@RunWith(AndroidJUnit4.class)
public class RepositoryTest {

    DatabaseBuilder db;
    PartDAO partDAO;
    ProductDAO productDAO;
    VehicleDAO vehicleDAO;
    VanStockDAO vanStockDAO;
    Repository repository;

    private Part part = new Part(0, "2020-01-01");;
    private Product product = new Product(0, "test", "2020-01-01");
    private Vehicle vehicle = new Vehicle(0,"ABC123", 2020,"Ford,", "Focus", "Blue", "2020-01-01");;
    private VanStock vanStock = new VanStock(0,1,6,"2020-01-01");;

    @Before
    public void setUp() throws Exception {
        db = DatabaseBuilder.getInstance(ApplicationProvider.getApplicationContext());
        partDAO = db.partDAO();
        productDAO = db.productDAO();
        vehicleDAO = db.vehicleDAO();
        vanStockDAO = db.vanStockDAO();
        repository = new Repository(ApplicationProvider.getApplicationContext());
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void testParts(){
        repository.deleteAllParts();
        part.setDescription("test");
        part.setCost(1.0);
        part.setAssociatedProduct(1);
        part.setLastUpdated(ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));
        repository.insert(part);
        Assert.assertEquals("test", repository.getAllParts().get(0).getDescription());
        part = repository.getAllParts().get(0);
        part.setDescription("test2");
        repository.update(part);
        Assert.assertEquals("test2", repository.getAllParts().get(0).getDescription());
        repository.delete(part);
        Assert.assertEquals(0, repository.getAllParts().size());
    }

    @Test
    public void testProducts(){
        repository.deleteAllProducts();
        repository.insert(product);
        Assert.assertEquals("test", repository.getAllProducts().get(0).getProductName());
        product = repository.getAllProducts().get(0);
        product.setProductName("test2");
        repository.update(product);
        Assert.assertEquals("test2", repository.getAllProducts().get(0).getProductName());
        repository.delete(product);
        Assert.assertEquals(0, repository.getAllProducts().size());
    }

    @Test
    public void testVehicles(){
        repository.deleteAllVehicles();
        repository.insert(vehicle);
        Assert.assertEquals("ABC123", repository.getAllVehicles().get(0).getVIN());
        vehicle = repository.getAllVehicles().get(0);
        vehicle.setVIN("ABC124");
        repository.update(vehicle);
        Assert.assertEquals("ABC124", repository.getAllVehicles().get(0).getVIN());
        repository.delete(vehicle);
        Assert.assertEquals(0, repository.getAllVehicles().size());
    }

    @Test
    public void testVanStock(){
        repository.deleteAllVanStock();
        repository.insert(vanStock);
        Assert.assertEquals(6, repository.getAllVanStock().get(0).getQuantity());
        vanStock.setQuantity(7);
        repository.update(vanStock);
        Assert.assertEquals(7, repository.getAllVanStock().get(0).getQuantity());
        repository.delete(vanStock);
        Assert.assertEquals(0, repository.getAllVanStock().size());
    }
}