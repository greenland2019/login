/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.services;

import java.util.List;

/**
 *
 * @author Eplants
 */
public interface IEplants<T> {
    
      public void insert(T o);
    public void delete(T o);
    public boolean check(T o);
    public List<T> displayAll();
    public T displayById(int id);
    
    public boolean update(T os);
}
