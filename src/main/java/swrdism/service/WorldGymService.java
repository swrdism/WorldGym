package swrdism.service;

import org.springframework.stereotype.Service;
import swrdism.model.*;
import swrdism.repository.*;
import swrdism.webCrawler.WebCrawler;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Service
public class WorldGymService {

    @Resource
    private ClassDataRepository classDataRepository;

    public List<ClassData> getAllClassData() {
        return (List<ClassData>) classDataRepository.findAll();
    }

    @Transactional
    public void updateClassData(Set<ClassData> classDataSet){
        for (ClassData classData : classDataSet){
            classDataRepository.save(classData);
        }
    }

    public void truncateClassData() {
        classDataRepository.truncate();
    }


    @Resource
    private StoreDataRepository storeDataRepository;

    public List<StoreData> getAllStoreData() {
        return (List<StoreData>) storeDataRepository.findAll();
    }

    @Transactional
    public void updateStoreData(Set<StoreData> storeDataSet){
        for (StoreData storeData : storeDataSet){
            storeDataRepository.save(storeData);
        }
    }

    public void truncateStoreData() {
        storeDataRepository.truncate();
    }


    @Resource
    private TeacherDataRepository teacherDataRepository;

    public List<TeacherData> getAllTeacherData() {
        return (List<TeacherData>) teacherDataRepository.findAll();
    }

    @Transactional
    public void updateTeacherData(Set<TeacherData> teacherDataSet){
        for (TeacherData teacherData : teacherDataSet){
            teacherDataRepository.save(teacherData);
        }
    }

    public void truncateTeacherData() {
        teacherDataRepository.truncate();
    }

    @Resource
    private UpdateTimeRepository updateTimeRepository;

    public UpdateTime getUpdateTime() {
        return updateTimeRepository.findById(1).orElse(null);
    }

    @Transactional
    public void updateUpdateTime(UpdateTime updateTime){
        updateTimeRepository.save(updateTime);
    }

    public void truncateUpdateTime() {
        updateTimeRepository.truncate();
    }

    @Resource
    private WorldGymClassRepository worldGymClassRepository;

    public List<WorldGymClass> getAllWorldGymClass() {
        return (List<WorldGymClass>) worldGymClassRepository.findAll();
    }

    @Transactional
    public void updateWorldGymClass(List<WorldGymClass> worldGymClassSet){
        for (WorldGymClass worldGymClass : worldGymClassSet){
            worldGymClassRepository.save(worldGymClass);
        }
    }
    public void truncateWorldGymClass() {
        worldGymClassRepository.truncate();
    }


    public boolean isUpdated() {

        UpdateTime now = new UpdateTime();
        now.setDate(new java.sql.Date(new Date().getTime()));

        System.out.printf("%d%n", now.getDate().getMonth());

        boolean isUpdated;

        if (getUpdateTime() == null) {
            isUpdated = false;
            System.out.printf("null%n");
        } else {
            if (getUpdateTime().getDate().getMonth() != now.getDate().getMonth()) {
                isUpdated = false;
            } else {
                isUpdated = true;
            }
        }

        if (!isUpdated) {

            System.out.println("WebCrawling");

            WebCrawler webCrawler = new WebCrawler();
            webCrawler.StoreCrawler();
            webCrawler.ClassCrawler();

            System.out.println("WebCrawled");

            truncateUpdateTime();
            truncateClassData();
            truncateStoreData();
            truncateTeacherData();
            truncateWorldGymClass();

            updateUpdateTime(now);
            updateClassData(webCrawler.getClassDataSet());
            updateStoreData(webCrawler.getStoreDataSet());
            updateTeacherData(webCrawler.getTeacherDataSet());
            updateWorldGymClass(webCrawler.getWorldGymClasses());
        }
        return true;
    }

}

