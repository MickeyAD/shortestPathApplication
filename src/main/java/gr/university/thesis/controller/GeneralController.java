package gr.university.thesis.controller;

//import gr.university.thesis.service.DijktraShortestPathServiceImp;

import gr.university.thesis.entity.Station;
import gr.university.thesis.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GeneralController {

    @Autowired
    private StationService stationService;

//    @Autowired
//    private LegService legService;

    @GetMapping("/test")
    public String test() { //

//        List<Station> stations = stationRepository.findAll();
//        List<Leg> legs = legRepository.findAll();
//        for (Station s : stations) {
//            s.setAdjacentLegs(legs);
//        }
//        Set<Station> stationSet = new HashSet<>(stations);
//        Graph graph = new Graph(stationSet);
//        graph.dijktraAlgorithmDistanceCost(stationRepository.findStationById(1), stationRepository.findStationById(11));

//        Graph map = new Graph();

        List<Station> stationList = stationService.findAllStations();
        for (Station station : stationList) {
            System.out.println(station);
        }


//
//        for (Station station : sourcesSet) {
//            System.out.println(station.getId() + " station has " + station.getAdjacentLegs());
//        }

//        stationService.dijktraAlgorithmTimeCost(one, eight);


        return "lol";

    }
}
