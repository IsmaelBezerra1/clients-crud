package ismaeldev.clients.repositories;

import ismaeldev.clients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
