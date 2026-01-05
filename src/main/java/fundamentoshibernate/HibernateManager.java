package fundamentoshibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateManager {

    private SessionFactory sessionFactory;
    
    public HibernateManager() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    private void save(Movie movie) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(movie);
        session.getTransaction().commit();
        session.close();
    }
    
    private void read(int movieId) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Movie movie = (Movie) session.load(Movie.class, movieId);
        session.getTransaction().commit();
        System.out.println("Movie: " + movie.getTitle());
        session.close();
    }
    
    private void readAll() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        List<Movie> movies = session.createQuery("from Movie").list();
        session.getTransaction().commit();
        movies.forEach(movie -> System.out.println(movie.getTitle()));
        session.close();
    }
    
    private void delete(int movieId) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Movie movie = new Movie();
        movie.setId(movieId);
        session.delete(movie);
        session.getTransaction().commit();
        session.close();
    }
    
    private void update(Movie m) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Movie movie = (Movie) session.load(Movie.class, m.getId());
        movie.setSynopsis("");
        session.update(movie);
        session.getTransaction().commit();
        session.close();
    }
    
    public static void main(String[] args) {
        HibernateManager hm = new HibernateManager();
        Movie movie = new Movie();
        movie.setTitle("");
        movie.setDirector("");
        movie.setSynopsis("");
        hm.save(movie);
        hm.read(1);
        Movie m = new Movie();
        m.setId(1);
        hm.update(m);
        hm.readAll();
        hm.delete(1);
    }

}
