package EscolaCRUD.DataTransferObjects;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
public class PessoaDTO {
    @NotBlank
    private String cpf;

    @NotBlank
    private String nome;

    @NotBlank
    private LocalDate nascimento;

}
