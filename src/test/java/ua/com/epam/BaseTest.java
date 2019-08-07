package ua.com.epam;

import org.testng.annotations.AfterSuite;
import ua.com.epam.core.client.mysql.MySQLClient;
import ua.com.epam.core.client.rest.RestClient;
import ua.com.epam.service.CleanUpService;
import ua.com.epam.service.ServiceFactory;
import ua.com.epam.utils.DataFactory;
import ua.com.epam.utils.ValidatorFactory;

public class BaseTest {
    private RestClient client = new RestClient();
    protected DataFactory testData = new DataFactory();
    protected ValidatorFactory validateThat = new ValidatorFactory(client);
    protected ServiceFactory services = new ServiceFactory(client);
    protected CleanUpService clean = new CleanUpService(client, services);

    @AfterSuite(alwaysRun = true)
    public void closeDBConnection() {
        MySQLClient.closeConnection();
    }
}
