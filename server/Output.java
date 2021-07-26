package server;

import java.util.ArrayList;

public class Output {
    private boolean status;
    private ArrayList<String> messages;
    private Object information;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public void setInformation() {
        this.information = information;
    }

    public boolean getStatus() {
        return status;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public Object getInformation() {
        return information;
    }


}
