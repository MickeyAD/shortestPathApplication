package gr.university.thesis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Set;

// Graph class represents a map/graph upon which stations exist and connect to each other.
@Data
@NoArgsConstructor
public class Graph {

    // Set of stations
    private Set<Station> stations;

    public Graph(Set<Station> stations) {
        this.stations = stations;
    }

    // Method attempts to find the shortest path between two stations/vertices in the map/graph.
    public void dijktraAlgorithmDistanceCost(Station start, Station end) {
        /*
         *  Lines 27 - 38 (including comments): Initialization of the algorithm (1):
         *      1) Shortest path between every station from the start station:
         *          a) if station = start, then cost = 0
         *          b) if station != start, then cost = infinite.
         *      2) Start station is visited, the rest stations are unvisited.
         */
        // Shortest path cost of each station from the start station.
        HashMap<Station, Integer> shortestPath = new HashMap<>();
        // for loop: Initializes total cost values to every station from the start.
        for (Station station : stations) {
            // the distance from start station to itself is zero...
            if (station == start) {
                shortestPath.put(start, 0);
                // ...to every other is infinite.
            } else {
                shortestPath.put(station, Integer.MAX_VALUE);
            }
        }
        // Initially, only start station is visited, the rest are unvisited.
        start.setVisited(true);

        /*
         *  Lines 48 - 59 (including comments): Initialization of the algorithm (2):
         *      1) Hashmap of stations:
         *             a) Each station (child) points to another station (parent).
         *             b) The start station (child) points to no other station (no parent).
         *      2) Assign cost values to adjacent edges of the start station.
         */
        // Mapping from station to station
        HashMap<Station, Station> successor = new HashMap<>();
        // Start station has no parent
        successor.put(start, null);
        /*
         *  For loop:
         *      1) Assigns cost values between the start station and its adjacent stations.
         *      2) Adjacent vertices (children) point to the start vertex (parent).
         */
        for (Edge edge : start.getAdjacentEdges()) {
            shortestPath.put(edge.getDestination(), edge.getDistanceCost());
            successor.put(edge.getDestination(), start);
        }

        // Loop which repeats until all stations in the graph are visited.
        while (true) {
            Station nextStation = nextClosestAndUnvisitedStation(shortestPath);
            /*
             * In case the next station is the final end destination:
             *     1) Print shortest path
             *     2) Print total cost
             */
            if (nextStation == end) {
                System.out.println("The path with the smallest distance cost between " + start.getName() + " and " + end.getName() + " is:");
                Station child = end;
                String path = end.getName();
                /*
                 *  Loop which repeats every time there is a previous station (parent) before our final destination (child).
                 *  The loop ceases to continue after it has reached the start station which has no parent.
                 */
                while (true) {
                    Station parent = successor.get(child);
                    if (parent == null) {
                        break;
                    }
                    path = parent.getName() + " -> " + path;
                    child = parent;
                }
                System.out.println(path);
                System.out.println("Total distance cost: " + distanceLength(shortestPath.get(end)) + "\n");
                /*
                 *  Checks whether the total distance cost is less than a specified amount.
                 *  If yes, then that distance may be walked.
                 */
                if (shortestPath.get(end) < 500) {
                    System.out.println("Distance can be walked.");
                    System.out.println("Total walking time cost: " + walkingTimeDuration(shortestPath.get(end)) + "\n");
                } else {
                    System.out.println("Distance above 500 meters cannot be walked." + "\n");
                }
                return;
            }
            /*
             *  In case the next station does not exist and has not reached the destination,
             *  there is not any available path between the start and end stations.
             */
            if (nextStation == null) {
                System.out.println("No available path exists between " + start.getName() + " and " + end.getName());
                return;
            }
            nextStation.setVisited(true);

            // For loop: Updates shortest path costs of every non visited adjacent station from the current station.
            for (Edge edge : nextStation.getAdjacentEdges()) {
                // Skipping already visited adjacent stations.
                if (edge.getDestination().isVisited()) {
                    continue;
                }
                /*
                 *  In case the new path of the adjacent station is shorter when going through
                 *  the current station (nextStation), than its previous cost value:
                 *      1) Update shortest path of adjacent station to equal the shortest path cost
                 *         of the station we are currently in plus the cost of the edge which connects
                 *         the current station and its adjacent one.
                 *      2) Let the adjacent station be included in the path to the end destination:
                 *         adjacent station (child) follows the current station (parent).
                 */
                if (shortestPath.get(nextStation) + edge.getDistanceCost() < shortestPath.get(edge.getDestination())) {
                    shortestPath.put(edge.getDestination(), shortestPath.get(nextStation) + edge.getDistanceCost());
                    successor.put(edge.getDestination(), nextStation);
                }
            }
        }
    }

