package route.swing.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Administrator
 */
public class Haversine {

    private static final int R = 6371;

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = toRad(lat2 - lat1);
        double lonDistance = toRad(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(toRad(lat1)) * Math.cos(toRad(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private static double toRad(double value) {
        return value * Math.PI / 180;
    }

    public static double round(double value) {
        return Math.round(value);
    }

    public static String calcTime(double distanceKm, double speedKmh) {
        double travelTimeHours = (distanceKm + 10) / speedKmh;
        int travelTimeHoursInt = (int) travelTimeHours;

        double remainingMinutes = (travelTimeHours - travelTimeHoursInt) * 60;
        int travelTimeMinutesInt = (int) remainingMinutes;

        double remainingSeconds = (remainingMinutes - travelTimeMinutesInt) * 60;
        int travelTimeSecondsInt = (int) remainingSeconds;

        return travelTimeHoursInt + " h, " + travelTimeMinutesInt + " m, " + travelTimeSecondsInt + " s";
    }
}
