package testcase;
import core.utilities.DataHelper;
import core.utilities.DateTimeHelper;
import dataobject.DataProject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.HomePage;
import pageobject.SearchResultPage;
import java.util.Locale;

public class SearchWeather extends BaseTest{
    @Test
    public void SearchWeatherOfCity(){
        HomePage homePage = new HomePage();
        homePage.searchWeather(DataProject.inputSearch);
        homePage.clickCityName();

        SearchResultPage searchResultPage = new SearchResultPage();
        String timeLocal = searchResultPage.getTimeLocal();
        String cityName = searchResultPage.getCityName().split(",")[0].trim().replace(" ","");
        String temperature = searchResultPage.getTemperature();

        Assert.assertEquals(DateTimeHelper.getCurrentDateTime().toLowerCase(Locale.ROOT),timeLocal.toLowerCase(Locale.ROOT));

        Assert.assertEquals(DataProject.inputSearch.toLowerCase(Locale.ROOT).trim().replace(" ",""),cityName.toLowerCase(Locale.ROOT));

        Assert.assertEquals(DataHelper.isNumeric(temperature),true);
        System.out.println(timeLocal);
        System.out.println(cityName);
        System.out.println(temperature);

    }
}
