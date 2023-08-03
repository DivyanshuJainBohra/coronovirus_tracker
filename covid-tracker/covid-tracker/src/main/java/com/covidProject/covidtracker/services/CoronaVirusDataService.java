package com.covidProject.covidtracker.services;

import com.covidProject.covidtracker.models.LocationStats;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.List;

@Service
public class CoronaVirusDataService {
    private static String  VIRUS_DATA_URL  = "https://data.covid19india.org/csv/latest/states.csv" ;
    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
       HttpResponse<String>  httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());

         StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("State"));
            locationStat.setDead(record.get("Deceased"));
            locationStat.setRecovered(record.get("Recovered"));
            System.out.println(locationStat);
            newStats.add(locationStat);
        }
        this.allStats = newStats;



    }
}
