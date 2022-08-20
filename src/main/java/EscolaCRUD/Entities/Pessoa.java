package EscolaCRUD.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="Pessoa")
@Getter
@Setter
public class Pessoa implements Serializable{

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable=false, unique=true, length=11)
    private String cpf;

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private LocalDate nascimento;

}
