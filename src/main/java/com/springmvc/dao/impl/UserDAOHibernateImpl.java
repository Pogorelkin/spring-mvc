package com.springmvc.repositories.impl;

import com.springmvc.entities.User;
import com.springmvc.repositories.UserRepository;
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

@Repository()
@Qualifier("jpa")
@CacheConfig(cacheNames = "users")
public class UserRepositoryHibernateImpl implements UserRepository {
    private Logger logger = LoggerFactory.getLogger(UserRepositoryHibernateImpl.class);

    @Cacheable
    @Override
    public List<User> findAll() {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Cacheable(key = "#id")
    @Override
    public User findById(long id) {
        User user = null;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.get(User.class, id);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
        return user;
    }

    @Override
    public long add(User entity) {
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

    @CacheEvict(key = "#id")
    @Override
    public long update(long id, User entity) {
        long rows = 0;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            entity.setUserId(id);
            rows = (long) session.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
        return rows;
    }

    @CacheEvict(key = "#id")
    @Override
    public long deleteById(long id) {
        long rows = 0;
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery(String.format("from User e where e.id = %d", id));
            rows = (long) query.executeUpdate();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
        return rows;
    }
}
