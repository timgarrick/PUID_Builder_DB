package com.timgarrick.puid_builder_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PUIDService {
    private final PUIDRepo puidRepo;

    @Autowired
    public PUIDService(PUIDRepo puidRepo){
        this.puidRepo = puidRepo;
    }

    //CRUD operations
    //create a new PUID
    public PUID addPUID(PUID puid) {
        puid.setPuidname(generatePuidName(puid));
        puid.setCreatedDate(new Date());
        puid.setObsolete(false);
        return puidRepo.save(puid);
    }

    private String generatePuidName(PUID puid) {
        //Generate a new puidname + 3 digits
        String partialPuidname = puid.getSurname().toLowerCase() + puid.getFirstname().toLowerCase().charAt(0);
        List<PUID> listOfPuids = puidRepo.findByPUIDName(partialPuidname);

        if(!listOfPuids.isEmpty()) {
            String lastMatchingPuidname = listOfPuids.get(0).getPuidname();
            int lastThreeDigits = Integer.parseInt(lastMatchingPuidname.substring(lastMatchingPuidname.length()-3));
            partialPuidname = partialPuidname + (lastThreeDigits+1);
        } else {
            partialPuidname = partialPuidname + "100";
        }

        return partialPuidname;
    }

    //read a PUID from database
    public PUID findPUIDByID(Long id) {
        return puidRepo.findById(id).orElseThrow();
    }

    //read a PUID from database
    public List<PUID> findPUIDByPUIDName(String puidname) {
        return puidRepo.findByPUIDName(puidname);
    }

    //read all PUID from database
    public List<PUID> findAllUsers() {
        return puidRepo.findAll();
    }

    //update PUID
    public PUID updatePUID(PUID puid) {
        puid.setModifiedDate(new Date());
        puid.setPuidname(generatePuidName(puid));
        return puidRepo.save(puid);
    }

    //update PUID by id
    public PUID updatePUIDByID(Long id){
        PUID puid = findPUIDByID(id);
        puid.setModifiedDate(new Date());
        puid.setPuidname(generatePuidName(puid));
        return puidRepo.save(puid);
    }

    //delete PUID by setting obsolete flag
    public PUID deletePUID(PUID puid) {
        puid.setModifiedDate(new Date());
        puid.setObsolete(true);
        return puidRepo.save(puid);
    }

    //delete PUID by ID by setting obsolete flag
    public PUID deletePUIDByID(Long id) {
        PUID puid = findPUIDByID(id);
        puid.setModifiedDate(new Date());
        puid.setObsolete(true);
        return puidRepo.save(puid);
    }
}
