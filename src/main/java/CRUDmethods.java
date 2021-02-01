import okhttp3.*;

import java.io.IOException;

public class CRUDmethods {

    public static void create() {
    }

    public static void read() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://hapi.fhir.org/baseR5/AllergyIntolerance/")
                .method("GET", null)
                .addHeader("Content-Type", "application/fhir+xml")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update() {
    }

    public static void delete() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/fhir+xml");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://hapi.fhir.org/baseR4/AllergyIntolerance/example")
                .method("DELETE", body)
                .addHeader("Content-Type", "application/fhir+xml")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
