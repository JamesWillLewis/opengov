package za.org.opengov.stockout.service.medical.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.stockout.entity.medical.Medicine;
import za.org.opengov.stockout.entity.medical.MedicineClass;
import za.org.opengov.stockout.entity.medical.Product;
import za.org.opengov.stockout.service.medical.MedicineClassService;
import za.org.opengov.stockout.service.medical.MedicineService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional
public class MedicineServiceImplTest {

	@Autowired
	private MedicineService medicineService;

	@Autowired
	private MedicineClassService medicineClassService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllMedicinesForClass() {
		
		List<MedicineClass> medicineClasses = medicineClassService.getAll();
		
		for(MedicineClass medicineClass: medicineClasses){
			System.out.println(medicineClass.getUid());
			for(Medicine medicine: medicineClass.getMedicines()){
				System.out.println("\t" + medicine.getName());
				for(Product product: medicine.getProducts()){
					System.out.println("\t\t" + product.getName() + " - " + product.getDescription());
				}
			}
			
		}
	}
}
