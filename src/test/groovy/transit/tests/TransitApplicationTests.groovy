package transit.tests

import static org.hamcrest.Matchers.*
import static org.springframework.http.MediaType.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*

import org.junit.*
import org.junit.runner.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.test.context.*
import org.springframework.test.context.junit4.*
import org.springframework.test.web.servlet.*
import org.springframework.web.context.*


@RunWith(SpringRunner)
@SpringBootTest
class TransitApplicationTests {

  @Autowired
  private WebApplicationContext wac
  private MockMvc client

  @Before
  void setup() {
    client = webAppContextSetup(wac).build()
  }

  @Test
  void postAgency() {
    client
      .perform(post("/agencies")
        .contentType(APPLICATION_JSON)
        .content('''{
                      "id": "FunBus",
                      "name": "The Fun Bus",
                      "url": "http://www.thefunbus.org",
                      "timezone": "America/Los_Angeles",
                      "phone": "(310) 555-0222",
                      "lang": "en"
                    }'''))
      .andExpect(status().isCreated())
  }

  @Test
  void postCalendar() {
    client
      .perform(post("/calendars")
        .contentType(APPLICATION_JSON)
        .content('''{
                      "id": "WE",
                      "monday": false,
                      "tuesday": false,
                      "wednesday": false,
                      "thursday": false,
                      "friday": false,
                      "saturday": true,
                      "sunday": true,
                      "startDate": "2006-07-01",
                      "endDate": "2006-07-31"
                    }'''))
      .andExpect(status().isCreated())
  }

}
