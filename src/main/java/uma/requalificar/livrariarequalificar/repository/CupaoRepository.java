package uma.requalificar.livrariarequalificar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uma.requalificar.livrariarequalificar.model.Cupao;

@Repository
public interface CupaoRepository extends CrudRepository <Cupao, Long>
{

}
