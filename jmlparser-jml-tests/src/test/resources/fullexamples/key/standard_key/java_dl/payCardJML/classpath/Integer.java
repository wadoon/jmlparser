// This file is part of KeY - Integrated Deductive Software Design 
//
// Copyright (C) 2001-2011 Universitaet Karlsruhe (TH), Germany 
//                         Universitaet Koblenz-Landau, Germany
//                         Chalmers University of Technology, Sweden
// Copyright (C) 2011-2013 Karlsruhe Institute of Technology, Germany 
//                         Technical University Darmstadt, Germany
//                         Chalmers University of Technology, Sweden
//
// The KeY system is protected by the GNU General 
// Public License. See LICENSE.TXT for details.
// 

/* This file has been generated by Stubmaker (de.uka.ilkd.stubmaker)
 * Date: Wed May 14 11:55:47 CEST 2008
 */
package java.lang;

public final class Integer extends java.lang.Number implements java.lang.Comparable {
    public final static int MIN_VALUE = -2147483648;
    public final static int MAX_VALUE = 2147483647;
    public final static java.lang.Class TYPE;
    public final static int SIZE = 32;

    public static java.lang.String toString(int arg0, int arg1);

    public static java.lang.String toHexString(int arg0);

    public static java.lang.String toOctalString(int arg0);

    public static java.lang.String toBinaryString(int arg0);

    public static java.lang.String toString(int arg0);

    public static int parseInt(java.lang.String arg0, int arg1) throws java.lang.NumberFormatException;

    public static int parseInt(java.lang.String arg0) throws java.lang.NumberFormatException;

    public static java.lang.Integer valueOf(java.lang.String arg0, int arg1) throws java.lang.NumberFormatException;

    public static java.lang.Integer valueOf(java.lang.String arg0) throws java.lang.NumberFormatException;

    public static java.lang.Integer valueOf(int arg0);

    public Integer(int arg0);

    public Integer(java.lang.String arg0) throws java.lang.NumberFormatException;

    public byte byteValue();

    public short shortValue();

    public int intValue();

    public long longValue();

    public float floatValue();

    public double doubleValue();

    public java.lang.String toString();

    public int hashCode();

    public boolean equals(java.lang.Object arg0);

    public static java.lang.Integer getInteger(java.lang.String arg0);

    public static java.lang.Integer getInteger(java.lang.String arg0, int arg1);

    public static java.lang.Integer getInteger(java.lang.String arg0, java.lang.Integer arg1);

    public static java.lang.Integer decode(java.lang.String arg0) throws java.lang.NumberFormatException;

    public int compareTo(java.lang.Integer arg0);

    public static int highestOneBit(int arg0);

    public static int lowestOneBit(int arg0);

    public static int numberOfLeadingZeros(int arg0);

    public static int numberOfTrailingZeros(int arg0);

    public static int bitCount(int arg0);

    public static int rotateLeft(int arg0, int arg1);

    public static int rotateRight(int arg0, int arg1);

    public static int reverse(int arg0);

    public static int signum(int arg0);

    public static int reverseBytes(int arg0);

    public int compareTo(java.lang.Object arg0);
}
