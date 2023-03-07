package com.example.hibernate.demo;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
    public static void main(String[] args) {

        // create session factory

        SessionFactory factory = new Configuration()
                .configure("/hibernate.cfg.xml").
                addAnnotatedClass(Instructor.class).
                addAnnotatedClass(InstructorDetail.class).
                addAnnotatedClass(Course.class).
                buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from database
            int instructorPK = 1;
            Instructor instructor = session.get(Instructor.class, instructorPK);
            System.out.println("example: Instructor retrieved: " + instructor);
            // show courses
            System.out.println("example: Courses: " + instructor.getCourses());
            // commit transaction
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }
}
