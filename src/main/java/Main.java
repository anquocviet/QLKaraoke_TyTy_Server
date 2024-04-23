import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import server.Server;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class Main {
   public static void main(String[] args) {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("MariaDB Karaoke");
      EntityManager em = emf.createEntityManager();
      new Server().start();
   }
}
