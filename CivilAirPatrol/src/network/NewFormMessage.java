package network;

import forms.ScheduledPushModelAbstraction.FormType;

public class NewFormMessage extends NetworkMessage {
    private static final long serialVersionUID = 6246753614374221315L;
    private FormType formType;
    private String missionNo;
    private long date;

    public NewFormMessage(FormType formType, String missionNo, long date) {
        super(MessageType.NEW_FORM);
        this.missionNo = missionNo;
        this.date = date;
        this.formType = formType;
    }

    public FormType getFormType() {
        return formType;
    }
    
    public String getMissionNo() {
        return missionNo;
    }

    public long getDate() {
        return date;
    }
}