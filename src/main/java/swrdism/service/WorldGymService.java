package swrdism.service;

import org.springframework.stereotype.Service;
import swrdism.exception.NotFoundException;
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
    private CategoryDataRepository categoryDataRepository;

    public List<CategoryData> getAllCategoryData() {
        return (List<CategoryData>) categoryDataRepository.findAll();
    }

    @Transactional
    public void updateCategoryData(Set<CategoryData> categoryDataSet) {
        for (CategoryData categoryData : categoryDataSet) {
            categoryDataRepository.save(categoryData);
        }
    }

    public void deleteCategoryData() {
        categoryDataRepository.deleteAll();
    }


    @Resource
    private ClassNameDataRepository classNameDataRepository;

    public List<ClassNameData> getAllClassNameData() {
        return (List<ClassNameData>) classNameDataRepository.findAll();
    }

    @Transactional
    public void updateClassNameData(Set<ClassNameData> classNameDataSet){
        for (ClassNameData classNameData : classNameDataSet){
            classNameDataRepository.save(classNameData);
        }
    }

    public void deleteClassNameData() {
        classNameDataRepository.deleteAll();
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

    public void deleteStoreData() {
        storeDataRepository.deleteAll();
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

    public void deleteTeacherData() {
        teacherDataRepository.deleteAll();
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
    public void deleteUpdateTime() {
        updateTimeRepository.deleteAll();
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
    public void deleteWorldGymClass() {
        worldGymClassRepository.deleteAll();
    }


    public void checkUpdate(){

        Date month = new java.sql.Date(new Date().getMonth());

        boolean update;
        if (getUpdateTime() == null){
            update = true;
        }else{
            if(getUpdateTime().getDate() != month){
                update =true;
            }
            else {
                update = false;
            }
        }

        if (update){

            System.out.println("WebCrawling");

            WebCrawler webCrawler = new WebCrawler();
            webCrawler.StoreCrawler();
            webCrawler.ClassCrawler();
            System.out.println("WebCrawled");

            deleteUpdateTime();
            updateUpdateTime(webCrawler.getUpdateTime());

            deleteCategoryData();
            deleteClassNameData();
            deleteStoreData();
            deleteTeacherData();
            deleteWorldGymClass();

            updateCategoryData(webCrawler.getCategoryDataSet());
            updateClassNameData(webCrawler.getClassNameDataSet());
            updateStoreData(webCrawler.getStoreDataSet());
            updateTeacherData(webCrawler.getTeacherDataSet());
            updateWorldGymClass(webCrawler.getWorldGymClasses());
        }
    }
}
