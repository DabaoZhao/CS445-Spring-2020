package org.pop.rs.Response;

public class FailureResponse {
    String type = "http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation";
    String title = "Your request data didn't pass validation";
    String detail;
    int status;
    String instance;

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public int getStatus() {
        return status;
    }

    public String getInstance() {
        return instance;
    }
}
