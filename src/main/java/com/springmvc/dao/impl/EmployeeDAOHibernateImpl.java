package com.springmvc.dao.impl;

import com.springmvc.dao.EmployeeDAO;
import com.springmvc.entities.Employee;
import com.springmvc.exceptions.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("jpa")
@CacheConfig(cacheNames = "employees")
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
    Logger logger = LoggerFactory.getLogger(EmployeeDAOHibernateImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Cacheable
    public List<Employee> findAll() {
        Query<Employee> query;
        List<Employee> employeeList = null;
        try (Session session = sessionFactory.openSession()) {
            query = session.createQuery("FROM Employee");
            employeeList = query.getResultList();
            session.close();
        } catch (Exception e) {
            throw new DAOException("Couldn't get all Employees from DB", e.getCause());
        }
        return employeeList;
    }

    @Override
    @Cacheable(key = "#id")
    public Employee findById(long id) {
        Query<Employee> query;
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT e FROM Employee e WHERE e.id = :id";
            query = session.createQuery(sql);
            query.setParameter("id", id);
            logger.info(query.toString()    );
            return (Employee) query.uniqueResult();

        } catch (Exception ex) {
            throw new DAOException("Couldn't get Employee from DB with id = " + id, ex.getCause());
        }
    }

    @Override
    public long add(Employee entity) {
        long rows = 0;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            rows = (long) session.save(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            throw new DAOException("Couldn't add Employee to DB" + entity.toString(), e.getCause());
        }
        return rows;
    }

    @Override
    @CacheEvict(key = "#id")
    public long update(long id, Employee entity) {
        long rows = 0;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            entity.setEmployeeId(id);
            rows = (long) session.save(entity);
            session.close();
        } catch (Exception ex) {
            throw new DAOException("Couldn't update Employee" + entity.toString(), ex.getCause());
        }
        return rows;
    }

    @Override
    @CacheEvict(key = "#id")
    public long deleteById(long id) {
        long rows = 0;
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(String.format("from Employees e where e.id = %d", id));
            rows = (long) query.executeUpdate();
            session.close();
        } catch (Exception e) {
            throw new DAOException("Couldn't delete Employee with id = " + id, e.getCause());
        }
        return rows;
    }
}
