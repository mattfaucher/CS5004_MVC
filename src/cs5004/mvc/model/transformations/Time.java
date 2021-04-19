package cs5004.mvc.model.transformations;

import java.util.Objects;

/** Class to represent start and end time of an object's animation. */
public class Time {
  // class attributes
  private int startTime;
  private int endTime;

  /**
   * Constructs a new Time object.
   *
   * @param startTime time to start animation.
   * @param endTime time to end animation.
   * @throws IllegalArgumentException if endTime is less than startTime.
   */
  public Time(int startTime, int endTime) throws IllegalArgumentException {
    if (endTime < startTime) {
      throw new IllegalArgumentException("End cannot come before start");
    }
    if (startTime < 0) {
      throw new IllegalArgumentException("Times can't be negative");
    }
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Getter for startTime.
   *
   * @return startTime.
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Getter for endTime.
   *
   * @return endTime.
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Method to set the time to a newly given time object.
   *
   * @param time Time object to update the shapes time animation duration.
   */
  public void setTime(Time time) {
    if (time == null) {
      throw new IllegalArgumentException("Time cannot be null");
    }

    this.startTime = time.getStartTime();
    this.endTime = time.getEndTime();
  }

  /**
   * Method to check if a given tick is between the start and end time.
   *
   * @param tick time to check for inbetween.
   * @return true if between.
   */
  public boolean isBetween(int tick) {
    return (tick >= startTime && tick <= endTime);
  }

  /**
   * Method to get the total time difference.
   *
   * @return int time.
   */
  public int getDifference() {
    return this.endTime - this.startTime;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Time)) {
      return false;
    }
    Time t = (Time) o;
    return this.startTime == t.startTime && this.endTime == t.endTime;
  }

  @Override
  public int hashCode() {
    return Objects.hash(startTime, endTime);
  }

  @Override
  public String toString() {
    return String.format("Starts at: t=%d\nEnds at: t=%d", this.startTime, this.endTime);
  }
}
