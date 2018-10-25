package com.buutcamp.dao;

import com.buutcamp.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StudentDAO {

    //Make hibernate and spring recognize this class as a repository--

    //get a sessionFactory reference, to access data
    @Autowired
    private SessionFactory sessionFactory;

    //get all data in the database
    @Transactional
    public List<Student> getStudents() {

        Session session = sessionFactory.getCurrentSession();

        Query<Student> query = session.createQuery("from Student", Student.class);

        return query.getResultList();
    }

    @Transactional
    public void saveStudent(Student student) {

        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    @Transactional
    public void deleteStudent(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from Student where id=:studentId");

        query.setParameter("studentId", id);

        query.executeUpdate();
    }
}
