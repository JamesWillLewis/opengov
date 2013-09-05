package za.org.opengov.stockout.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import za.org.opengov.stockout.domain.entity.medical.Product;


@Entity
@Table(name="SOWS_STOCKOUT_REPORT")
public class StockoutReport {

	private Long uid;
	private Product product;
	private Facility facility;
	private Subject reporter;
	private Subject reportee;
	private String cause;
	private boolean reportedToDOH;
	private boolean resolved;
	
	public StockoutReport() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID", unique = true, nullable = false)
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@ManyToOne
	@JoinColumn(name="FK_PRODUCT")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne
	@JoinColumn(name="FK_FACILITY")
	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@ManyToOne
	@JoinColumn(name="FK_REPORTER")
	public Subject getReporter() {
		return reporter;
	}

	public void setReporter(Subject reporter) {
		this.reporter = reporter;
	}

	@ManyToOne
	@JoinColumn(name="FK_REPORTEE")
	public Subject getReportee() {
		return reportee;
	}

	public void setReportee(Subject reportee) {
		this.reportee = reportee;
	}

	@Column(name="CAUSE")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Column(name="REPORTED_TO_DOH")
	public boolean getReportedToDOH() {
		return reportedToDOH;
	}

	public void setReportedToDOH(boolean reportedToDOH) {
		this.reportedToDOH = reportedToDOH;
	}

	@Column(name="IS_RESOLVED")
	public boolean getResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	
	
}
