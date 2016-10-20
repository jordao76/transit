package transit.domain

/**
 * Information whether bikes are allowed.
 */
enum BikesAllowed {
  /**
   * Indicates that there is no bike information.
   */
  UNKNOWN,

  /**
   * Indicates that at least one bicycle is allowed.
   */
  AT_LEAST_ONE,

  /**
   * Indicates that no bicycles are allowed.
   */
  NONE
}
