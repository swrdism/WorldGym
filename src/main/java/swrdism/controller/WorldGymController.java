package swrdism.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swrdism.model.*;
import swrdism.service.WorldGymService;
import java.util.List;

@RestController
@RequestMapping(value = "/WorldGym")
public class WorldGymController {

    @Autowired
    private WorldGymService worldGymService;

    @GetMapping(value ="/checkUpdate")
    public void checkUpdate(){
        worldGymService.checkUpdate();
    }



    @GetMapping(value = "/getClassNameDataList")
    public ResponseEntity<List<ClassData>> getClassNameDataList(){
        List<ClassData> classDataList = worldGymService.getAllClassData();
        return ResponseEntity.ok(classDataList);
    }

    @GetMapping(value = "/getTeacherDataList")
    public ResponseEntity<List<TeacherData>> getTeacherDataList(){
        List<TeacherData> teacherDataList = worldGymService.getAllTeacherData();
        return ResponseEntity.ok(teacherDataList);
    }

    @GetMapping(value = "/getStoreDataList")
    public ResponseEntity<List<StoreData>> getStoreDataList(){
        List<StoreData> storeDataList = worldGymService.getAllStoreData();
        return ResponseEntity.ok(storeDataList);
    }

//    @PostMapping(value = "/postResult")
//    public ResponseEntity<WorldGymClass> createClassmates(@RequestBody Classmate request) throws Exception {
//        try {
//            Classmate classmate = classmateService.createClassmate(request);
//            URI location = ServletUriComponentsBuilder
//                    .fromCurrentRequest()
//                    .path("/{id}")
//                    .buildAndExpand(classmate.getNumber())
//                    .toUri();
//            return ResponseEntity.created(location).body(classmate);
//        }catch (NotFoundException ex) {
//            return ResponseEntity.ok(request);
//        }
//    }
}
