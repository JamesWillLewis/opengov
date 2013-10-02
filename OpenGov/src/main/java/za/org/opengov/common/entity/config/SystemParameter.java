package za.org.opengov.common.entity.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OPENGOV_PARAMS")
public class SystemParameter {
	
	private String parameter;
	private String value;
	
	public SystemParameter() {
	}

	@Id
	@Column(name = "PARAM", unique = true, nullable = false)
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Column(name="VALUE")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
