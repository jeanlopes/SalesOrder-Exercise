import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by I848075 on 20/08/2015.
 */
public class ReadProductTest {

    ReadProduct read;
    RegisterProduct register;
    FakeProductRepository repository;
    FakeProductReceiver receiver;

    @Before
    public void setUp() throws Exception {
        receiver = new FakeProductReceiver();
        repository = new FakeProductRepository();
        read = new ReadProduct(this.repository, this.receiver);
    }

    @Test
    public void ReadingSuccessful_CanReadOneProduct() {
        ProductInfo info = givenInfo("productName", "ProductDescription", 10.0, 10);
        register = new RegisterProduct(receiver, info, repository);
        register.execute();
        Assert.assertTrue(receiver.registrationWasSuccessful);
        ProductInfo infoRetrieved = read.getProductInfoByProductName("productName");
        assertProductsInfoAreEqual(info, infoRetrieved);
    }

    @Test
    public void ReadingSuccessful_CanReadTwoProducts() {
        ProductInfo info1 = givenInfo("productName", "ProductDescription", 10.0, 10);
        ProductInfo info2 = givenInfo("productName2", "ProductDescription2", 20.0, 20);

        register = new RegisterProduct(receiver, info1, repository);
        register.execute();
        Assert.assertTrue(receiver.registrationWasSuccessful);
        ProductInfo infoRetrieved = read.getProductInfoByProductName("productName");
        assertProductsInfoAreEqual(info1, infoRetrieved);

        register = new RegisterProduct(receiver, info2, repository);
        register.execute();
        Assert.assertTrue(receiver.registrationWasSuccessful);
        infoRetrieved = read.getProductInfoByProductName("productName2");
        assertProductsInfoAreEqual(info2, infoRetrieved);
    }

    @Test
    public void ReadingFailed_ProductDoesNotExist() {
        ProductInfo infoRetrieved = read.getProductInfoByProductName("productName");
        Assert.assertNull(infoRetrieved);
    }

    private ProductInfo givenInfo(String productName, String productDescription, double price, int unitsInStock) {
        ProductInfo info = new ProductInfo();
        info.name = productName;
        info.description = productDescription;
        info.price = price;
        info.unitsInStock = unitsInStock;
        return info;
    }

    private void assertProductsInfoAreEqual(ProductInfo info, ProductInfo infoRetrieved) {
        Assert.assertEquals(info.name, infoRetrieved.name);
        Assert.assertEquals(info.description, infoRetrieved.description);
        Assert.assertEquals(info.price, infoRetrieved.price, 0.001);
        Assert.assertEquals(info.unitsInStock, infoRetrieved.unitsInStock);
    }
}
