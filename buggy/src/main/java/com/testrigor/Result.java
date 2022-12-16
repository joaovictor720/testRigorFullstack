package com.testrigor;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.json.JSONObject;

import org.json.XML;

class Result {

    private static final String API_HOST = "http://159.65.167.108:3000";
    private static final String API_PATH = "/api/v1/people";
    private static final Gson GSON_MAPPER = new Gson();

    /*
	 * Gets the API response in string format containing people list, parses it into an object,
	 * collects the name of the people for creating a collection of strings with those names
	 * and returns it. The collection should not contain duplicated names.
	 *
     */
    public static Collection<String> collectUniquePeopleNamesFromApiResponse() throws IOException {
        /** Variable name was 'apiRespStr', but it was being used as 
         * 'apiResponseStr' later on the code. Changed to the longer one,
         * so it is more verbose and clear.
         */ 
        String apiResponseStr = callPeopleApi();
        
        /**
         * Code was previously trying to decode the API response directly
         * from 'apiResponseStr' as a JSON, but the API responds with XML.
         * Now it converts 'apiResponseStr' from XML to JSON, and then
         * decodes it.
         */
        JSONObject apiJson = XML.toJSONObject(apiResponseStr);
        JSONWrapper apiResponse = GSON_MAPPER.fromJson(apiJson.toString(), JSONWrapper.class);
        
        List<Person> peopleList = apiResponse.getResponse().getData().getData();

        List<String> peopleNames = new ArrayList<>();
        
        /**
         * Adds a name to the list only if it doesn't already contain it, in
         * accordance to this method's purpose.
         */
        for (Person p : peopleList){
            if (!peopleNames.contains(p.getName())){
                peopleNames.add(p.getName());
            }
        }
        return peopleNames;
    }

    /*
	 * Calls an API that returns a list of people inside a response
     */
    public static String callPeopleApi() throws IOException {
        URL url = new URL(API_HOST + API_PATH);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Accept", "application/xml");
        try ( InputStream is = connection.getInputStream()) {
            try ( BufferedReader bf = new BufferedReader(new InputStreamReader(is))) {
                return bf
                        .lines()
                        .collect(Collectors.joining("\n"));
            }
        }
    }
    
    class JSONWrapper{
        private Response Response;

        public Response getResponse() {
            return Response;
        }

        public void setResponse(Response response) {
            this.Response = response;
        }
    }

    class Response {
        private Data data;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }
    
    class Data {
        List<Person> data;

        public List<Person> getData() {
            return data;
        }

        public void setData(List<Person> data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Response response = (Response) o;
            return Objects.equals(data, response.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }
    class Person {
        String name;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }
}
