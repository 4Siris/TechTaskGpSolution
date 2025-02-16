package org.example.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Объект для хранения информации о прибытия/отбытия в Json")
public class ArrivalTime {
    private String checkIn;
    private String checkOut;
}
