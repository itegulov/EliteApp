package ru.ifmo.ctddev.elite.query;

/**
 * Main class for the querying.
 *
 * @author Zakhar Voit (zakharvoit@gmail.com)
 */
public class QueryTool {
    public static void main(String[] args) {
        QueryBuilder builder = new QueryBuilder();
        QuerySubmitter submitter = new QuerySubmitter();
        QueryUI queryUI = new QueryUI(builder, submitter);
        queryUI.start();
    }
}
