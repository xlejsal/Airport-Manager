package cz.muni.fi.pa165.airportmanager.core.repositories.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Simple entity class modeling real life Airplane,
 * each is given a unique non-nullable *name*, its *type*
 * and *company* name are also required to not be null.
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
@Table(name="Airplanes")
public class AirplanePO {
    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    //Possibly make enums for type and company ?
    @EqualsAndHashCode.Exclude
    @NotNull
    @Column(nullable = false)
    private String type;

    @EqualsAndHashCode.Exclude
    @NotNull
    @Column(nullable = false)
    private String company;

    @EqualsAndHashCode.Exclude
    private int capacity;
}
