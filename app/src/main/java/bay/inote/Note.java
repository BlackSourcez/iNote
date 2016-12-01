package bay.inote;

/**
 * Created by Bae on 11/23/2016.
 */

public class Note {

    private String Topic;

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    private String Content;

    private String Date;
    private String Time;

    private int Priority;

    @Override
    public String toString(){
        return String.format("Topic: %s\nContent: %s\nPriority: %d\nDate: %s\nTime: %s",getTopic(),getContent(),getPriority(),getDate(),getTime());
    }
}
