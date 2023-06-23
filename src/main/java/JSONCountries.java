package main.java;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.io.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static int getNumberOfCountries(JSONArray jsonData) {
        return (int) IntStream.range(0, jsonData.length())
                .count();
    }

    public static String getCountryWithMostLanguages(JSONArray jsonData, String language) {
        return IntStream.range(0, jsonData.length())
                .mapToObj(jsonData::getJSONObject)
                .filter(countryData -> {
                    JSONArray languages = countryData.getJSONArray("languages");
                    return languages.toString().contains("\"" + language + "\"");
                })
                .max(Comparator.comparingInt(countryData -> {
                    JSONArray languages = countryData.getJSONArray("languages");
                    return languages.length();
                }))
                .map(countryData -> countryData.getString("country"))
                .orElse("");
    }

    public static int countOfficialLanguages(JSONArray jsonData) {
        return (int) IntStream.range(0, jsonData.length())
                .mapToObj(jsonData::getJSONObject)
                .flatMap(countryData -> {
                    JSONArray languages = countryData.getJSONArray("languages");
                    return IntStream.range(0, languages.length())
                            .mapToObj(languages::getString);
                })
                .collect(Collectors.toSet())
                .size();
    }

    public static String getCountryWithHighestLanguages(JSONArray jsonData) {
        return IntStream.range(0, jsonData.length())
                .mapToObj(jsonData::getJSONObject)
                .max(Comparator.comparingInt(countryData -> {
                    JSONArray languages = countryData.getJSONArray("languages");
                    return languages.length();
                }))
                .map(countryData -> countryData.getString("country"))
                .orElse("");
    }


    public static List<String> getMostCommonLanguage(JSONArray jsonData) {
        Map<String, Long> languageCountMap = IntStream.range(0, jsonData.length())
                .mapToObj(jsonData::getJSONObject)
                .flatMap(countryData -> {
                    JSONArray languages = countryData.getJSONArray("languages");
                    return IntStream.range(0, languages.length())
                            .mapToObj(languages::getString);
                })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long maxCount = languageCountMap.values().stream()
                .max(Long::compare)
                .orElse(0L);

        return languageCountMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
