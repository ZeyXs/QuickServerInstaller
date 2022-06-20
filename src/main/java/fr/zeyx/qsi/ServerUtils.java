package fr.zeyx.qsi;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class ServerUtils {

    private static HttpURLConnection connection;

    private static JsonElement parseJson(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }

    private static JsonElement getJsonFrom(String paperUrl) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL(paperUrl);
            connection = (HttpURLConnection) url.openConnection();

            // Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }

            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();

            return parseJson(responseContent.toString());

        } catch (MalformedURLException e) {
            System.out.println("Request Failed: Wrong URL (" + paperUrl + ")");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("Request Failed: Connection failed. (" + paperUrl + ")");
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> getVersions() {
        JsonElement json = getJsonFrom("https://api.papermc.io/v2/projects/paper/");

        Map<String, Object> deserializedJson = new Gson().fromJson(
                json, new TypeToken<HashMap<String, Object>>() {}.getType()
        );

        return (ArrayList<String>) deserializedJson.get("versions");
    }

    public static ArrayList<Integer> getBuilds(String version) {
        JsonElement json = getJsonFrom("https://api.papermc.io/v2/projects/paper/versions/" + version);

        Map<String, Object> deserializedJson = new Gson().fromJson(
                json, new TypeToken<HashMap<String, Object>>() {}.getType()
        );

        return (ArrayList<Integer>) deserializedJson.get("builds");
    }

    public static String getFAWEDownloadLink() {
        JsonElement json = getJsonFrom("https://ci.athion.net/job/FastAsyncWorldEdit/lastStableBuild/api/json");

        Map<String, Object> deserializedJson = new Gson().fromJson(
                json, new TypeToken<HashMap<String, Object>>() {}.getType()
        );

        Map<String, String> artifact = ((ArrayList<Map<String, String>>) deserializedJson.get("artifacts")).get(0);
        return "https://ci.athion.net/job/FastAsyncWorldEdit/lastSuccessfulBuild/artifact/" + artifact.get("relativePath");
    }

    public static String getPaperDownloadLink(String version, int build) {
        return "https://api.papermc.io/v2/projects/paper/versions/" + version + "/builds/" + build + "/downloads/paper-" + version + "-" + build + ".jar";
    }
}
