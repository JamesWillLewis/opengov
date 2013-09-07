package za.org.opengov.stockout.entity.medical;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="SOWS_PHARM_PRODUCT")
public class Product {
	
	private String uid;
	private Medicine medicine;
	private Supplier supplier;
	private Dosage dosage;
	private int volume;
	private String name;
	private double priceExclVAT;
	private double priceInclVAT;
	private Long numberInStock;
	private Date shelfLifeDateTimePeriod;
	
	public Product() {
	}

	@Id
	@Column(name = "UID", unique = true, nullable = false)
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@ManyToOne
	@JoinColumn(name="FK_MEDICINE")
	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	@ManyToOne
	@JoinColumn(name="FK_SUPPLIER")
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@ManyToOne
	@JoinColumn(name="FK_DOSAGE")
	public Dosage getDosage() {
		return dosage;
	}

	public void setDosage(Dosage dosage) {
		this.dosage = dosage;
	}

	@Column(name="VOLUME")
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="PRICE_EXCL_VAT")
	public double getPriceExclVAT() {
		return priceExclVAT;
	}

	public void setPriceExclVAT(double priceExclVAT) {
		this.priceExclVAT = priceExclVAT;
	}

	@Column(name="PRICE_INCL_VAT")
	public double getPriceInclVAT() {
		return priceInclVAT;
	}

	public void setPriceInclVAT(double priceInclVAT) {
		this.priceInclVAT = priceInclVAT;
	}

	@Column(name="NUMBER_IN_STOCK")
	public Long getNumberInStock() {
		return numberInStock;
	}

	public void setNumberInStock(Long numberInStock) {
		this.numberInStock = numberInStock;
	}

	@Column(name="SHELF_LIFE_TIME_PERIOD")
	public Date getShelfLifeDateTimePeriod() {
		return shelfLifeDateTimePeriod;
	}

	public void setShelfLifeDateTimePeriod(Date shelfLifeDateTimePeriod) {
		this.shelfLifeDateTimePeriod = shelfLifeDateTimePeriod;
	}
	
	
}
