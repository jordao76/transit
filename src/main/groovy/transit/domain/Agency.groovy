package transit.domain

import javax.persistence.*


/**
 * An Agency is an operator of a public transit network, often a public authority.
 * Agencies can have URLs, phone numbers, and language indicators.
 */
@Entity
class Agency {
  /**
   * Uniquely identifies a transit agency.
   */
  @Id String id

  /**
   * Contains the full name of the transit agency.
   */
  String name

  /**
   * Contains the URL of the transit agency. The value must be a fully qualified URL that
   * includes http:// or https://, and any special characters in the URL must be correctly
   * escaped. See http://www.w3.org/Addressing/URL/4_URI_Recommentations.html for a
   * description of how to create fully qualified URL values.
   */
  String url

  /**
   * Contains the timezone where the transit agency is located. Timezone names never contain
   * the space character but may contain an underscore. Please refer to
   * http://en.wikipedia.org/wiki/List_of_tz_zones for a list of valid values.
   */
  String timezone

  /**
   * Contains a two-letter ISO 639-1 code for the primary language used by this transit agency.
   * The language code is case-insensitive (both en and EN are accepted). This setting defines
   * capitalization rules and other language-specific settings for all text contained in this
   * transit agency's feed. Please refer to http://www.loc.gov/standards/iso639-2/php/code_list.php for
   * a list of valid values.
   */
  String lang

  /**
   * Contains a single voice telephone number for the specified agency. This property is a string
   * value that presents the telephone number as typical for the agency's service area.
   * It can and should contain punctuation marks to group the digits of the number.
   * Dialable text (for example, TriMet's "503-238-RIDE") is permitted, but the field must not
   * contain any other descriptive text.
   */
  String phone

  /**
   * Specifies the URL of a web page that allows a rider to purchase tickets or other fare instruments
   * for that agency online. The value must be a fully qualified URL that includes http:// or https://,
   * and any special characters in the URL must be correctly escaped. See
   * http://www.w3.org/Addressing/URL/4_URI_Recommentations.html for a description of how to create
   * fully qualified URL values.
   */
  String fareUrl

  /**
   * Contains a single valid email address actively monitored by the agency's customer service department.
   * This email address will be considered a direct contact point where transit riders can reach a customer
   * service representative at the agency.
   */
  String email

  /**
   * Routes for the agency.
   */
  @OneToMany @JoinColumn(name="agency_id") List<Route> routes
}
