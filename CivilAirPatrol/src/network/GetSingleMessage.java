package network;

import forms.ScheduledPushModelAbstraction.FormType;

/**
 * GetSingleMessage will be used to send a request to the server for a single
 * form
 */
public class GetSingleMessage extends NetworkMessage {

    private static final long serialVersionUID = 9034689547424270973L;
    private int uid;
    private FormType formType;

    public GetSingleMessage(int uid, FormType formType) {
        super(MessageType.GET_SINGLE);
        this.uid = uid;
        this.formType = formType;
    }

    public int getUID() {
        return this.uid;
    }

    public FormType getFormType() {
        return this.formType;
    }
}
