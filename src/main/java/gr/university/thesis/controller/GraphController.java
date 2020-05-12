package gr.university.thesis.controller;

import gr.university.thesis.dto.Graph;
import gr.university.thesis.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GraphController {

    @Autowired
    private GraphService graphService;

    @GetMapping("/shortestPath/{firstStationId}/{secondStationId}")
    public Graph findShortestPath(@PathVariable int firstStationId,
                                  @PathVariable int secondStationId) {
        return graphService.findShortestPathTimeCost(firstStationId, secondStationId);
    }

    @GetMapping("/test")
    public void test() {
        graphService.test();
    }
}
