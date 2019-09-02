package swrdism.webCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import swrdism.model.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class webCrawler {
    private Set<StoreData> storeDataSet = new HashSet<>();
    private List<WorldGymClass> worldGymClasses = new ArrayList<>();
    private Set<CategoryData> categoryDataSet = new HashSet<>();
    private Set<ClassNameData> classNameDataSet = new HashSet<>();
    private Set<TeacherData> teacherDataSet = new HashSet<>();

    public void Crawler() {
        final String url = "http://www.worldgymtaiwan.com/zh-tw/schedule";

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

        String startTimeString;
        String endTimeString;

        for (StoreData storeData : storeDataSet){

            driver.get(url+"#class!id="+storeData.getStoreId());
            driver.navigate().refresh();
            document = Jsoup.parse(driver.getPageSource());

            for (Element classForm : document.getElementsByClass("st-list-1")){

                Elements li =classForm.getElementsByTag("li");

                String weekday =li.get(0).text();

                for (int i=1; i<li.size(); i++) {
                    WorldGymClass worldGymClass = new WorldGymClass();

                    Element data = li.get(i);

                    startTimeString = data.getElementsByClass("time").text().split("- ")[0];
                    Time startTime = new Time(0);
                    startTime.setHours(Integer.parseInt(startTimeString.split(":")[0]));
                    startTime.setMinutes(Integer.parseInt(startTimeString.split(":")[1]));

                    endTimeString = data.getElementsByClass("time").text().split("- ")[1];
                    Time endTime = new Time(0);
                    endTime.setHours(Integer.parseInt(endTimeString.split(":")[0]));
                    endTime.setMinutes(Integer.parseInt(endTimeString.split(":")[1]));

                    worldGymClass.setStoreName(storeData.getName());
                    worldGymClass.setCity(storeData.getCity());
                    worldGymClass.setDay(weekday);
                    worldGymClass.setCategory(data.attr("name"));
                    worldGymClass.setName(data.getElementsByTag("a").attr("title"));
                    worldGymClass.setStartTime(startTime);
                    worldGymClass.setEndTime(endTime);
                    worldGymClass.setTeacher(data.getElementsByClass("name").text().replace("(代)",""));

                    worldGymClasses.add(worldGymClass);

                }
            }
        }

        driver.quit();
    }

    public List<WorldGymClass> getWorldGymClasses() {
        return worldGymClasses;
    }

    public Set<StoreData> getStoreDataSet() {
        return storeDataSet;
    }
}

