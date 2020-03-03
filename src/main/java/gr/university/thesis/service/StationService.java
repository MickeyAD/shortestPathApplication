package gr.university.thesis.service;

import gr.university.thesis.dto.CalculatePathDTO;
import gr.university.thesis.dto.StationDetailsDTO;
import gr.university.thesis.entity.Leg;
import gr.university.thesis.entity.Station;
import gr.university.thesis.repository.LegRepository;
import gr.university.thesis.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class StationService implements DijktraAlgorithm {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private LegRepository legRepository;

    @Override
    public double dijktraAlgorithmTimeCost(Object start, Object end) {
        Station initialStation = stationRepository.findStationById(((Station) start).getId());
        initialStation.setAdjacentLegs(findAdjacentLegs(initialStation));

        Station finalStation = stationRepository.findStationById(((Station) end).getId());
        finalStation.setAdjacentLegs(findAdjacentLegs(finalStation));

        CalculatePathDTO calculatePathDTO = new CalculatePathDTO();

        List<Station> stationList = findAllStations();
        Set<Station> stations = new HashSet<>(stationList);

        HashMap<Object, Double> shortestPath = new HashMap<>();
        for (Station station : stations) {
            if (station == initialStation) {
                shortestPath.put(initialStation, 0.0); //5 le
            } else {
                shortestPath.put(station, Double.MAX_VALUE);
            }
        }
        initialStation.setVisited(true);

        HashMap<Station, Station> successor = new HashMap<>();
        successor.put(initialStation, null);
        for (Leg leg : initialStation.getAdjacentLegs()) {
            shortestPath.put(leg.getDestination(), (double) leg.getTimeCost());
            successor.put(leg.getDestination(), initialStation);
        }

        while (true) {
            Station nextStation = nextClosestAndUnvisitedVertex(shortestPath);
            if (nextStation == finalStation) {
                System.out.println("The path with the smallest time cost between " + initialStation.getName() + " and " + finalStation.getName() + " is:");
                Station child = finalStation;
                String path = finalStation.getName();
                while (true) {
                    Station parent = successor.get(child);
                    if (parent == null) {
                        break;
                    }
                    path = parent.getName() + " -> " + path;
                    child = parent;
                }
                System.out.println(path);
//                System.out.println("Total time cost: " + Formatter.timeDuration(shortestPath.get(finalStation)) + "\n");
//                calculatePathDTO.setTimeCost(shortestPath.get(finalStation));
                return (double) shortestPath.get(finalStation);
//                break;
            }
            if (nextStation == null) {
                System.out.println("No available path exists between " + initialStation.getName() + " and " + finalStation.getName());
                return 0.0;
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

    @Override
    public Station nextClosestAndUnvisitedVertex(HashMap<Object, Double> shortestPath) { //exei merika custom px alla gia psiloirrelevant opws px n morfopoiei to apotelesma
        List<Station> stationList = findAllStations();
        Set<Station> stations = new HashSet<>(stationList);

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

    @Override
    // Method used to reset visited vertices to unvisited, in case we want to run the algorithm multiple times.
    public void resetVerticesVisited() {
        List<Station> stationList = findAllStations();
        Set<Station> stations = new HashSet<>(stationList);

        for (Station v : stations) {
            v.setVisited(false);
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

//    @Autowired
//    private StationRepository stationRepository;
//
//    public List<Station> findStations() {
//        return stationRepository.findAll();
//    }
//
//    public Station findStationById(Integer id) throws ResourceNotFoundException {
//        Optional<Station> station = stationRepository.findById(id);
//        if (station.isPresent()) {
//            return station.get();
//        } else {
//            throw new ResourceNotFoundException("Could not find station with id: " + id);
//        }
//    }
//    public Station createStation(Station station) {
//        return stationRepository.save(station);
//    }
//
//    public void deleteStationById(Integer id) {
//        stationRepository.deleteById(id);
//    }
//
//    public void deleteAllStations() {
//        stationRepository.deleteAll();
//    }
//
//    public Optional<Station> findStationById(Integer id) {
//        return stationRepository.findById(id);
//    }
//
//    public Station updateStation(Station station) {
//        return stationRepository.save(station);
//    }

