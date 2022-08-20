package EscolaCRUD.Repositories;

import EscolaCRUD.Entities.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,UUID> {

    boolean existsById(UUID id);

    boolean existsByCpf(String cpf);

    boolean existsByNome(String nome);

}
