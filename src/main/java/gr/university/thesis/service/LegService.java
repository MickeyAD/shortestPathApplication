package gr.university.thesis.service;

import org.springframework.stereotype.Service;


@Service
public class LegService {

}

//    @Autowired
//    private LegRepository legRepository;
//
//    public List<Leg> findLegs() {
//        return legRepository.findAll();
//    }
//
//    public Leg findLegById(Integer id) throws Exception {
//        Optional<Leg> leg = legRepository.findById(id);
//        if (leg.isPresent()) {
//            return leg.get();
//        } else {
//            throw new Exception("Could not find leg with id: " + id);
//        }
//    }

//    public Leg createLeg(Leg leg) {
//        return legRepository.save(leg);
//    }
//
//    public void deleteLegById(Long id) {
//        legRepository.deleteById(id);
//    }
//
//    public void deleteAllLegs() {
//        legRepository.deleteAll();
//    }
//
//    public Leg updateLeg(Leg leg) {
//        return legRepository.save(leg);
//    }