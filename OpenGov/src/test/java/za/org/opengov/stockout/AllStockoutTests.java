package za.org.opengov.stockout;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import za.org.opengov.stockout.service.FacilityServiceTest;
import za.org.opengov.stockout.service.StockoutReportServiceTest;
import za.org.opengov.stockout.service.StockoutServiceTest;
import za.org.opengov.stockout.service.medical.DiseaseServiceTest;
import za.org.opengov.stockout.service.medical.ProductServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ FacilityServiceTest.class, StockoutReportServiceTest.class,
		StockoutServiceTest.class, DiseaseServiceTest.class, ProductServiceTest.class })
public class AllStockoutTests {

}
