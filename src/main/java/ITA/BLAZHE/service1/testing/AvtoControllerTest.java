package ITA.BLAZHE.service1.testing;

import ITA.BLAZHE.service1.controller.AvtoController;
import ITA.BLAZHE.service1.model.Avto;
import ITA.BLAZHE.service1.service.AvtoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AvtoController.class)
public class AvtoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AvtoService avtoService;

    @Test
    public void testGetAllAvto() throws Exception {
        List<Avto> avtos = Arrays.asList(new Avto("1", "Ford", "Focus", LocalDateTime.now(), "Diesel", "ABC123", LocalDateTime.now(), LocalDateTime.now().plusYears(1)));
        when(avtoService.findAll()).thenReturn(avtos);

        mockMvc.perform(get("/avto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].znamka", is("Ford")));
    }

    @Test
    public void testGetAvtoById() throws Exception {
        Avto avto = new Avto("1", "Ford", "Focus", LocalDateTime.now(), "Diesel", "ABC123", LocalDateTime.now(), LocalDateTime.now().plusYears(1));
        when(avtoService.findById("1")).thenReturn(Optional.of(avto));

        mockMvc.perform(get("/avto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.znamka", is("Ford")));
    }

    @Test
    public void testCreateAvto() throws Exception {
        Avto avto = new Avto("1", "Ford", "Focus", LocalDateTime.now(), "Diesel", "ABC123", LocalDateTime.now(), LocalDateTime.now().plusYears(1));
        when(avtoService.save(any(Avto.class))).thenReturn(avto);

        String avtoJson = objectMapper.writeValueAsString(avto); // Convert Avto object to JSON string

        mockMvc.perform(post("/avto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(avtoJson)) // Use the JSON string for the request
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.znamka", is("Ford")));
    }

    @Test
    public void testUpdateAvto() throws Exception {
        Avto updatedAvto = new Avto("1", "Toyota", "Corolla", LocalDateTime.now(), "Petrol", "XYZ789", LocalDateTime.now(), LocalDateTime.now().plusYears(1));
        when(avtoService.update("1", updatedAvto)).thenReturn(updatedAvto);

        String avtoJson = objectMapper.writeValueAsString(updatedAvto); // Serialize the Avto object to JSON

        mockMvc.perform(put("/avto/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(avtoJson)) // Use the JSON string in the request
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.znamka", is("Toyota")));
    }


    @Test
    public void testDeleteAvto() throws Exception {
        mockMvc.perform(delete("/avto/1"))
                .andExpect(status().isOk());
    }
}