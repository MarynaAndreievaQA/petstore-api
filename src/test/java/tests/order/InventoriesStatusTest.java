package tests.order;

import endPoints.StoreEndpoint;
import models.Status;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class InventoriesStatusTest {

    @Steps
    private StoreEndpoint storeEndpoint;
    private Status status;

    @Test
    public void inventoriesStatus() {
        storeEndpoint.inventoriesStatus(status);
    }
}

