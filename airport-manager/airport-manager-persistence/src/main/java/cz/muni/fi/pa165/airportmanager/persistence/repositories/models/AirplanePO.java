package cz.muni.fi.pa165.airportmanager.persistence.repositories.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Wither;
import org.hibernate.annotations.DynamicUpdate;

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
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Entity
@Table(name="Airplanes")
@DynamicUpdate
public class AirplanePO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    //Possibly make enums for type and company ?
    @NotNull
    @Column(nullable = false)
    private String type;

    @NotNull
    @Column(nullable = false)
    private String company;

    private Integer capacity;
}
