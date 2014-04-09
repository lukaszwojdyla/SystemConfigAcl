/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.model;

/**
 *
 * @author lukasz
 */
public class Entity {

    private String name;
    private EntityType type;
    private boolean read;
    private boolean execute;
    private boolean write;

    public Entity(String name, EntityType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isExecute() {
        return execute;
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }

    public Object[] getFields() {
        return new Object[] { name, type, read, execute, write };
    }
    
    public void updateField(Object value, int columnIndex) {
        switch(columnIndex) {
            case 0:
                name = (String) value;
                break;
            case 1:
                type = (EntityType) value;
                break;
            case 2:
                read = (Boolean) value;
                break;
            case 3:
                execute = (Boolean) value;
                break;
            case 4:
                write = (Boolean) value;
                break;
        }
    }
}
