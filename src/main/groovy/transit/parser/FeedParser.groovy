package transit.parser

import java.time.*
import java.time.format.*

import transit.domain.*

import com.fasterxml.jackson.dataformat.csv.*

class FeedParser {

  private dateFormat = DateTimeFormatter.ofPattern('yyyyMMdd')

  List<Agency> parseAgencies(InputStream is) {
    parseCsv(is) {
      new Agency(it.collectEntries {
        [(snakeToCamelCase(it.key - ~/^agency_/)): it.value]
      })
    }
  }

  List<Agency> parseCalendars(InputStream is) {
    parseCsv(is) {
      new Calendar(it.collectEntries {
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
    parseCsv(is) {
      new Route(it.collectEntries {
        if (it.key == 'agency_id')
          [agency: new Agency(id: it.value)]
        else
          [(snakeToCamelCase(it.key - ~/^route_/)): it.value]
      })
    }
  }

  private String snakeToCamelCase(String snakeCaseStr) {
    snakeCaseStr.replaceAll(/_\w/) { it[1].toUpperCase() }
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
