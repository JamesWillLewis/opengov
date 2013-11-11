package za.org.opengov.stockout.web.admin.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import za.org.opengov.stockout.entity.medical.Product;

/**Wraps data for/from the Products table for client side display*/
public class ProductWrapper {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	private long medicineUid;
	
	@NotNull @Min(0) @Max(1000)
	private int volume;
	
	@NotNull
	private double price;
	
	
	public ProductWrapper() {
	}

	public ProductWrapper(Product product) {
		
		this.name = product.getName();
		this.description = product.getDescription();
		if(product.getMedicine()!=null){
			this.medicineUid = product.getMedicine().getUid();
		} else {
			this.medicineUid = 0;
		}
		this.volume = product.getVolume();
		this.price = product.getPriceInclVAT();
	}

	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public int getVolume() {
		return volume;
	}


	public double getPrice() {
		return price;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	public long getMedicineUid() {
		return medicineUid;
	}


	public void setMedicineUid(long medicineUid) {
		this.medicineUid = medicineUid;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}


	public void setPrice(double price) {
		this.price = price;
	}



}
