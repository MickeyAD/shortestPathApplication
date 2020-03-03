package gr.university.thesis.util;

public class Formatter {

    // Method used to convert the total distance length of the shortest path into an appropriate output format.
    public static String distanceLength(int totalDistanceCostInMeters) {
        int kilometers = totalDistanceCostInMeters / 1000;
        int meters = totalDistanceCostInMeters % 1000;
        return kilometers + " Kilometers " + meters + " Meters ";
    }

    // Method used to convert the total time duration of the shortest path into an appropriate output format.
    public static String timeDuration(int totalTimeCostInSeconds) {
        int hours = totalTimeCostInSeconds / 3600;
        int minutes = (totalTimeCostInSeconds % 3600) / 60;
        int seconds = totalTimeCostInSeconds % 60;
        return String.format("%02d Hours %02d Minutes %02d Seconds", hours, minutes, seconds);
    }

    /*
     *  Method used to calculate the time taken to walk a distance given
     *  that distance value and the average walking speed.
     */
    public static String walkingTimeDuration(int totalDistanceCostInMeters) {
        double walkingSpeed = 1.4; // speed as of m/s
        double time = totalDistanceCostInMeters / walkingSpeed;
        int timeInt = (int) time;
        String result = timeDuration(timeInt);
        return result;
    }
}
