package mvc;

import java.awt.Component;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/* Placeholder for demonstrating Session MVC */
public class RadioMessageController implements Controller {

    private RadioMessageView view;
    private RadioMessageModel model;

    public RadioMessageController(String name) {
        view = new RadioMessageView();
        view.setName(name);
        model = new RadioMessageModel(name);
        setListeners();
    }

    @Override
    public Component getViewComponent() {
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
                model.updateMessageRecievedFrom(view.getMessageRecievedFromText());
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
                model.updateMessageSentSendingOperatorInitials(view.getMessageSentSendingOperatorInitialsText());
            }
        });
    }
}