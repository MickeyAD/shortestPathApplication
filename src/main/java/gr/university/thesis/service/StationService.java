package gr.university.thesis.service;

import gr.university.thesis.entity.Station;
import gr.university.thesis.exception.ResourceExistsException;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.LegRepository;
import gr.university.thesis.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationService {


    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private LegRepository legRepository;

//    // Method attempts to find the shortest path between two stations/vertices in the map/graph.
//    public void dijktraAlgorithmDistanceCost(Object start, Object end) {
//        Station initialStation = stationRepository.findStationById(((Station) start).getId());
//        initialStation.setAdjacentLegs(findAdjacentLegs(initialStation));
//
//        Station finalStation = stationRepository.findStationById(((Station) end).getId());
//        finalStation.setAdjacentLegs(findAdjacentLegs(finalStation));
//
//        CalculatePathDTO calculatePathDTO = new CalculatePathDTO();
//
//        List<Station> stationList = findAllStations();
//        Set<Station> stations = new HashSet<>(stationList);
//        /*
//         *  Lines 27 - 38 (including comments): Initialization of the algorithm (1):
//         *      1) Shortest path between every station from the start station:
//         *          a) if station = start, then cost = 0
//         *          b) if station != start, then cost = infinite.
//         *      2) Start station is visited, the rest stations are unvisited.
//         */
//        // Shortest path cost of each station from the start station.
//        HashMap<Station, Integer> shortestPath = new HashMap<>();
//        // for loop: Initializes total cost values to every station from the start.
//        for (Station station : stations) {
//            // the distance from start station to itself is zero...
//            if (station == start) {
//                shortestPath.put(initialStation, 0);
//                // ...to every other is infinite.
//            } else {
//                shortestPath.put(station, Integer.MAX_VALUE);
//            }
//        }
//        // Initially, only start station is visited, the rest are unvisited.
//        initialStation.setVisited(true);
//
//        /*
//         *  Lines 48 - 59 (including comments): Initialization of the algorithm (2):
//         *      1) Hashmap of stations:
//         *             a) Each station (child) points to another station (parent).
//         *             b) The start station (child) points to no other station (no parent).
//         *      2) Assign cost values to adjacent edges of the start station.
//         */
//        // Mapping from station to station
//        HashMap<Station, Station> successor = new HashMap<>();
//        // Start station has no parent
//        successor.put(initialStation, null);
//        /*
//         *  For loop:
//         *      1) Assigns cost values between the start station and its adjacent stations.
//         *      2) Adjacent vertices (children) point to the start vertex (parent).
//         */
//        for (Leg leg : initialStation.getAdjacentLegs()) {
//            shortestPath.put(leg.getDestination(), leg.getDistanceCost());
//            successor.put(leg.getDestination(), initialStation);
//        }
//
//        // Loop which repeats until all stations in the graph are visited.
//        while (true) {
//            Station nextStation = nextClosestAndUnvisitedStation(shortestPath);
//            /*
//             * In case the next station is the final end destination:
//             *     1) Print shortest path
//             *     2) Print total cost
//             */
//            if (nextStation == end) {
//                System.out.println("The path with the smallest distance cost between " + initialStation.getName() + " and " + finalStation.getName() + " is:");
//                Station child = finalStation;
//                String path = finalStation.getName();
//                /*
//                 *  Loop which repeats every time there is a previous station (parent) before our final destination (child).
//                 *  The loop ceases to continue after it has reached the start station which has no parent.
//                 */
//                while (true) {
//                    Station parent = successor.get(child);
//                    if (parent == null) {
//                        break;
//                    }
//                    path = parent.getName() + " -> " + path;
//                    child = parent;
//                }
//                System.out.println(path);
//                System.out.println("Total distance cost: " + Formatter.distanceLength(shortestPath.get(end)) + "\n");
//                /*
//                 *  Checks whether the total distance cost is less than a specified amount.
//                 *  If yes, then that distance may be walked.
//                 */
//                if (shortestPath.get(end) < 500) {
//                    System.out.println("Distance can be walked.");
//                    System.out.println("Total walking time cost: " + Formatter.walkingTimeDuration(shortestPath.get(end)) + "\n");
//                } else {
//                    System.out.println("Distance above 500 meters cannot be walked." + "\n");
//                }
//                return;
//            }
//            /*
//             *  In case the next station does not exist and has not reached the destination,
//             *  there is not any available path between the start and end stations.
//             */
//            if (nextStation == null) {
//                System.out.println("No available path exists between " + initialStation.getName() + " and " + finalStation.getName());
//                return;
//            }
//            nextStation.setVisited(true);
//
//            // For loop: Updates shortest path costs of every non visited adjacent station from the current station.
//            for (Leg leg : nextStation.getAdjacentLegs()) {
//                // Skipping already visited adjacent stations.
//                if (leg.getDestination().isVisited()) {
//                    continue;
//                }
//                /*
//                 *  In case the new path of the adjacent station is shorter when going through
//                 *  the current station (nextStation), than its previous cost value:
//                 *      1) Update shortest path of adjacent station to equal the shortest path cost
//                 *         of the station we are currently in plus the cost of the leg which connects
//                 *         the current station and its adjacent one.
//                 *      2) Let the adjacent station be included in the path to the end destination:
//                 *         adjacent station (child) follows the current station (parent).
//                 */
//                if (shortestPath.get(nextStation) + leg.getDistanceCost() < shortestPath.get(leg.getDestination())) {
//                    shortestPath.put(leg.getDestination(), shortestPath.get(nextStation) + leg.getDistanceCost());
//                    successor.put(leg.getDestination(), nextStation);
//                }
//            }
//        }
//    }


//    private Station nextClosestAndUnvisitedStation(HashMap<Station, Integer> shortestPath) {
//        List<Station> stationList = findAllStations();
////        Set<Station> stations = new HashSet<>(stationList);
//
//        for (Station station : stationList) {
//            station.setAdjacentLegs(findAdjacentLegs(station));
//        }
//
//        Station nextStation = null;
//        int shortestCost = Integer.MAX_VALUE;
//        for (Station station : stationList) {
//            int currentCost = shortestPath.get(station);
//            //Skipping already visited stations or ones with unknown shortest path (infinite).
//            if (station.isVisited() || currentCost == Integer.MAX_VALUE) {
//                continue;
//            }
//            if (currentCost < shortestCost) {
//                shortestCost = currentCost;
//                nextStation = station;
//            }
//        }
//        return nextStation;
//    }


    public ResponseEntity<Station> addStation(Station station) {
        try {
            Station newStation = new Station();
            newStation.setName(station.getName());
            newStation.setLatitude(station.getLatitude());
            newStation.setLongitude(station.getLongitude());
            stationRepository.save(newStation);
            return new ResponseEntity<>(newStation, HttpStatus.CREATED);
        } catch (ResourceExistsException resourceExistsException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Station> updateStation(int id, Station stationDetails) {
        try {
            Optional<Station> station = stationRepository.findById(id);
            Station currentStation = station.get();
            currentStation.setName(stationDetails.getName());
            currentStation.setLatitude(stationDetails.getLatitude());
            currentStation.setLongitude(stationDetails.getLongitude());
            stationRepository.save(currentStation);
            return new ResponseEntity<>(currentStation, HttpStatus.CREATED);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Station> findStationById(int id) {
        try {
            Optional<Station> stationOptional = stationRepository.findById(id);
            Station station = stationOptional.get();
            return new ResponseEntity<>(station, HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Station>> findAllStations() {
        try {
            List<Station> stationList = new ArrayList<>();
            stationRepository.findAll().forEach(stationList::add);
            if (!stationList.isEmpty()) {
                return new ResponseEntity<>(stationList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteStationById(int id) {
        try {
            stationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllStations() {
        try {
            stationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

//    public StationDetailsDTO findTwoStations(int station1Id, int station2Id) {
//        Station station1 = stationRepository.findStationById(station1Id);
//        Station station2 = stationRepository.findStationById(station2Id);
//        return new StationDetailsDTO(station1, station2);
//    }


}
