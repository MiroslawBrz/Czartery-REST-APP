package pl.miroslawbrz.czartery;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pl.miroslawbrz.czartery.api.response.UserResponse;
import pl.miroslawbrz.czartery.model.User;
import pl.miroslawbrz.czartery.repository.UserRepository;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void ifCreateUserRequestIsCorrectShouldReturnHttpCode200AndReturnUserId() throws Exception{

        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"userName\": \"Mirosław\",\n" +
                        "\t\"userLastName\": \"Brzezinski\",\n" +
                        "\t\"userMail\": \"miroslawbrzezinski@gmail.com\",\n" +
                        "\t\"userPassword\": \"zxmo192jKl!\"\n" +
                        "}"))
                .andExpect(status().is(200))
                .andExpect(content().string(Matchers.containsString("Poprawnie dodano konto")))
                .andExpect(content().string(Matchers.containsString("id")))
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        UserResponse userResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                UserResponse.class
        );
        Optional<User> optionalUser = userRepository.findById(userResponse.getId());
        Assert.assertTrue(optionalUser.isPresent());

    }

    @Test
    public void ifCreateUserRequestIsWithEmptyUserNameShouldReturnHttpCode400AndErrorMsg() throws Exception{

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"userName\": \"\",\n" +
                        "\t\"userLastName\": \"Brzezinski\",\n" +
                        "\t\"userMail\": \"miroslawbrzezinski@gmail.com\",\n" +
                        "\t\"userPassword\": \"zxmo192jKl!\"\n" +
                        "}"))
                .andExpect(status().is(400))
                .andExpect(content().json(
                        "{\"errorCode\":\"ERR001\"," +
                                "\"errorMessage\":\"Dane wymagane do realizacji \\u017C\\u0105dania s\\u0105 niekompletne.\"," +
                                "\"responseStatus\":\"ERROR\"}"
                ))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ifCreateUserRequestContainsWrongEmailShouldReturnHttpCode400AndErrorMsg() throws Exception{

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"userName\": \"Miroslaw\",\n" +
                        "\t\"userLastName\": \"Brzezinski\",\n" +
                        "\t\"userMail\": \"abc\",\n" +
                        "\t\"userPassword\": \"zxmo192jKl!\"\n" +
                        "}"))
                .andExpect(status().is(400))
                .andExpect(content().json(
                        "{\"errorCode\":\"ERR002\"," +
                                "\"errorMessage\":\"Podany adres e-mail jest nie poprawny\"," +
                                "\"responseStatus\":\"ERROR\"}"
                ))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ifCreateUserRequestContainsWrongPasswordShouldReturnHttpCode400AndErrorMsg() throws Exception{

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"userName\": \"Miroslaw\",\n" +
                        "\t\"userLastName\": \"Brzezinski\",\n" +
                        "\t\"userMail\": \"miroslawbrzezinski@gmail.com\",\n" +
                        "\t\"userPassword\": \"abc\"\n" +
                        "}"))
                .andExpect(status().is(400))
                .andExpect(content().json(
                        "{\"errorCode\":\"ERR003\"," +
                                "\"errorMessage\":\"Podane hasło powinno posiadać wielkie i małe litery, liczby oraz znaki specjalne i zawierać conajmniej 8 znaków\"," +
                                "\"responseStatus\":\"ERROR\"}"
                ))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ifCreateUserRequestContainsAlreadyRegisterEmailShouldReturnHttpCode409AndErrorMsg() throws Exception{

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"userName\": \"Miroslaw\",\n" +
                        "\t\"userLastName\": \"Brzezinski\",\n" +
                        "\t\"userMail\": \"janusz@gmail.com\",\n" +
                        "\t\"userPassword\": \"zxmo192jKl!\"\n" +
                        "}"))
                .andExpect(status().is(409))
                .andExpect(content().json(
                        "{\"errorCode\":\"ERR004\"," +
                                "\"errorMessage\":\"Użytkownik o podanym adresie znajduje się już w bazie\"," +
                                "\"responseStatus\":\"ERROR\"}"
                ))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ifGetAllUsersShouldReturnUsersArrayAndHTTPCode200() throws Exception{
        mockMvc.perform(get("/api/users"))
                .andExpect(status().is(200))
                .andExpect(content().json(

                        "[" +
                                "{" +
                                "\"userId\":1," +
                                "\"userName\":\"Adam\"," +
                                "\"userLastName\":\"Kowalski\"," +
                                "\"activationHash\":987," +
                                "\"userMail\":\"adamkowalski@buziaczek.com\"," +
                                "\"userPassword\":\"krjsHgr89824vjh#bt58\"," +
                                "\"userActive\":true," +
                                "\"userRole\":null" +
                                "}," +
                                "" +
                                "{" +
                                "\"userId\":2," +
                                "\"userName\":\"Janusz\"," +
                                "\"userLastName\":\"Nosacz\"," +
                                "\"activationHash\":789," +
                                "\"userMail\":\"janusz@gmail.com\"," +
                                "\"userPassword\":\"iuuh#sD9361/di8v\"," +
                                "\"userActive\":false," +
                                "\"userRole\":null" +
                                "}," +
                                "" +
                                "{" +
                                "\"userId\":3," +
                                "\"userName\":\"Jan\"," +
                                "\"userLastName\":\"Nowak\"," +
                                "\"activationHash\":456," +
                                "\"userMail\":\"nowak@wp.pl\"," +
                                "\"userPassword\":\"sdvs8F10vb!dbrd\"," +
                                "\"userActive\":true," +
                                "\"userRole\":null" +
                                "}" +
                                "]"

                ))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ifDeleteUserByIdShouldReturnHttpCode200AndResponseMessage() throws Exception {
        mockMvc.perform(delete("/api/users/2"))
                .andExpect(status().is(200))
                .andExpect(content().json(
                        "{\"responseMessage\":\"Usunięto użytkownika\"," +
                                "\"responseStatus\":\"SUCCESS\"," +
                                "\"id\": 2}"
                ))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void ifUpdateUserShouldReturnHttpCode200AndUserResponse() throws Exception{
        mockMvc.perform(post("/api/users/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"userName\": \"Miroslaw\",\n" +
                        "\t\"userLastName\": \"Brzezinski\",\n" +
                        "\t\"userMail\": \"janusz@gmail.com\",\n" +
                        "\t\"userPassword\": \"zxmo192jKl!\"\n" +
                        "}"))
                .andExpect(status().is(200));
    }
}
