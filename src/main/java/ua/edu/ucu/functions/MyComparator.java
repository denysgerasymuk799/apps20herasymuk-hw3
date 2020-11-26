/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.ucu.functions;

import java.util.Comparator;

/**
 *
 * @author Andrii_Rodionov
 */
public interface MyComparator extends Comparator<Object> {
    @Override
    int compare(Object a, Object b);
}
