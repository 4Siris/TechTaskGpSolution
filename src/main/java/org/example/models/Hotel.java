package org.example.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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
@Schema(description = "Entity отеля для хранения в базе данных")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private String brand;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
    @JdbcTypeCode(SqlTypes.JSON)
    private Contacts contacts;
    @JdbcTypeCode(SqlTypes.JSON)
    private ArrivalTime arrivalTime;
    @ElementCollection
    private List<String> amenities;
}
