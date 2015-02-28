/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 * 
 * @author Robert
 */
public enum UserType {
    READER, WRITER, NONE;

    @Override
    public String toString() {
        switch (this) {
        case READER:
            return "READER";
        case WRITER:
            return "WRITER";
        case NONE:
            return "NONE";
        default:
            return null;
        }
    }

    public static UserType getType(String type) {
        if (type.equals("READER")) {
            return READER;
        } else if (type.equals("WRITER")) {
            return WRITER;
        } else if (type.equals("NONE")) {
            return NONE;
        } else {
            return null;
        }
    }
}
