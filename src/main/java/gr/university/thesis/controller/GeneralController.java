package gr.university.thesis.controller;

import gr.university.thesis.entity.Edge;
import gr.university.thesis.entity.Graph;
import gr.university.thesis.entity.Station;
import gr.university.thesis.service.DijktraShortestPathService;
import gr.university.thesis.service.EdgeService;
import gr.university.thesis.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Controller
public class GeneralController {

    @Autowired
    StationService stationService;

//    @Autowired
//    EdgeService edgeService;

    @Autowired
    DijktraShortestPathService dijktraShortestPathService;

    @GetMapping("/Dijktra")
    public String dijktraAlgorithm() {
        Station source = stationService.findStationById(1);
        Station destination = stationService.findStationById(10);
        dijktraShortestPathService.dijktraAlgorithmDistanceCost(source, destination);

//        List<Station> stationList = stationService.findStations();
//        List<Edge> edgeList = edgeService.findEdges();
//
//        List<Station> stationResults = new ArrayList<>();
//        for (Station station : stationList) {
//            station.setName(stationList.iterator().next().getName());
//            station.setAdjacentEdges(new Edge(
//                    edgeList.iterator().next().setSource(edgeList.get(station.getId()).getSource()),
//                    edgeList.iterator().next().setDestination(edgeList.get(station.getId()).getDestination()),
//                    edgeList.iterator().next().setDistanceCost(edgeList.get(station.getId()).getDistanceCost()),
//                    edgeList.iterator().next().setTimeCost(edgeList.get(station.getId()).getTimeCost())));
//            stationResults.add(station);
//        }
//        Set<Station> stationSet = new HashSet<>(stationResults);
//        Graph graph = new Graph(stationSet);
//        graph.dijktraAlgorithmDistanceCost(stationList.get(3), stationList.get(9));
        return "DijktraS";
    }

}
