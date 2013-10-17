package edu.neu.util;

/**
 * User: yummin
 * Date: 13-10-16
 */
public interface DBHandler {
    // Add record
    public void add() throws Exception;
    // Update record
    public void update() throws Exception;
    // Search record
    public void search() throws Exception;
    // Delete record
    public void delete() throws Exception;
}
