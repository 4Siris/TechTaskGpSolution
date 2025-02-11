package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String brand;
    @JdbcTypeCode(SqlTypes.JSON)
    private Address address;
    @JdbcTypeCode(SqlTypes.JSON)
    private Contacts contacts;
    @JdbcTypeCode(SqlTypes.JSON)
    private ArrivalTime arrivalTime;
    private List<String> amenities;
}
