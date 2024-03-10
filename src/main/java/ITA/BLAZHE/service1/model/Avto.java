package ITA.BLAZHE.service1.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "termin")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Avto {
    @Id
    private String id;
    private String znamka;
    private String model;
    private LocalDateTime letoIzdelave;
    private String tipGorivo;
    private String registracija;
    private LocalDateTime prvaRegistracija;
    private LocalDateTime registracijaDo;

}

