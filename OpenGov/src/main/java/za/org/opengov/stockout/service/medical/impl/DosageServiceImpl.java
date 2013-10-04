package za.org.opengov.stockout.service.medical.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.org.opengov.common.service.impl.AbstractServiceImpl;
import za.org.opengov.stockout.dao.medical.DosageDao;
import za.org.opengov.stockout.entity.medical.Dosage;
import za.org.opengov.stockout.service.medical.DosageService;

@Service("dosageService")
@Transactional
public class DosageServiceImpl extends AbstractServiceImpl<DosageDao, Dosage, Long> implements DosageService {

	@Autowired
	public DosageServiceImpl(DosageDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

}
