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
public enum DefaultUserType {

    DEFAULT("-----");

    private DefaultUserType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    private final String value;
}
