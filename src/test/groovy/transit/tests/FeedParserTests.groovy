package transit.tests

import java.time.*

import org.junit.*
import org.springframework.core.io.*

import transit.domain.*
import transit.parser.*

class FeedParserTests {

  @Test
  void parseSampleFeedAgency() {
    def csv = new ClassPathResource("sample-feed/agency.txt").getInputStream()
    def agencies = new FeedParser().parseAgencies(csv)
    assert agencies.size() == 1
    def agency = agencies[0]
    assert agency.id == 'DTA'
    assert agency.name == 'Demo Transit Authority'
    assert agency.url == 'http://google.com'
    assert agency.timezone == 'America/Los_Angeles'
  }

  @Test
  void parseSampleFeedCalendars() {
    def csv = new ClassPathResource("sample-feed/calendar.txt").getInputStream()
    def calendars = new FeedParser().parseCalendars(csv)
    assert calendars.size() == 2
    // FULLW,1,1,1,1,1,1,1,20070101,20101231
    def calendar = calendars[0]
    assert calendar.id == 'FULLW'
    assert calendar.monday
    assert calendar.tuesday
    assert calendar.wednesday
    assert calendar.thursday
    assert calendar.friday
    assert calendar.saturday
    assert calendar.sunday
    assert calendar.startDate == LocalDate.of(2007,1,1)
    assert calendar.endDate == LocalDate.of(2010,12,31)
    // WE,0,0,0,0,0,1,1,20070101,20101231
    calendar = calendars[1]
    assert calendar.id == 'WE'
    assert !calendar.monday
    assert !calendar.tuesday
    assert !calendar.wednesday
    assert !calendar.thursday
    assert !calendar.friday
    assert calendar.saturday
    assert calendar.sunday
    assert calendar.startDate == LocalDate.of(2007,1,1)
    assert calendar.endDate == LocalDate.of(2010,12,31)
  }

}
