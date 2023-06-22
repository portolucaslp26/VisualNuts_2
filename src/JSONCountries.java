import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.io.*;

public class JSONCountries {

    public static void main(String[] args) {
        String jsonContent = "[\n" +
                "    {\n" +
                "        \"country\": \"US\",\n" +
                "        \"languages\": [\"en\"]\n" +
                "    },\n" +
                "    {\n" +
                "        \"country\": \"BE\",\n" +
                "        \"languages\": [\"nl\", \"fr\", \"de\"]\n" +
                "    },\n" +
                "    {\n" +
                "        \"country\": \"NL\",\n" +
                "        \"languages\": [\"nl\", \"fy\"]\n" +
                "    },\n" +
                "    {\n" +
                "        \"country\": \"DE\",\n" +
                "        \"languages\": [\"de\"]\n" +
                "    },\n" +
                "    {\n" +
                "        \"country\": \"ES\",\n" +
                "        \"languages\": [\"es\"]\n" +
                "    }\n" +
                "]";

        try {
            JSONArray jsonData = new JSONArray(jsonContent);

            System.out.println("Number of countries: " + getNumberOfCountries(jsonData));
            System.out.println("Country with the most official languages where they speak German: " +
                    getCountryWithMostLanguages(jsonData, "de"));
            System.out.println("Total count of official languages: " + countOfficialLanguages(jsonData));
            System.out.println("Country with the highest number of official languages: " +
                    getCountryWithHighestLanguages(jsonData));
            System.out.println("Most common official language(s) of all countries: " +
                    getMostCommonLanguage(jsonData));
        } catch (JSONException e) {
            System.out.println("Invalid JSON data.");
        }
    }

    private static int getNumberOfCountries(JSONArray jsonData) {
        return jsonData.length();
    }

    private static String getCountryWithMostLanguages(JSONArray jsonData, String language) {
        String countryWithMostLanguages = "";
        int maxLanguages = 0;

        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject countryData = jsonData.getJSONObject(i);
            String country = countryData.getString("country");
            JSONArray languages = countryData.getJSONArray("languages");

            if (languages.toString().contains("\"" + language + "\"") && languages.length() > maxLanguages) {
                countryWithMostLanguages = country;
                maxLanguages = languages.length();
            }
        }

        return countryWithMostLanguages;
    }

    private static int countOfficialLanguages(JSONArray jsonData) {
        Set<String> languageSet = new HashSet<>();

        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject countryData = jsonData.getJSONObject(i);
            JSONArray languages = countryData.getJSONArray("languages");

            for (int j = 0; j < languages.length(); j++) {
                languageSet.add(languages.getString(j));
            }
        }

        return languageSet.size();
    }

    private static String getCountryWithHighestLanguages(JSONArray jsonData) {
        String countryWithHighestLanguages = "";
        int highestLanguages = 0;

        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject countryData = jsonData.getJSONObject(i);
            String country = countryData.getString("country");
            JSONArray languages = countryData.getJSONArray("languages");

            if (languages.length() > highestLanguages) {
                countryWithHighestLanguages = country;
                highestLanguages = languages.length();
            }
        }

        return countryWithHighestLanguages;
    }

    private static List<String> getMostCommonLanguage(JSONArray jsonData) {
        Map<String, Integer> languageCountMap = new HashMap<>();

        for (int i = 0; i < jsonData.length(); i++) {
            JSONObject countryData = jsonData.getJSONObject(i);
            JSONArray languages = countryData.getJSONArray("languages");

            for (int j = 0; j < languages.length(); j++) {
                String language = languages.getString(j);
                languageCountMap.put(language, languageCountMap.getOrDefault(language, 0) + 1);
            }
        }

        int maxCount = 0;
        List<String> mostCommonLanguages = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : languageCountMap.entrySet()) {
            int count = entry.getValue();

            if (count > maxCount) {
                maxCount = count;
                mostCommonLanguages.clear();
                mostCommonLanguages.add(entry.getKey());
            } else if (count == maxCount) {
                mostCommonLanguages.add(entry.getKey());
            }
        }

        return mostCommonLanguages;
    }
}
