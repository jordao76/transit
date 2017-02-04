package transit.parser

import java.time.*
import java.time.format.*

import transit.domain.*

import com.fasterxml.jackson.dataformat.csv.*

class FeedParser {

  private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern('yyyyMMdd')
  private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern('H:m:s')

  List<Agency> parseAgencies(InputStream is) {
    parseCsv(is) { Map<String, String> map ->
      new Agency(map.collectEntries {
        [(snakeToCamelCase(it.key - ~/^agency_/)): it.value]
      })
    }
  }

  List<Calendar> parseCalendars(InputStream is) {
    parseCsv(is) { Map<String, String> map ->
      new Calendar(map.collectEntries {
        if (it.key == 'service_id')
          [id: it.value]
        else if (it.key in ['start_date', 'end_date'])
          [(snakeToCamelCase(it.key)): LocalDate.parse(it.value, dateFormat)]
        else
          [(it.key): it.value == '1']
      })
    }
  }

  List<Route> parseRoutes(InputStream is) {
    parseCsv(is) { Map<String, String> map ->
      new Route(map.collectEntries {
        if (it.key == 'agency_id')
          [agency: new Agency(id: it.value)]
        else if (it.key == 'route_type')
          [type: TransportationType.ofId(Integer.parseInt(it.value))]
        else
          [(snakeToCamelCase(it.key - ~/^route_/)): it.value]
      })
    }
  }

  List<Trip> parseTrips(InputStream is) {
    parseCsv(is) { Map<String, String> map ->
      new Trip(map.collectEntries {
        if (it.key == 'route_id')
          [route: new Route(id: it.value)]
        else if (it.key == 'service_id')
          [calendar: new Calendar(id: it.value)]
        else if (it.key == 'direction_id')
          [direction: it.value == '1' ? TripDirection.INBOUND : TripDirection.OUTBOUND]
        else
          [(snakeToCamelCase(it.key - ~/^trip_/)): it.value]
      })
    }
  }

  List<Frequency> parseFrequencies(InputStream is) {
    parseCsv(is) { Map<String, String> map ->
      new Frequency(map.collectEntries {
        if (it.key == 'trip_id')
          [trip: new Trip(id: it.value)]
        else if (it.key in ['start_time', 'end_time'])
          [(snakeToCamelCase(it.key)): LocalTime.parse(it.value, timeFormat)]
        else if (it.key == 'headway_secs')
          [headwaySecs: Integer.parseInt(it.value)]
        else
          [(snakeToCamelCase(it.key)): it.value]
      })
    }
  }

  private String snakeToCamelCase(String snakeCaseStr) {
    snakeCaseStr.replaceAll(/_\w/) { String match -> match[1].toUpperCase() }
  }

  private <T> List<T> parseCsv(InputStream is, Closure<T> transform) {
    def mapper = new CsvMapper()
    def schema = CsvSchema.emptySchema().withHeader()
    mapper
      .readerFor(Map)
      .with(schema)
      .readValues(is)
      .collect(transform)
  }

}
