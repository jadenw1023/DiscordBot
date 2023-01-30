package com.experiment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;

public class MovieAPI {
    public static int pageNum = 1;

    // handles the search calls
    public static List<Anime> searchAnime(String search) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://anime-db.p.rapidapi.com/anime?page="+pageNum+"&size=1&search="+search)
                .get()
                .addHeader("X-RapidAPI-Key", Tokens.apiKey)
                .addHeader("X-RapidAPI-Host", "anime-db.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        if(response.code()!=200){
            System.out.println("Error Occured, Status code: "+response.code());
            return null;
        }

        // formats the json response
        String json = response.body().string();
        response.close();
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(json, JsonObject.class);
        JsonArray animeArray = obj.get("data").getAsJsonArray();
        List<Anime> animeList = new ArrayList<>();
        for (JsonElement animeElement : animeArray) {
            Anime anime = gson.fromJson(animeElement, Anime.class);
            animeList.add(anime);
        }

        return animeList;
    }

    // handles the genre calls
    public static List<Anime> searchGenre(String genre) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://anime-db.p.rapidapi.com/anime?page="+pageNum+"&size=1&genres=" + genre)
                .get()
                .addHeader("X-RapidAPI-Key", Tokens.apiKey)
                .addHeader("X-RapidAPI-Host", "anime-db.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        if(response.code()!=200){
            System.out.println("Error Occured, Status code: "+response.code());
            return null;
        }

        // formats the json response
        String json = response.body().string();
        response.close();
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(json, JsonObject.class);
        JsonArray animeArray = obj.get("data").getAsJsonArray();
        List<Anime> animeList = new ArrayList<>();
        for (JsonElement animeElement : animeArray) {
            Anime anime = gson.fromJson(animeElement, Anime.class);
            animeList.add(anime);
        }

        return animeList;

    }

    public static int nextPage(){
        pageNum += 1;
        return pageNum;
    }
    public static int prevPage(){
        pageNum -= 1;
        return pageNum;
    }
    public static int resetPageNum(){
        pageNum = 1;
        return pageNum;
    }


    static class Anime {

        @SerializedName("title")
        private String title;

        @SerializedName("genres")
        private List<String> genres;

        @SerializedName("type")
        private String type;


        @SerializedName("episodes")
        private String episodes;

        @SerializedName("image")
        private String image;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public String getEpisodes() {
            return episodes;
        }

        public void setEpisodes(String episodes) {
            this.episodes = episodes;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }
}



