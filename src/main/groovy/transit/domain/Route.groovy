package transit.domain

import javax.persistence.*


/**
 * GTFS Routes are equivalent to "Lines" in public transportation systems.
 * Routes are made up of one or more Trips — a Trip occurs at a specific time and
 * so a Route is time-independent.
 */
@Entity
class Route {
  /**
   * ID that uniquely identifies a route.
   */
  @Id String id

  /**
   * Agency for the specified route. Used to provide data for routes from more than one agency.
   */
  @ManyToOne Agency agency

  /**
   * Short name of a route. This will often be a short, abstract identifier like "32",
   * "100X", or "Green" that riders use to identify a route, but which doesn't give any
   * indication of what places the route serves.
   *
   * At least one of shortName or longName must be specified, or potentially both if appropriate.
   * If the route does not have a short name, specify a longName and use an empty string as the value for this property.
   */
  String shortName

  /**
   * Full name of a route. This name is generally more descriptive than the shortName and will
   * often include the route's destination or stop. At least one of shortName or longName must be
   * specified, or potentially both if appropriate. If the route does not have a long name,
   * specify a shortName and use an empty string as the value for this property.
   */
  String longName

  /**
   * Contains a description of a route. Meant for useful, quality information, not simply as a duplicate
   * of the name of the route. For example:
   *
   *   "'A' trains operate between Inwood-207 St, Manhattan and Far Rockaway-Mott Avenue, Queens at all times.
   *    Also from about 6AM until about midnight, additional 'A' trains operate between Inwood-207 St and Lefferts
   *    Boulevard (trains typically alternate between Lefferts Blvd and Far Rockaway)."
   */
  String desc

  /**
   * Type of transportation used on a route.
   */
  TransportationType type

  /**
   * URL of a web page about that particular route. This should be different from the agency.url.
   *
   * The value must be a fully qualified URL that includes http:// or https://, and any special
   * characters in the URL must be correctly escaped. See http://www.w3.org/Addressing/URL/4_URI_Recommentations.html
   * for a description of how to create fully qualified URL values.
   */
  String url

  /**
   * In systems that have colors assigned to routes, this defines a color that corresponds to a route.
   * The color must be provided as a six-character hexadecimal number, for example, 00FFFF. If no color
   * is specified, the default route color is white (FFFFFF).
   *
   * The color difference between color and textColor should provide sufficient contrast when viewed on
   * a black and white screen. The W3C Techniques for Accessibility Evaluation And Repair Tools document
   * offers a useful algorithm for evaluating color contrast (at http://www.w3.org/TR/AERT#color-contrast).
   * There are also helpful online tools for
   * choosing contrasting colors, including the snook.ca Color Contrast Check application
   * (at http://snook.ca/technical/colour_contrast/colour.html).
   */
  String color

  /**
   * Specifies a legible color to use for text drawn against a background of route_color.
   * The color must be provided as a six-character hexadecimal number, for example, FFD700.
   * If no color is specified, the default text color is black (000000).
   *
   * The color difference between color and textColor should provide sufficient contrast when
   * viewed on a black and white screen.
   */
  String textColor

  /**
   * Trips for the route.
   */
  @OneToMany @JoinColumn(name="route_id") List<Trip> trips
}
