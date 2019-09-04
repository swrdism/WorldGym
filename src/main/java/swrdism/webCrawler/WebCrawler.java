package swrdism.webCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import swrdism.model.*;
import java.sql.*;
import java.util.*;
import java.util.Date;


public class WebCrawler {
    private Set<StoreData> storeDataSet = new HashSet<>();
    private List<WorldGymClass> worldGymClasses = new ArrayList<>();
    private Set<CategoryData> categoryDataSet = new HashSet<>();
    private Set<ClassNameData> classNameDataSet = new HashSet<>();
    private Set<TeacherData> teacherDataSet = new HashSet<>();
    private UpdateTime updateTime = new UpdateTime();
    private final String url = "http://www.worldgymtaiwan.com/zh-tw/schedule";

    public List<WorldGymClass> getWorldGymClasses() {
        return worldGymClasses;
    }
    public Set<StoreData> getStoreDataSet() {
        return storeDataSet;
    }
    public UpdateTime getUpdateTime() {
        return updateTime;
    }
    public Set<CategoryData> getCategoryDataSet() {
        return categoryDataSet;
    }
    public Set<ClassNameData> getClassNameDataSet() {
        return classNameDataSet;
    }
    public Set<TeacherData> getTeacherDataSet() {
        return teacherDataSet;
    }

    public void StoreCrawler() {


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get(url);

        Document document = Jsoup.parse(driver.getPageSource());
        Elements stores = document.getElementById("store").getElementsByTag("li");

        for (Element s : stores) {
            StoreData storeData = new StoreData();
            storeData.setName(s.text());
            storeData.setCity(s.attr("name"));
            storeData.setStoreId(s.attr("onclick").toString().split(",")[1].replace(")", ""));
            storeDataSet.add(storeData);
        }


        driver.quit();
    }

    public void ClassCrawler(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);

        int j=0;
        for (StoreData storeData : storeDataSet){
            j++;
            if (j==3){
                break;
            }

            driver.get(url+"#class!id="+storeData.getStoreId());
            driver.navigate().refresh();

            Document document = Jsoup.parse(driver.getPageSource());
            try{
            for (Element classForm : document.getElementsByClass("st-list-1")){

                Elements li =classForm.getElementsByTag("li");

                String weekday =li.get(0).text();

                for (int i=1; i<li.size(); i++) {
                    WorldGymClass worldGymClass = new WorldGymClass();

                    Element data = li.get(i);
                    String startTimeString = data.getElementsByClass("time").text().split("- ")[0].trim();

                    Time startTime = new Time(0);
                    startTime.setHours(Integer.parseInt(startTimeString.charAt(0) +
                            String.valueOf(startTimeString.charAt(1))));
                    startTime.setMinutes(Integer.parseInt(startTimeString.charAt(startTimeString.length() - 2)
                            +String.valueOf(startTimeString.charAt(startTimeString.length()-1))));

                    String endTimeString = data.getElementsByClass("time").text().split("- ")[1].trim();
                    Time endTime = new Time(0);
                    endTime.setHours(Integer.parseInt(endTimeString.charAt(0) +
                            String.valueOf(endTimeString.charAt(1))));
                    endTime.setMinutes(Integer.parseInt(endTimeString.charAt(endTimeString.length() - 2)
                            +String.valueOf(endTimeString.charAt(endTimeString.length()-1))));

                    worldGymClass.setStoreName(storeData.getName());

                    worldGymClass.setCity(storeData.getCity());

                    worldGymClass.setDay(weekday);

                    worldGymClass.setCategory(data.attr("name"));
                    CategoryData categoryData = new CategoryData();
                    categoryData.setName(worldGymClass.getCategory());
                    categoryDataSet.add(categoryData);

                    worldGymClass.setName(data.getElementsByTag("a").attr("title"));
                    ClassNameData classNameData = new ClassNameData();
                    classNameData.setName(worldGymClass.getName());
                    classNameDataSet.add(classNameData);

                    worldGymClass.setStartTime(startTime);

                    worldGymClass.setEndTime(endTime);

                    worldGymClass.setTeacher(data.getElementsByClass("name").text().replace("(代)", ""));
                    TeacherData teacherData = new TeacherData();
                    teacherData.setName(worldGymClass.getTeacher());
                    teacherDataSet.add(teacherData);

                    worldGymClasses.add(worldGymClass);

                }}
            }catch (Exception ex){
                System.out.println(storeData.getStoreId());
                System.out.println(ex);
            }
        }
        Date date = new Date();
        updateTime.setDate(new java.sql.Date(date.getMonth()));
    }

}

