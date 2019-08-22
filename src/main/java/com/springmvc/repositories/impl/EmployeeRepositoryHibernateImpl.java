package com.springmvc.repositories.impl;

import com.springmvc.entities.Employee;
import com.springmvc.repositories.EmployeeRepository;
import com.springmvc.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jpa")
@CacheConfig(cacheNames = "employees")
public class EmployeeRepositoryHibernateImpl implements EmployeeRepository {
    private Logger logger = LoggerFactory.getLogger(EmployeeRepositoryHibernateImpl.class);

    @Override
    @Cacheable
    public List<Employee> findAll() {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }

    @Override
    @Cacheable(key = "#id")
    public Employee findById(long id) {
        Employee employee = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            employee = session.get(Employee.class, id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
        return employee;
    }

    @Override
    public long add(Employee entity) {
        long rows = 0;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            rows = (long) session.save(entity);
            session.getTransaction().commit();
            return rows;
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
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
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
        return rows;
    }

    @Override
    @CacheEvict(key = "#id")
    public long deleteById(long id) {
        long rows = 0;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Employee e where e.id = " + id);
            rows = (long) query.executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
        return rows;
    }
}
