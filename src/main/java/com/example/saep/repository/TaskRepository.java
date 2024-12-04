package com.example.saep.repository;


import com.example.saep.database.TaskEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void save(TaskEntity task) {
        entityManager.persist(task);
    }

    @Transactional
    public void update(TaskEntity task) {
        entityManager.merge(task);
    }

    @Transactional(readOnly = true)
    public Optional<TaskEntity> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(TaskEntity.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<TaskEntity> findByEmail(String email) {
        TypedQuery<TaskEntity> query = entityManager.createQuery(
                "SELECT u FROM TaskEntity u WHERE u.email = :email", TaskEntity.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst();
    }

    @Transactional(readOnly = true)
    public List<TaskEntity> getAll() {
        TypedQuery<TaskEntity> query = entityManager.createQuery(
                "SELECT u FROM TaskEntity u", TaskEntity.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteById(Integer id) {
        TaskEntity task = entityManager.find(TaskEntity.class, id);
        if (task != null) {
            entityManager.remove(task);
        }
    }


}

