package transit.tests

import java.time.*

import org.junit.*
import org.springframework.core.io.*

import transit.domain.*
import transit.parser.*

class FeedParserTests {

  // note: the sample feed doesn't exercise all fields

  @Test
  void parseSampleFeedAgency() {
    def csv = new ClassPathResource("sample-feed/agency.txt").getInputStream()
    def agencies = new FeedParser().parseAgencies(csv)
    assert agencies.size() == 1
    // DTA,Demo Transit Authority,http://google.com,America/Los_Angeles
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
    // WE,0,0,0,0,0,1,1,20070101,20101231
    def calendar = calendars[1]
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

  @Test
  void parseSampleFeedRoutes() {
    def csv = new ClassPathResource("sample-feed/routes.txt").getInputStream()
    def routes = new FeedParser().parseRoutes(csv)
    assert routes.size() == 5
    // CITY,DTA,40,City,,3,,,
    def route = routes[3]
    assert route.id == 'CITY'
    assert route.agency.id == 'DTA'
    assert route.shortName == '40'
    assert route.longName == 'City'
    assert route.type == TransportationType.BUS
  }

  @Test
  void parseSampleFeedTrips() {
    def csv = new ClassPathResource("sample-feed/trips.txt").getInputStream()
    def trips = new FeedParser().parseTrips(csv)
    assert trips.size() == 11
    // route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id
    // AB,FULLW,AB1,to Bullfrog,0,1,
    def trip = trips[0]
    assert trip.route.id == 'AB'
    assert trip.calendar.id == 'FULLW'
    assert trip.id == 'AB1'
    assert trip.headsign == 'to Bullfrog'
    assert trip.direction == TripDirection.OUTBOUND
    assert trip.blockId == '1'
    assert trip.shapeId == ''
  }

}
