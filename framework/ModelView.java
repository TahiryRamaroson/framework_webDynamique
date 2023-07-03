package etu1849.framework;

import java.util.HashMap;

public class ModelView {
    String urlView;
    HashMap<String,Object> data = new HashMap<>();
    HashMap<String, Object> session = new HashMap<String, Object>();

    public ModelView(){}

    public ModelView(String url) {
        this.setUrlView(url);
    }

    public void addItem(String name, Object value){
        this.getData().put(name, value);
    }

    public void addSession(String key, Object value){
        this.session.putIfAbsent(key, value);
    }

    public String getUrlView() {
        return urlView;
    }
    public void setUrlView(String urlView) {
        this.urlView = urlView;
    }
    public HashMap<String, Object> getData() {
        return data;
    }
    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    public HashMap<String, Object> getSession() {
        return session;
    }
    public void setSession(HashMap<String, Object> session) {
        this.session = session;
    }

}