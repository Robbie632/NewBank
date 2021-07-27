package server;

import java.util.ArrayList;

/**
 * Class for containing information from attempted log in
 */
public class Output {
    private boolean status;
    private ArrayList<String> messages = new ArrayList<String>();
    private Object information;

    /**
     * Setter method, sets whether login was successful or not
     * @param status, whether login was successful or not
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Adds message
     * @param message, message to be added
     */
    public void addMessage(String message) {
        messages.add(message);
    }

    /**
     * Setter method, sets information that is to be returned
     * @param information, information to be stored and returned
     */
    public void setInformation(Object information) {
        this.information = information;
    }

    /**
     * Getter method that returns status
     * @return
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Getter method that returns messages
     * @return
     */
    public ArrayList<String> getMessages() {
        return messages;
    }

    /**
     * Getter method that returns information
     * @return
     */
    public Object getInformation() {
        return information;
    }


}
