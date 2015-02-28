package forms;

import mvcCommon.IController;

import com.google.gson.JsonObject;
import common.OnConnectionErrorListener;

public interface IFormController extends IController {

    @Override
    public FormComponent getViewComponent();

    public abstract int getID();

    public abstract void updateFromJson(JsonObject json);

    public void setOnConnectionErrorListener(OnConnectionErrorListener l);

}
