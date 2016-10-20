package transit.domain

import javax.persistence.*

/**
 * A Trip represents a journey taken by a vehicle through Stops.
 * Trips are time-specific — they are defined as a sequence of StopTimes,
 * so a single Trip represents one journey along a transit line or route.
 * In addition to StopTimes, Trips use Calendars to define the days when a
 * Trip is available to passengers.
 */
@Entity
class Trip {
  /**
   * Identifies a trip.
   */
  @Id String id

  /**
   * Identifies a route
   */
  @ManyToOne Route route

  /**
   * Identifies a set of dates when service is available for one or more
   * routes and trips.
   */
  @ManyToOne Calendar calendar

  /**
   * Text that appears on a sign that identifies the trip's destination to passengers.
   * Distinguishes between different patterns of service in the same route.
   * If the headsign changes during a trip, you can override it by specifying values for
   * the the headsign property in StopTimes.
   */
  String headsign

  /**
   * Text that appears in schedules and sign boards to identify the trip to passengers,
   * for example, to identify train numbers for commuter rail trips. If riders do not
   * commonly rely on trip names, this property should be blank.
   *
   * A value, if provided, should uniquely identify a trip within a service day;
   * it should not be used for destination names or limited/express designations.
   */
  String shortName

  /**
   * Indicates the direction of travel for a trip. Distinguishes between bi-directional
   * trips with the same Route. This property is not used in routing; it provides a way
   * to separate trips by direction when publishing time tables.
   * You can specify names for each direction with the headsign property.
   */
  TripDirection direction

  /**
   * Identifies the block to which the trip belongs. A block consists of two or more sequential
   * trips made using the same vehicle, where a passenger can transfer from one trip to the
   * next just by staying in the vehicle. The blockId must be referenced by two or more Trips.
   */
  String blockId

  /**
   * Defines a shape for the trip. Shapes define how a line should be drawn on the map to represent a trip.
   */
  String shapeId // TODO: relationship to Shape

  /**
   * Wheelchair accessibility information.
   */
  WheelchairAccessible wheelchairAccessible

  /**
   * Information whether bikes are allowed.
   */
  BikesAllowed bikesAllowed
}
