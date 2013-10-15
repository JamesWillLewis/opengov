/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
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

import za.org.opengov.common.util.StringMatchable;


@Entity
@Table(name="SOWS_PHARM_PRODUCT")
public class Product {
	
	/**
	 * Product code (UID)
	 */
	private String uid;
	/**
	 * The actual medicine which constitutes this particular brand. 
	 * A product name would be Panado, and the medicine is Paracetamol.
	 */
	private Medicine medicine;
	/**
	 *	The company which produces the product. (i.e Adcock Ingram)
	 */
	private Supplier supplier;
	/**
	 * Dosage mass/form of the product.
	 */
	private Dosage dosage;
	/**
	 * Quantity of dosages for this product. 
	 */
	private int volume;
	/**
	 * Product/brand name of the product. (i.e. Panado)
	 */
	private String name;
	/**
	 * Descript suffix of the product name (i.e. 200ML INJ)
	 */
	private String description;
	/**
	 * Price in ZAR exclusive of tax
	 */
	private double priceExclVAT;
	/**
	 * Price in ZAR inclusive of tax
	 */
	private double priceInclVAT;
	/**
	 * Shelf life of the product (specified as a time period, not the actual expiry date)
	 */
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

	@Column(name="SHELF_LIFE_TIME_PERIOD")
	public Date getShelfLifeDateTimePeriod() {
		return shelfLifeDateTimePeriod;
	}

	public void setShelfLifeDateTimePeriod(Date shelfLifeDateTimePeriod) {
		this.shelfLifeDateTimePeriod = shelfLifeDateTimePeriod;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
