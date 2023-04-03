package etu1849.framework;

/**
 * ClassMapping
 */
public class ClassMapping {
    String className;
    String Method;


    public ClassMapping(String className, String method) {
        this.setClassName(className);
        this.setMethod(method);
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMethod() {
        return Method;
    }
    public void setMethod(String method) {
        Method = method;
    } 
}