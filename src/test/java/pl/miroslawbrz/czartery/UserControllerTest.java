package pl.miroslawbrz.czartery;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pl.miroslawbrz.czartery.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void ifCreateUserRequestContainsWrongEmailShouldReturnHttpCode400AndErrorMsg() throws Exception{
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"userName\": \"Miroslaw\",\n" +
                        "\t\"userLastName\": \"Brzezinski\",\n" +
                        "\t\"userMail\": \"abc\",\n" +
                        "\t\"userPassword\": \"zxmo192jKl!\n" +
                        "}"))
                .andExpect(status().is(400))
                .andExpect(content().json(
                        "{\"errorCode\":\"ERR002\"," +
                                "\"errorMessage\":\"Podany adres e-mail jest nie poprawny\"," +
                                "\"responseStatus\":\"ERROR\"}"
                ))
                .andDo(MockMvcResultHandlers.print());

    }

}
