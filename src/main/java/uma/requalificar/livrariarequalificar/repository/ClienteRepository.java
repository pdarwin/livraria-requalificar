package uma.requalificar.livrariarequalificar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uma.requalificar.livrariarequalificar.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>
{

}
