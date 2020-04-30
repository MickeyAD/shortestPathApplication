package gr.university.thesis.service;

import gr.university.thesis.dto.CalculatePathDTO;
import gr.university.thesis.dto.Graph;
import gr.university.thesis.dto.StationDetailsDTO;
import gr.university.thesis.entity.Leg;
import gr.university.thesis.entity.Station;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.LegRepository;
import gr.university.thesis.repository.StationRepository;
import gr.university.thesis.util.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private LegRepository legRepository;

    // Method attempts to find the shortest path between two stations/vertices in the map/graph.
    public void dijktraAlgorithmDistanceCost(Object start, Object end) {
        Station initialStation = stationRepository.findStationById(((Station) start).getId());
        initialStation.setAdjacentLegs(findAdjacentLegs(initialStation));

        Station finalStation = stationRepository.findStationById(((Station) end).getId());
        finalStation.setAdjacentLegs(findAdjacentLegs(finalStation));

        CalculatePathDTO calculatePathDTO = new CalculatePathDTO();

        List<Station> stationList = findAllStations();
        Set<Station> stations = new HashSet<>(stationList);
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
                shortestPath.put(initialStation, 0);
                // ...to every other is infinite.
            } else {
                shortestPath.put(station, Integer.MAX_VALUE);
            }
        }
        // Initially, only start station is visited, the rest are unvisited.
        initialStation.setVisited(true);

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
        successor.put(initialStation, null);
        /*
         *  For loop:
         *      1) Assigns cost values between the start station and its adjacent stations.
         *      2) Adjacent vertices (children) point to the start vertex (parent).
         */
        for (Leg leg : initialStation.getAdjacentLegs()) {
            shortestPath.put(leg.getDestination(), leg.getDistanceCost());
            successor.put(leg.getDestination(), initialStation);
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
                System.out.println("The path with the smallest distance cost between " + initialStation.getName() + " and " + finalStation.getName() + " is:");
                Station child = finalStation;
                String path = finalStation.getName();
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
                System.out.println("Total distance cost: " + Formatter.distanceLength(shortestPath.get(end)) + "\n");
                /*
                 *  Checks whether the total distance cost is less than a specified amount.
                 *  If yes, then that distance may be walked.
                 */
                if (shortestPath.get(end) < 500) {
                    System.out.println("Distance can be walked.");
                    System.out.println("Total walking time cost: " + Formatter.walkingTimeDuration(shortestPath.get(end)) + "\n");
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
                System.out.println("No available path exists between " + initialStation.getName() + " and " + finalStation.getName());
                return;
            }
            nextStation.setVisited(true);

            // For loop: Updates shortest path costs of every non visited adjacent station from the current station.
            for (Leg leg : nextStation.getAdjacentLegs()) {
                // Skipping already visited adjacent stations.
                if (leg.getDestination().isVisited()) {
                    continue;
                }
                /*
                 *  In case the new path of the adjacent station is shorter when going through
                 *  the current station (nextStation), than its previous cost value:
                 *      1) Update shortest path of adjacent station to equal the shortest path cost
                 *         of the station we are currently in plus the cost of the leg which connects
                 *         the current station and its adjacent one.
                 *      2) Let the adjacent station be included in the path to the end destination:
                 *         adjacent station (child) follows the current station (parent).
                 */
                if (shortestPath.get(nextStation) + leg.getDistanceCost() < shortestPath.get(leg.getDestination())) {
                    shortestPath.put(leg.getDestination(), shortestPath.get(nextStation) + leg.getDistanceCost());
                    successor.put(leg.getDestination(), nextStation);
                }
            }
        }
    }

    public Graph findShortestPathTimeCost(Station start, Station end) {

        CalculatePathDTO calculatePathDTO = new CalculatePathDTO();

        List<Station> stationList = stationRepository.findAll();
        Set<Station> stations = new HashSet<>(stationList);

        for (Station station : stationList) {
            station.setAdjacentLegs(findAdjacentLegs(station));
        }

        Station initialStation = stationRepository.findStationById(start.getId());
        Station finalStation = stationRepository.findStationById(end.getId());

        HashMap<Station, Integer> shortestPath = new HashMap<>();

        for (Station station : stations) {
            if (station == initialStation) {
                shortestPath.put(initialStation, 0);
            } else {
                shortestPath.put(station, Integer.MAX_VALUE);
            }
        }
        initialStation.setVisited(true);

        HashMap<Station, Station> successor = new HashMap<>();
        successor.put(initialStation, null);
        for (Leg leg : initialStation.getAdjacentLegs()) {
            shortestPath.put(leg.getDestination(), leg.getTimeCost());
            successor.put(leg.getDestination(), initialStation);
        }

        ArrayList<Station> pathList = new ArrayList<>();
        while (true) {
            Station nextStation = nextClosestAndUnvisitedStation(shortestPath);
            if (nextStation == finalStation) {
                Station child = finalStation;
                while (true) {
                    Station parent = successor.get(child);
                    if (parent == null) {
                        break;
                    }
                    pathList.add(parent);
                    child = parent;
                }
                System.out.println("Total time cost: " + Formatter.timeDuration(shortestPath.get(finalStation)));

                calculatePathDTO.setTimeCost(shortestPath.get(finalStation));

                Set<Station> pathListSet = new HashSet<>(pathList);
                return new Graph(pathListSet);
            }
            if (nextStation == null) {
                System.out.println("No available path exists between " + initialStation.getName() + " and " + finalStation.getName());
            }
            nextStation.setVisited(true);

            for (Leg leg : nextStation.getAdjacentLegs()) {
                if (leg.getDestination().isVisited()) {
                    continue;
                }
                if (shortestPath.get(nextStation) + leg.getTimeCost() < shortestPath.get(leg.getDestination())) {
                    shortestPath.put(leg.getDestination(), shortestPath.get(nextStation) + leg.getTimeCost());
                    successor.put(leg.getDestination(), nextStation);
                }
            }
        }
    }

    private Station nextClosestAndUnvisitedStation(HashMap<Station, Integer> shortestPath) {
        List<Station> stationList = findAllStations();
        Set<Station> stations = new HashSet<>(stationList);

        Station nextStation = null;
        int shortestCost = Integer.MAX_VALUE;
        for (Station station : stations) {
            int currentCost = shortestPath.get(station);
            //Skipping already visited stations or ones with unknown shortest path (infinite).
            if (station.isVisited() || currentCost == Integer.MAX_VALUE) {
                continue;
            }
            if (currentCost < shortestCost) {
                shortestCost = currentCost;
                nextStation = station;
            }
        }
        return nextStation;
    }

    // Method used to reset visited vertices to unvisited, in case we want to run the algorithm multiple times.
    public void resetVerticesVisited() {
        List<Station> stationList = findAllStations();
        Set<Station> stations = new HashSet<>(stationList);

        for (Station v : stations) {
            v.setVisited(false);
        }
    }

    public Station addStation(Station station) {
        return stationRepository.save(station);
    }

    public Station updateStation(Station station) {
        return stationRepository.save(station);
    }

    public Station findStationById(Integer id) throws ResourceNotFoundException {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent()) {
            return station.get();
        } else {
            throw new ResourceNotFoundException("Could not find station with id: " + id);
        }
    }

    public List<Station> findAllStations() {
        return stationRepository.findAll();
    }

    public StationDetailsDTO findTwoStations(int station1Id, int station2Id) {
        Station station1 = stationRepository.findStationById(station1Id);
        Station station2 = stationRepository.findStationById(station2Id);
        return new StationDetailsDTO(station1, station2);
    }

    public void deleteStationById(Integer id) {
        stationRepository.deleteById(id);
    }

    public void deleteAllStations() {
        stationRepository.deleteAll();
    }

    public List<Leg> findAdjacentLegs(Station station) {
        List<Leg> legList = legRepository.findAll();
        station.setAdjacentLegs(new ArrayList<>());
//        List<Station> sources = new ArrayList<>();
//        Set<Station> sourcesSet = new HashSet<>();

//        for (Leg leg : legList) {
//            leg.getSource().setAdjacentLegs(new ArrayList<>());
//            sourcesSet.add(leg.getSource());
//            // System.out.println("source: " + leg.getSource() + " destination: " + leg.getDestination());
//        }
        for (Leg leg : legList) {
//            for (Station station : sourcesSet) {
            if (leg.getSource().equals(station)) {
                station.getAdjacentLegs().add(leg);
//                    break;
            }
//            }
        }
        return station.getAdjacentLegs();
    }

}
