package com.example.json_studenta;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {
    HttpURLConnection connection;

    public Request(String address) throws IOException {
        URL url = new URL(address);
        connection = (HttpURLConnection) url.openConnection();
    }

    public InputStream get() {
        try {
            connection.setRequestMethod("GET");
            return new BufferedInputStream(connection.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertToJson(InputStream input) {
        InputStreamReader inputStreamReader = new InputStreamReader(input);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();

        String result = "";
        try {
            while ((result = bufferedReader.readLine()) != null) {
                stringBuilder.append(result).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    public String getResponse() throws IOException {
        InputStream inputStream = get();
        return convertToJson(inputStream);
    }
}
