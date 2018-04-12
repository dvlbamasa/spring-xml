import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import static org.hibernate.Criteria.DISTINCT_ROOT_ENTITY;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import java.util.List;
import java.io.Serializable;
import java.util.stream.Collectors;

public class Dao {
	
	public Dao() {}
	
	
    public static <T> void create(T object) {
    	Session session = HibernateSession.getSession();
        session.save(object);
    }

  	public static Object getById(long id, String object) {
  		Session session = HibernateSession.getSession();
  		Object resultObject = session.load(object + ".class", id);
        return resultObject;
	}
  	

  	public static <T> void update(T object) {
  		Session session = HibernateSession.getSession();
	  	session.update(object);
  	}

  	public static <T> void delete(long id, String object) {
  		Session session = HibernateSession.getSession();
        Object resultObject = session.load(object + ".class", id);
        if (resultObject != null) {
            session.delete(resultObject);
        }
  	}

  	public static List getList(String object) {
  		Session session = HibernateSession.getSession();
  		List results = null;
  		try {
  			Criteria criteria = session.createCriteria(object);
  			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			results = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return results;
  	}

  	public static List getOrderedList(String object, String order) {
  		Session session = HibernateSession.getSession();
  		List results = null;
  		try {
  			Criteria criteria = session.createCriteria(object);
  			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
  			criteria.addOrder(Order.asc(order));
			results = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return results;
  	}
}