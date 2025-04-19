import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ManufactureDAO {
    public boolean add(Manufacture p) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(p);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public Manufacture get(String id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Manufacture.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Manufacture> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacture> query = cb.createQuery(Manufacture.class);
            Root<Manufacture> root = query.from(Manufacture.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean remove(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            if (manufacture != null) {
                session.delete(manufacture);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(Manufacture p) {
        if (p == null) {
            return false;
        }

        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(p);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Manufacture p) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(p);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkAllManufacturesHaveOver100Employees() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Manufacture> root = query.from(Manufacture.class);

            query.select(cb.count(root));
            query.where(cb.le(root.get("employee"), 100));

            Long count = session.createQuery(query).getSingleResult();
            return count != null && count == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getSumOfAllEmployees() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
            Root<Manufacture> root = query.from(Manufacture.class);

            query.select(cb.sum(root.get("employee")));

            Integer sum = session.createQuery(query).getSingleResult();
            return sum != null ? sum : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Manufacture getLastUSManufacturer() throws NoSuchElementException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacture> query = cb.createQuery(Manufacture.class);
            Root<Manufacture> root = query.from(Manufacture.class);

            query.select(root);
            query.where(cb.equal(root.get("location"), "US"));
            query.orderBy(cb.desc(root.get("id")));

            Manufacture result = session.createQuery(query)
                    .setMaxResults(1)
                    .uniqueResult();

            if (result == null) {
                throw new NoSuchElementException("No US-based manufacturer found");
            }

            return result;
        } catch (NoSuchElementException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoSuchElementException("Error retrieving US-based manufacturer: " + e.getMessage());
        }
    }
}