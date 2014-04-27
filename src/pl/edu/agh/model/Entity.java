/* 
 * Copyright (C) 2014 lukasz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

    public Entity() {
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
    
    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }
    
    public boolean isExecute() {
        return execute;
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }

    public Object[] getFields() {
        return new Object[] { name, type, read, write,  execute };
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
                write = (Boolean) value;         
                break;
            case 4:
                execute = (Boolean) value;
                break;
        }
    }
}
