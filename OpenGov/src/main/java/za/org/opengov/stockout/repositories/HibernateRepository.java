package za.org.opengov.stockout.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateRepository extends HibernateDaoSupport {
	
	@Autowired
    public void autowireSessionFactory(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }

}
