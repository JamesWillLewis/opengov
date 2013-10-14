package za.org.opengov.stockout.dao.medical.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.springframework.stereotype.Repository;

import za.org.opengov.common.dao.impl.AbstractDaoImpl;
import za.org.opengov.stockout.dao.medical.MedicineClassDao;
import za.org.opengov.stockout.entity.medical.MedicineClass;

@Repository("medicineClassDao")
public class MedicineClassDaoImpl extends
		AbstractDaoImpl<MedicineClass, String> implements MedicineClassDao {

	protected MedicineClassDaoImpl() {
		super(MedicineClass.class);
	}

	@Override
	public List<MedicineClass> fetchAllEager() {
		
		String query = "select distinct c from MedicineClass c left join fetch c.medicines m left join fetch m.products";
		
		
		return getCurrentSession().createQuery(query).list();
	}

}
