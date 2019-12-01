package models;

import net.sf.json.JSONObject;

public class ChatMessage {
    String msgType = "";
    String fromID = "";
    String toID = "";
    String content = "";
    String date = "";
    String extra = "";

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getFromID() {
        return fromID;
    }

    public void setFromID(String fromID) {
        this.fromID = fromID;
    }

    public String getToID() {
        return toID;
    }

    public void setToID(String toID) {
        this.toID = toID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String toJsonString(){
        return JSONObject.fromObject(this).toString();
    }

    public static ChatMessage getInstance(String s){
        JSONObject jsonObject = JSONObject.fromObject(s);
        return (ChatMessage)JSONObject.toBean(jsonObject,ChatMessage.class);
    }



}
