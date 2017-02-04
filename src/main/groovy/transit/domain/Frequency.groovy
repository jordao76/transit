package transit.domain

import java.time.*

// https://developers.google.com/transit/gtfs/reference/frequencies-file
class Frequency {
  Trip trip
  LocalTime startTime
  LocalTime endTime
  int headwaySecs
  String exactTimes
}
