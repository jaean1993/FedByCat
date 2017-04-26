package fedbycat.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by anjin on 4/23/17.
 */
public class FedByCatApiResult {

    private static final Logger log = Logger.getLogger(FedByCatApiResult.class);

    private static Gson gson = new GsonBuilder().serializeNulls().create();

    private int code;

    private String msg;

    private Object result;

    public FedByCatApiResult() {
    }

    public FedByCatApiResult(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static void writeResponse(HttpServletRequest request, HttpServletResponse response, String output) throws IOException {

        if (output == null) {
            return;
        }

        log.info(output);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        if (writer != null) {
            writer.write(output);
            writer.close();
        }
    }

    public static void writeResponseFail(HttpServletRequest request, HttpServletResponse response, Object output) throws IOException {

        FedByCatApiResult res = new FedByCatApiResult(1, "fail", output);
        writeResponse(request, response, gson.toJson(res));
    }

    public static void writeResponseException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        try {
            writeResponseFail(request, response, e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void writeResponseOk(HttpServletRequest request, HttpServletResponse response, Object output) throws IOException {
        writeResponse(request, response, successForObj(output));
    }

    private static String successForObj(Object output) {

        if (output instanceof JsonElement) {
            JsonObject res = new JsonObject();
            res.addProperty("code", 0);
            res.addProperty("msg", "success");
            res.add("result", (JsonElement) output);
            return res.toString();
        } else {
            FedByCatApiResult res = new FedByCatApiResult(0, "success", output);
            return gson.toJson(res);
        }

    }

}
