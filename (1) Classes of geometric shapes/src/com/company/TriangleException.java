package com.company;

public class TriangleException extends Exception{
    public String getLocalizedMessage()
    {
        return "This is definitely a error";
    }
    public String isNotTriangle()
    {
        return "Error. Bad sides of triangle.";
    }
}
