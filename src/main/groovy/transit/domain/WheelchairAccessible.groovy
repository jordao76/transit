package transit.domain

/**
 * Wheelchair accessibility information.
 */
enum WheelchairAccessible {
  /**
   * Indicates that there is no accessibility information.
   */
  UNKNOWN,

  /**
   * Indicates that at least one rider in a wheelchair can be accommodated.
   */
  AT_LEAST_ONE,

  /**
   * Indicates that no riders in wheelchairs can be accommodated.
   */
  NONE
}
