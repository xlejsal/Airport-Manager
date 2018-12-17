package cz.muni.fi.pa165.airportmanager.persistence.repositories.models;

import cz.muni.fi.pa165.airportmanager.persistence.repositories.enums.Gender;
import lombok.*;
import lombok.experimental.Wither;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "surname", "birthDate"})
@Entity
@Table(name="Stewards", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "surname", "birthDate"}))
@DynamicUpdate
public class StewardPO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, updatable = false)
    private String name;

    @NotNull
    @Column(nullable = false, updatable = false)
    private String surname;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false, updatable = false)
    private LocalDate birthDate;

    //String for now .. but who's supposed to enum 60+ genders ._.
    @NotNull
    @Column(nullable = false)
    private Gender gender;

    private String nationality;

    @ManyToMany(mappedBy = "stewards", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<FlightPO> flights = new HashSet<>();
}
