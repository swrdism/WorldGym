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

    @GetMapping(value = "/getCategoryDataList")
    public ResponseEntity<List<CategoryData>> getCategoryDataList(){
        List<CategoryData> categoryDataList = worldGymService.getAllCategoryData();
        return ResponseEntity.ok(categoryDataList);
    }

    @GetMapping(value = "/getClassNameDataList")
    public ResponseEntity<List<ClassNameData>> getClassNameDataList(){
        List<ClassNameData> classNameDataList = worldGymService.getAllClassNameData();
        return ResponseEntity.ok(classNameDataList);
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
