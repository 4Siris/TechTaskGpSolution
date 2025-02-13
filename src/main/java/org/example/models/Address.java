package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer houseNumber;
    private String street;
    private String city;
    private String county;
    private String postCode;

    @Override
    public String toString() {
        return houseNumber + " " + street + ", " +
                city + ", " +
                postCode + ", " +
                county;
    }
}
