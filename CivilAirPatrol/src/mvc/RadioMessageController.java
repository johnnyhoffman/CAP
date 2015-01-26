package mvc;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.google.gson.JsonObject;

import mvc.ScheduledPushModelAbstraction.OnModelLoadListener;

/* Placeholder for demonstrating Session MVC */
public class RadioMessageController implements IFormController {

    private RadioMessageView view;
    private RadioMessageModel model;

    public RadioMessageController(int id, String name) {
        view = new RadioMessageView();
        view.setName(name);
        model = new RadioMessageModel(id, name);
        setListeners();
    }

    public RadioMessageController(int id, JsonObject json) {
        view = new RadioMessageView();
        view.setName(json.get("name").getAsString());
        model = new RadioMessageModel(id, json);
        setListeners();
        refreshViewFromModel();
    }

    @Override
    public FormComponent getViewComponent() {
        return view;
    }

    /*
     * Set the listeners for all of the actions we want to keep track of in the
     * view.
     */
    public void setListeners() {
        view.addHeaderMsgNumChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderMsgNum(view.getHeaderMsgNumText());
            }
        });
        view.addHeaderPrecedenceChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderPrecedence(view.getHeaderPrecedenceText());
            }
        });
        view.addHeaderDtgChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderDtg(view.getHeaderDtgText());
            }
        });
        view.addHeaderFromChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderFrom(view.getHeaderFromText());
            }
        });
        view.addHeaderToChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderTo(view.getHeaderToText());
            }
        });
        view.addHeaderInfoChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderInfo(view.getHeaderInfoText());
            }
        });
        view.addHeaderSubjChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderSubj(view.getHeaderSubjText());
            }
        });
        view.addHeaderGroupCntChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateHeaderGroupCnt(view.getHeaderGroupCntText());
            }
        });
        view.addMessageChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateMessage(view.getMessageText());
            }
        });
        view.addMessageRecievedFromChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateMessageRecievedFrom(view
                        .getMessageRecievedFromText());
            }
        });
        view.addMessageRecievedDtgChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateMessageRecievedDtg(view.getMessageRecievedDtgText());
            }
        });
        view.addMessageRecievedRecievingOperatorInitialsChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateMessageRecievedRecievingOperatorInitials(view
                        .getMessageRecievedRecievingOperatorInitialsText());
            }
        });
        view.addMessageSentToChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateMessageSentTo(view.getMessageSentToText());
            }
        });
        view.addMessageSentDtgChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateMessageSentDtg(view.getMessageSentDtgText());
            }
        });
        view.addMessageSentSendingOperatorInitialsChangeListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                model.updateMessageSentSendingOperatorInitials(view
                        .getMessageSentSendingOperatorInitialsText());
            }
        });

        view.setOnCloseListener(new FormComponent.OnCloseListener() {

            @Override
            public void onClose() {
                model.push();
            }
        });

        model.setOnModelLoadListener(new OnModelLoadListener() {
            @Override
            public void onModelLoad() {
                refreshViewFromModel();
            }
        });
    }

    public void refreshViewFromModel() {
        view.setHeaderMsgNumText(model.getHeaderMsgNum());
        view.setHeaderPrecedenceText(model.getHeaderPrecedence());
        view.setHeaderDtgText(model.getHeaderDtg());
        view.setHeaderFromText(model.getHeaderFrom());
        view.setHeaderToText(model.getHeaderTo());
        view.setHeaderInfoText(model.getHeaderInfo());
        view.setHeaderSubjText(model.getHeaderSubj());
        view.setHeaderGroupCntText(model.getHeaderGroupCnt());
        view.setMessageText(model.getMessage());
        view.setMessageRecievedFromText(model.getMessageRecievedFrom());
        view.setMessageRecievedDtgText(model.getMessageRecievedDtg());
        view.setMessageRecievedRecievingOperatorInitialsText(model
                .getMessageRecievedRecievingOperatorInitials());
        view.setMessageSentToText(model.getMessageSentTo());
        view.setMessageSentDtgText(model.getMessageSentDtg());
        view.setMessageSentSendingOperatorInitialsText(model
                .getMessageSentSendingOperatorInitials());
    }
}