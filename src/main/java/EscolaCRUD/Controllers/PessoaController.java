package EscolaCRUD.Controllers;

import EscolaCRUD.DataTransferObjects.PessoaDTO;
import EscolaCRUD.Entities.Pessoa;
import EscolaCRUD.Services.PessoaService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {

        this.pessoaService = pessoaService;

    }

    @PostMapping("/savePessoa")
    public ResponseEntity<Object> savePessoa(@RequestBody @Valid PessoaDTO pessoaDto){

        if(pessoaService.existsByCpf(pessoaDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("conflito: cpf em uso!");
        }

        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        pessoa.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "id") UUID id){
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if (pessoaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        pessoaService.delete(pessoaOptional.get());//".get()"?
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada.");
    }

    @GetMapping("/getAllPessoas")
    public ResponseEntity<List<Pessoa>> getAllPessoas(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePessoa(@PathVariable(value="id") UUID id){
        Optional<Pessoa> pessoaOptional=pessoaService.findById(id);
        return pessoaOptional.<ResponseEntity<Object>>map(pessoa -> ResponseEntity.status(HttpStatus.OK).body(pessoa)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePessoa(@PathVariable(value = "id") UUID id, @RequestBody @Valid PessoaDTO pessoaDto){
        Optional<Pessoa> pessoaOptional = pessoaService.findById(id);
        if (pessoaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        pessoa.setId(pessoaOptional.get().getId());
        pessoa.setRegistrationDate(pessoaOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.save(pessoa));
    }
}