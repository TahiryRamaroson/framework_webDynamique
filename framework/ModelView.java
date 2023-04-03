package etu1849.framework;

public class ModelView {
    String urlView;

    public ModelView(String url) {
        this.setUrlView(url);
    }

    public String getUrlView() {
        return urlView;
    }

    public void setUrlView(String urlView) {
        this.urlView = urlView;
    }

}