    public void dijktraAlgorithmTimeCost(Station start, Station end) {
        HashMap<Station, Integer> shortestPath = new HashMap<>();
        for (Station station : stations) {
            if (station == start) {
                shortestPath.put(start, 0);
            } else {
                shortestPath.put(station, Integer.MAX_VALUE);
            }
        }
        start.setVisited(true);

        HashMap<Station, Station> successor = new HashMap<>();
        successor.put(start, null);
        for (Edge edge : start.getAdjacentEdges()) {
            shortestPath.put(edge.getDestination(), edge.getTimeCost());
            successor.put(edge.getDestination(), start);
        }

        while (true) {
            Station nextStation = nextClosestAndUnvisitedStation(shortestPath);
            if (nextStation == end) {
                System.out.println("The path with the smallest time cost between " + start.getName() + " and " + end.getName() + " is:");
                Station child = end;
                String path = end.getName();
                while (true) {
                    Station parent = successor.get(child);
                    if (parent == null) {
                        break;
                    }
                    path = parent.getName() + " -> " + path;
                    child = parent;
                }
                System.out.println(path);
                System.out.println("Total time cost: " + timeDuration(shortestPath.get(end)) + "\n");
                return;
            }
            if (nextStation == null) {
                System.out.println("No available path exists between " + start.getName() + " and " + end.getName());
                return;
            }
            nextStation.setVisited(true);

            for (Edge edge : nextStation.getAdjacentEdges()) {
                if (edge.getDestination().isVisited()) {
                    continue;
                }
                if (shortestPath.get(nextStation) + edge.getTimeCost() < shortestPath.get(edge.getDestination())) {
                    shortestPath.put(edge.getDestination(), shortestPath.get(nextStation) + edge.getTimeCost());
                    successor.put(edge.getDestination(), nextStation);
                }
            }
        }
    }

    public Station nextClosestAndUnvisitedStation(HashMap<Station, Integer> shortestPath)  {
        Station nextStation = null;
        double shortestCost = Integer.MAX_VALUE;
        //for loop: Calculating the next unvisited & closest station.
        for (Station station : stations) {
            double currentCost = shortestPath.get(station);
            //Skipping already visited stations or ones with unknown shortest path (infinite).
            if (station.isVisited() || currentCost == Integer.MAX_VALUE) {
                continue;
            }
            /*
             * if the shortest path of a station is known, then it is updated (from infinite) and
             * so the next station is found.
             */
            if (currentCost < shortestCost) {
                shortestCost = currentCost;
                nextStation = station;
            }
        }
        return nextStation;
    }

      // Convenient methods for our graph.
//    public void addStation(Station station) {
//        stations.add(station);
//    }
//
//    public void addEdge(Station source, Station destination, int distanceCost) {
//        stations.add(source);
//        stations.add(destination);
//        for (Edge e : source.getAdjacentEdges()) {
//            if (e.getSource() == source && e.getDestination() == destination) {
//                e.setDistanceCost(distanceCost);
//                return;
//            }
//        }
//        source.getAdjacentEdges().add(new Edge(source, destination, distanceCost));
//    }
//
//    public void addEdge(Station source, Station destination, int timeCost) {
//        stations.add(source);
//        stations.add(destination);
//        for (Edge e : source.getAdjacentEdges()) {
//            if (e.getSource() == source && e.getDestination() == destination) {
//                e.setTimeCost(timeCost);
//                return;
//            }
//        }
//        source.getAdjacentEdges().add(new Edge(source, destination, timeCost));
//    }

    // Method used to reset visited vertices to unvisited, in case we want to run the algorithm multiple times.
    public void resetVerticesVisited() {
        for (Station v : stations) {
            v.setVisited(false);
        }
    }

    // Method used to convert the total distance length of the shortest path into an appropriate output format.
    public String distanceLength(int totalDistanceCostInMeters) {
        int kilometers = totalDistanceCostInMeters / 1000;
        int meters = totalDistanceCostInMeters % 1000;
        return kilometers + " Kilometers " + meters + " Meters ";
    }

    // Method used to convert the total time duration of the shortest path into an appropriate output format.
    public String timeDuration(int totalTimeCostInSeconds) {
        int hours = totalTimeCostInSeconds / 3600;
        int minutes = (totalTimeCostInSeconds % 3600) / 60;
        int seconds = totalTimeCostInSeconds % 60;
        return String.format("%02d Hours %02d Minutes %02d Seconds", hours, minutes, seconds);
    }

    /*
     *  Method used to calculate the time taken to walk a distance given
     *  that distance value and the average walking speed.
     */
    public String walkingTimeDuration(int totalDistanceCostInMeters) {
        double walkingSpeed = 1.4; // speed as of m/s
        double time = totalDistanceCostInMeters / walkingSpeed;
        int timeInt = (int)time;
        String result = timeDuration(timeInt);
        return result;
    }
}
