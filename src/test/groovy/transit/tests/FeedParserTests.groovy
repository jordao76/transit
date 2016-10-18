package transit.tests

import org.junit.*
import org.springframework.core.io.*

import transit.domain.*
import transit.parser.*

class FeedParserTests {

  @Test
  void parseAgencies() {
    def csv = new ClassPathResource("sample-feed/agency.txt").getInputStream()
    def agencies = new FeedParser().parseAgencies(csv)
    for (agency in agencies) {
      assert agency.id == 'DTA'
      assert agency.name == 'Demo Transit Authority'
      assert agency.url == 'http://google.com'
      assert agency.timezone == 'America/Los_Angeles'
    }
  }

}
