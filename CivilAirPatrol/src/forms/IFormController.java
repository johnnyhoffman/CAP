package forms;

import mvcCommon.IController;

import com.google.gson.JsonObject;

public interface IFormController extends IController {

    @Override
    public FormComponent getViewComponent();

    public abstract int getID();

    public abstract void updateFromJson(JsonObject json);

}
