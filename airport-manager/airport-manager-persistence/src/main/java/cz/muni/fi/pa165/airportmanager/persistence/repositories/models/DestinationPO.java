package cz.muni.fi.pa165.airportmanager.persistence.repositories.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * DestinationPO defined by city and country
 * plus a unique airport code
 * @author kotrc
 * Created on 25.10.2018
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"airportCode"})
@Entity
@Table(name="Destination")
public class DestinationPO {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true, updatable = false)
    private String airportCode;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String country;

}
