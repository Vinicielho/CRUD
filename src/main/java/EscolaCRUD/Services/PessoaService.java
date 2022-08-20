package EscolaCRUD.Services;

import EscolaCRUD.Entities.Pessoa;
import EscolaCRUD.Repositories.PessoaRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {

    final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository=pessoaRepository;
    }

    @Transactional
    public Pessoa save(Pessoa pessoa) {return pessoaRepository.save(pessoa);}

    @Transactional
    public void delete(Pessoa pessoa) {
        pessoaRepository.delete(pessoa);
    }

    public boolean existsByCpf(String cpf){
        return pessoaRepository.existsByCpf(cpf);
    }

    public boolean existsByNome(String nome){
        return pessoaRepository.existsByNome(nome);
    }

    public boolean existsById(UUID id){
        return pessoaRepository.existsById(id);
    }

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(UUID id){
        return pessoaRepository.findById(id);
    }
}