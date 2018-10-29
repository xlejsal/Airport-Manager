package cz.muni.fi.pa165.airportmanager.core.repositories.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Simple entity class modeling flight Steward,
 * attributes *name* and *surname*  and *birthDate* mustn't be null
 * and are not nullable, to have at least something set
 * in stone to identify them.
 *
 * @author Stepan Benes
 * Created on 2018-10-25
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "surname", "birthDay"})
@Entity
@Table(name="Stewards")
public class StewardPO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String surname;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    //String for now .. but who's supposed to enum 60+ genders ._.
    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String nationality;

    @ManyToMany(mappedBy = "stewards")
    private Set<Flight> flights = new HashSet<Flight>();
}
