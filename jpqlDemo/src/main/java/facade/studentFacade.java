package facade;

import entity.Student;
import java.util.List;
import javax.persistence.*;

public class studentFacade {

    EntityManagerFactory emf;

    public studentFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Student getPerson(int id) {
        EntityManager em = emf.createEntityManager();

        Student p = null;

        try {
            em.getTransaction().begin();
            p = em.find(Student.class, id);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Student getSpecificPerson(String firstName) {

        EntityManager em = emf.createEntityManager();

        Student p = null;

        try {
            em.getTransaction().begin();
            p = em.find(Student.class, firstName);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public List<Student> getPersons() {
        EntityManager em = emf.createEntityManager();

        List<Student> persons = null;

        try {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from Person p").getResultList();
            em.getTransaction().commit();
            return persons;
        } finally {
            em.close();
        }
    }

    public Student addPerson(Student p) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Student deletePerson(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Student p = em.find(Student.class, id);
            em.remove(p);

            em.getTransaction()
                    .commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Student editPerson(Student pers) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Student p = em.find(Student.class, pers.getId());
            if (p
                    != null) {
                p = pers;
                em.merge(p);
                em.getTransaction().commit();
                return p;
            }
        } finally {
            em.close();
        }

        return null;
    }

}
