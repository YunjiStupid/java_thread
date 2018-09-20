package zhanglei.class4;
class Producer implements Runnable{
    private Message msg;
    public Producer(Message msg){
        this.msg = msg;
    }
    public void run() {
        for(int x = 0; x < 50; x++){
            if(x%2 == 0){
                this.msg.setTitle("张磊");
                this.msg.setContent("上");
            }else{
                this.msg.setTitle("张磊");
                this.msg.setContent("还需努力");
            }
        }
    }
}
class Consumer{
    private Message msg;
    public Consumer(Message msg){
        this.msg = msg;
    }
    public void run() {
        for(int x = 0; x < 50; x++){
            if(x%2 == 0){
                this.msg.setTitle("张磊");
                this.msg.setContent("上");
            }else{
                this.msg.setTitle("张磊");
                this.msg.setContent("还需努力");

            }
        }
    }
}
class Message{
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
public class MessageModel {

}
