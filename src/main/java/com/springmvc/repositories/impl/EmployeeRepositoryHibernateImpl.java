package com.springmvc.repositories.impl;

import com.springmvc.entities.Employee;
import com.springmvc.repositories.EmployeeRepository;
import com.springmvc.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("jpa")
@CacheConfig(cacheNames = "employees")
public class EmployeeRepositoryHibernateImpl implements EmployeeRepository {
    private Logger logger = LoggerFactory.getLogger(EmployeeRepositoryHibernateImpl.class);

    @Override
    @Cacheable
    public List<Employee> findAll() {
        List<Employee> employeeList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employeeList = session.createQuery("from Employee", Employee.class).list();
            session.close();
            logger.info(employeeList.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return employeeList;
    }

    @Override
    @Cacheable(key = "#id")
    public Employee findById(long id) {
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employee = session.get(Employee.class, id);
            logger.info(employee.toString());
            session.close();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return employee;
    }

    @Override
    public long add(Employee entity) {
        long rows = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            rows = (long) session.save(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return rows;
    }

    @Override
    @CacheEvict(key = "#id")
    public long update(long id, Employee entity) {
        long rows = 0;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            entity.setEmployeeId(id);
            rows = (long) session.save(entity);
            session.close();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return rows;
    }

    @Override
    @CacheEvict(key = "#id")
    public long deleteById(long id) {
        long rows = 0;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(String.format("from Employee e where e.id = %d", id));
            rows = (long) query.executeUpdate();
            session.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return rows;
    }
}
