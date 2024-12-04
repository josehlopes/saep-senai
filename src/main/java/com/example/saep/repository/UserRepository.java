package com.example.saep.repository;

import com.example.saep.database.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void save(UserEntity user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(UserEntity user) {
        entityManager.merge(user);
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Integer id) {
        String query = "SELECT u FROM UserEntity u WHERE u.id = :id";
        List<UserEntity> result = entityManager.createQuery(query, UserEntity.class)
                .setParameter("id", id)
                .getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> findByEmail(String email) {
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst();
    }

    @Transactional(readOnly = true)
    public List<UserEntity> getAll() {
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u", UserEntity.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteById(Integer id) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
