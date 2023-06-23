package test;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import main.java.*;
import java.util.List;

public class JSONCountriesTest {

    @Test
    public void testGetNumberOfCountries() {
        String jsonContent = "[{\"country\":\"US\",\"languages\":[\"en\"]},{\"country\":\"BE\",\"languages\":[\"nl\",\"fr\",\"de\"]},{\"country\":\"NL\",\"languages\":[\"nl\",\"fy\"]},{\"country\":\"DE\",\"languages\":[\"de\"]},{\"country\":\"ES\",\"languages\":[\"es\"]}]";
        JSONArray jsonData = new JSONArray(jsonContent);

        int numberOfCountries = JSONCountries.getNumberOfCountries(jsonData);

        Assertions.assertEquals(5, numberOfCountries);
    }

    @Test
    public void testGetCountryWithMostLanguages() {
        String jsonContent = "[{\"country\":\"US\",\"languages\":[\"en\"]},{\"country\":\"BE\",\"languages\":[\"nl\",\"fr\",\"de\"]},{\"country\":\"NL\",\"languages\":[\"nl\",\"fy\"]},{\"country\":\"DE\",\"languages\":[\"de\"]},{\"country\":\"ES\",\"languages\":[\"es\"]}]";
        JSONArray jsonData = new JSONArray(jsonContent);

        String countryWithMostLanguages = JSONCountries.getCountryWithMostLanguages(jsonData, "de");

        Assertions.assertEquals("BE", countryWithMostLanguages);
    }

    @Test
    public void testCountOfficialLanguages() {
        String jsonContent = "[{\"country\":\"US\",\"languages\":[\"en\"]},{\"country\":\"BE\",\"languages\":[\"nl\",\"fr\",\"de\"]},{\"country\":\"NL\",\"languages\":[\"nl\",\"fy\"]},{\"country\":\"DE\",\"languages\":[\"de\"]},{\"country\":\"ES\",\"languages\":[\"es\"]}]";
        JSONArray jsonData = new JSONArray(jsonContent);

        int officialLanguagesCount = JSONCountries.countOfficialLanguages(jsonData);

        Assertions.assertEquals(6, officialLanguagesCount);
    }

    @Test
    public void testGetCountryWithHighestLanguages() {
        String jsonContent = "[{\"country\":\"US\",\"languages\":[\"en\"]},{\"country\":\"BE\",\"languages\":[\"nl\",\"fr\",\"de\"]},{\"country\":\"NL\",\"languages\":[\"nl\",\"fy\"]},{\"country\":\"DE\",\"languages\":[\"de\"]},{\"country\":\"ES\",\"languages\":[\"es\"]}]";
        JSONArray jsonData = new JSONArray(jsonContent);

        String countryWithHighestLanguages = JSONCountries.getCountryWithHighestLanguages(jsonData);

        Assertions.assertEquals("BE", countryWithHighestLanguages);
    }

    @Test
    public void testGetMostCommonLanguage() {
        String jsonContent = "[{\"country\":\"US\",\"languages\":[\"en\"]},{\"country\":\"BE\",\"languages\":[\"nl\",\"fr\",\"de\"]},{\"country\":\"NL\",\"languages\":[\"nl\",\"fy\"]},{\"country\":\"DE\",\"languages\":[\"de\"]},{\"country\":\"ES\",\"languages\":[\"es\"]}]";
        JSONArray jsonData = new JSONArray(jsonContent);

        List<String> mostCommonLanguages = JSONCountries.getMostCommonLanguage(jsonData);

        Assertions.assertEquals(List.of("de", "nl"), mostCommonLanguages);
    }
}

