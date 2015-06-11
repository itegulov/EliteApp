package ru.ifmo.ctddev.elite.query;

/**
 * @author Zakhar Voit (zakharvoit@gmail.com)
 */
public class QueryTool {
    public static void main(String[] args) {
        QueryBuilder builder = new QueryBuilder();
        QueryUI queryUI = new QueryUI(builder);
        queryUI.start();
    }
}
