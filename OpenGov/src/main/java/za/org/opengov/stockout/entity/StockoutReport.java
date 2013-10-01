package za.org.opengov.stockout.entity;

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
@Table(name = "SOWS_STOCKOUT_REPORT")
public class StockoutReport {

	private Long uid;
	private Stockout stockout;
	private Subject reporter;
	private Subject reportee;
	private String cause;
	private boolean reportedToDOH;
	private Date timestamp;

	public StockoutReport() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UID", unique = true, nullable = false)
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	@ManyToOne
	@JoinColumn(name = "FK_STOCKOUT")
	public Stockout getStockout() {
		return stockout;
	}

	public void setStockout(Stockout stockout) {
		this.stockout = stockout;
	}

	@ManyToOne
	@JoinColumn(name = "FK_REPORTER")
	public Subject getReporter() {
		return reporter;
	}

	public void setReporter(Subject reporter) {
		this.reporter = reporter;
	}

	@ManyToOne
	@JoinColumn(name = "FK_REPORTEE")
	public Subject getReportee() {
		return reportee;
	}

	public void setReportee(Subject reportee) {
		this.reportee = reportee;
	}

	@Column(name = "CAUSE")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Column(name = "REPORTED_TO_DOH")
	public boolean getReportedToDOH() {
		return reportedToDOH;
	}

	public void setReportedToDOH(boolean reportedToDOH) {
		this.reportedToDOH = reportedToDOH;
	}

	@Column(name = "TIMESTAMP")
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
