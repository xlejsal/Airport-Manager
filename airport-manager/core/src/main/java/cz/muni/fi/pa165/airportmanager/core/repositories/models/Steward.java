package cz.muni.fi.pa165.airportmanager.core.repositories.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Simple entity class modeling flight Steward,
 * attributes *name* and *surname* mustn't be null
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
@EqualsAndHashCode
@Entity
@Table(name="Stewards")
public class Steward {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String surname;

    private int age;

    //String for now .. but who's supposed to enum 60+ genders ._.
    private String gender;

    private String nationality;

    @ManyToMany(mappedBy = "stewards")
    private Set<Flight> flights = new HashSet<Flight>();
}
