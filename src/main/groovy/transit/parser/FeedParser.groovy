package transit.parser

import transit.domain.*

import com.fasterxml.jackson.dataformat.csv.*

class FeedParser {

  List<Agency> parseAgencies(InputStream is) {
    parseCsv(is) {
      new Agency(it.collectEntries { [(it.key - 'agency_'): it.value] })
    }
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
