package facade;

import entity.Studypoint;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class studypointsFacade {
    
    EntityManagerFactory emf;
    
    public studypointsFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    
    public Studypoint getPerson(int id)
    {
        EntityManager em = emf.createEntityManager();

        Studypoint p = null;
        
        try
        {
            em.getTransaction().begin();
            p = em.find(Studypoint.class, id);
            em.getTransaction().commit();
            return p;
        }
        finally
        {
            em.close();
        }    
    }
    
    public List<Studypoint> getPersons()
    {
        EntityManager em = emf.createEntityManager();

        List<Studypoint> persons = null;
        
        try
        {
            em.getTransaction().begin();
            persons = em.createQuery("Select p from Person p").getResultList();
            em.getTransaction().commit();
            return persons;
        }
        finally
        {
            em.close();
        }
    }

    public Studypoint addPerson(Studypoint p)
    {
        EntityManager em = emf.createEntityManager();
       
        try
        {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        }
        finally
        {
            em.close();
        }
    }
    
    public Studypoint deletePerson(int id)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            Studypoint p = em.find(Studypoint.class, id);
            em.remove(p);
            em.getTransaction().commit();
            return p;
        }
        finally
        {
            em.close();
        }
    }
    
    public Studypoint editPerson(Studypoint pers)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            Studypoint p = em.find(Studypoint.class, pers.getId());
            if(p != null)
            {
                p = pers;
                em.merge(p);
                em.getTransaction().commit();
                return p;
            }
        }
        finally
        {
            em.close();
        }  
        
        return null;
    }
    

    
}
