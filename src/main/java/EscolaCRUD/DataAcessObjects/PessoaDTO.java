package EscolaCRUD.DataAcessObjects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaDTO {
    @NotBlank
    private String cpf;

    @NotBlank
    private String nome;

    @NotBlank
    private LocalDate nascimento;

}
