package gm.zona_fit.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data //Con esta notacion se hacen todos los gets y sets automaticamente gracias a Lombok
@NoArgsConstructor //Agregamos el constructor vacio a nuestra clase sin necesida de definirlos nosotros mismos
@AllArgsConstructor //Agregamos contructor con tofos los argumentos para que podamos crera objetos con el nombre,id,etc.
@ToString
@EqualsAndHashCode
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer membresia;
}
