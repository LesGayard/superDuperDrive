package com.udacity.jwdnd.course1.cloudstorage.model;

public class NoteModel {
    private Integer noteid;
    private String notetitle;
    private String notedescription;
    private Integer userId;

    public NoteModel() {}

    public NoteModel(Integer noteId, String notetitle, String notedescription, Integer userId) {
        this.noteid = noteId;
        this.notetitle = notetitle;
        this.notedescription = notedescription;
        this.userId = userId;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteid +
                ", noteTitle='" + notetitle + '\'' +
                ", noteDescription='" + notedescription + '\'' +
                ", userId=" + userId +
                '}';
    }
}
