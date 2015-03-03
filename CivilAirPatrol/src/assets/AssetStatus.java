package assets;

import java.awt.Color;
import java.io.Serializable;

public class AssetStatus implements Serializable {
    private static final long serialVersionUID = 4964815272589154545L;
    private String name;
    private String time;
    private Color color;

    public AssetStatus(String name, String time, Color color) {
        this.name = name;
        this.time = time;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        if (time == null) {
            return name;
        } else {
            return name + " - " + time;
        }
    }
}
