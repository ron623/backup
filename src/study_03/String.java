package study_03;

import java.io.ObjectStreamField;
import java.util.Arrays;




	   /**
	    * The <code>String</code> class represents character strings. All
	    * string literals in Java programs, such as <code>"abc"</code>, are
	    * implemented as instances of this class.
	    * <p>
	    * Strings are constant; their values cannot be changed after they
	    * are created. String buffers support mutable strings.
	    * Because String objects are immutable they can be shared. For example:
	    * <p><blockquote><pre>
	    *     String str = "abc";
	    * </pre></blockquote><p>
	    * is equivalent to:
	    * <p><blockquote><pre>
	    *     char data[] = {'a', 'b', 'c'};
	    *     String str = new String(data);
	    * </pre></blockquote><p>
	    * Here are some more examples of how strings can be used:
	    * <p><blockquote><pre>
	    *     System.out.println("abc");
	    *     String cde = "cde";
	    *     System.out.println("abc" + cde);
	    *     String c = "abc".substring(2,3);
	    *     String d = cde.substring(1, 2);
	    * </pre></blockquote>
	    * <p>
	    * The class <code>String</code> includes methods for examining
	    * individual characters of the sequence, for comparing strings, for
	    * searching strings, for extracting substrings, and for creating a
	    * copy of a string with all characters translated to uppercase or to
	    * lowercase. Case mapping is based on the Unicode Standard version
	    * specified by the {@link java.lang.Character Character} class.
	    * <p>
	    * The Java language provides special support for the string
	    * concatenation operator (&nbsp;+&nbsp;), and for conversion of
	    * other objects to strings. String concatenation is implemented
	    * through the <code>StringBuilder</code>(or <code>StringBuffer</code>)
	    * class and its <code>append</code> method.
	    * String conversions are implemented through the method
	    * <code>toString</code>, defined by <code>Object</code> and
	    * inherited by all classes in Java. For additional information on
	    * string concatenation and conversion, see Gosling, Joy, and Steele,
	    * <i>The Java Language Specification</i>.
	    *
	    * <p> Unless otherwise noted, passing a <tt>null</tt> argument to a constructor
	    * or method in this class will cause a {@link NullPointerException} to be
	    * thrown.
	    *
	    * <p>A <code>String</code> represents a string in the UTF-16 format
	    * in which <em>supplementary characters</em> are represented by <em>surrogate
	    * pairs</em> (see the section <a href="Character.html#unicode">Unicode
	    * Character Representations</a> in the <code>Character</code> class for
	    * more information).
	    * Index values refer to <code>char</code> code units, so a supplementary
	    * character uses two positions in a <code>String</code>.
	    * <p>The <code>String</code> class provides methods for dealing with
	    * Unicode code points (i.e., characters), in addition to those for
	    * dealing with Unicode code units (i.e., <code>char</code> values).
	    *
	    * @author  Lee Boynton
	    * @author  Arthur van Hoff
	    * @author  Martin Buchholz
	    * @author  Ulf Zibis
	    * @see     java.lang.Object#toString()
	    * @see     java.lang.StringBuffer
	    * @see     java.lang.StringBuilder
	    * @see     java.nio.charset.Charset
	    * @since   JDK1.0
	    */

	   public final class String
	       implements java.io.Serializable, Comparable<String>, CharSequence
	   {
	       /** The value is used for character storage. */
	       private final char value[];

	       /** The offset is the first index of the storage that is used. */
	       private final int offset;

	       /** The count is the number of characters in the String. */
	       private final int count;

	       /** Cache the hash code for the string */
	       private int hash; // Default to 0

	       /** use serialVersionUID from JDK 1.0.2 for interoperability */
	       private static final long serialVersionUID = -6849794470754667710L;

	       /**
	        * Class String is special cased within the Serialization Stream Protocol.
	        *
	        * A String instance is written initially into an ObjectOutputStream in the
	        * following format:
	        * <pre>
	        *      <code>TC_STRING</code> (utf String)
	        * </pre>
	        * The String is written by method <code>DataOutput.writeUTF</code>.
	        * A new handle is generated to  refer to all future references to the
	        * string instance within the stream.
	        */
	       private static final ObjectStreamField[] serialPersistentFields =
	           new ObjectStreamField[0];

	       /**
	        * Initializes a newly created {@code String} object so that it represents
	        * an empty character sequence.  Note that use of this constructor is
	        * unnecessary since Strings are immutable.
	        */
	       public String() {
	           this.offset = 0;
	           this.count = 0;
	           this.value = new char[0];
	       }

	       /**
	        * Initializes a newly created {@code String} object so that it represents
	        * the same sequence of characters as the argument; in other words, the
	        * newly created string is a copy of the argument string. Unless an
	        * explicit copy of {@code original} is needed, use of this constructor is
	        * unnecessary since Strings are immutable.
	        *
	        * @param  original
	        *         A {@code String}
	        */
	       public String(String original) {
	           int size = original.count;
	           char[] originalValue = original.value;
	           char[] v;
	           if (originalValue.length > size) {
	               // The array representing the String is bigger than the new
	               // String itself.  Perhaps this constructor is being called
	               // in order to trim the baggage, so make a copy of the array.
	               int off = original.offset;
	               v = Arrays.copyOfRange(originalValue, off, off+size);
	           } else {
	               // The array representing the String is the same
	               // size as the String, so no point in making a copy.
	               v = originalValue;
	           }
	           this.offset = 0;
	           this.count = size;
	           this.value = v;
	       }

	       /**
	        * Allocates a new {@code String} so that it represents the sequence of
	        * characters currently contained in the character array argument. The
	        * contents of the character array are copied; subsequent modification of
	        * the character array does not affect the newly created string.
	        *
	        * @param  value
	        *         The initial value of the string
	        */
	       public String(char value[]) {
	           int size = value.length;
	           this.offset = 0;
	           this.count = size;
	           this.value = Arrays.copyOf(value, size);
	       }

	       /**
	        * Allocates a new {@code String} that contains characters from a subarray
	        * of the character array argument. The {@code offset} argument is the
	        * index of the first character of the subarray and the {@code count}
	        * argument specifies the length of the subarray. The contents of the
	        * subarray are copied; subsequent modification of the character array does
	        * not affect the newly created string.
	        *
	        * @param  value
	        *         Array that is the source of characters
	        *
	        * @param  offset
	        *         The initial offset
	        *
	        * @param  count
	        *         The length
	        *
	        * @throws  IndexOutOfBoundsException
	        *          If the {@code offset} and {@code count} arguments index
	        *          characters outside the bounds of the {@code value} array
	        */
	       public String(char value[], int offset, int count) {
	           if (offset < 0) {
	               throw new StringIndexOutOfBoundsException(offset);
	           }
	           if (count < 0) {
	               throw new StringIndexOutOfBoundsException(count);
	           }
	           // Note: offset or count might be near -1>>>1.
	           if (offset > value.length - count) {
	               throw new StringIndexOutOfBoundsException(offset + count);
	           }
	           this.offset = 0;
	           this.count = count;
	           this.value = Arrays.copyOfRange(value, offset, offset+count);
	       }



	       /**
	        * Allocates a new {@code String} constructed from a subarray of an array
	        * of 8-bit integer values.
	        *
	        * <p> The {@code offset} argument is the index of the first byte of the
	        * subarray, and the {@code count} argument specifies the length of the
	        * subarray.
	        *
	        * <p> Each {@code byte} in the subarray is converted to a {@code char} as
	        * specified in the method above.
	        *
	        * @deprecated This method does not properly convert bytes into characters.
	        * As of JDK&nbsp;1.1, the preferred way to do this is via the
	        * {@code String} constructors that take a {@link
	        * java.nio.charset.Charset}, charset name, or that use the platform's
	        * default charset.
	        *
	        * @param  ascii
	        *         The bytes to be converted to characters
	        *
	        * @param  hibyte
	        *         The top 8 bits of each 16-bit Unicode code unit
	        *
	        * @param  offset
	        *         The initial offset
	        * @param  count
	        *         The length
	        *
	        * @throws  IndexOutOfBoundsException
	        *          If the {@code offset} or {@code count} argument is invalid
	        *
	        * @see  #String(byte[], int)
	        * @see  #String(byte[], int, int, java.lang.String)
	        * @see  #String(byte[], int, int, java.nio.charset.Charset)
	        * @see  #String(byte[], int, int)
	        * @see  #String(byte[], java.lang.String)
	        * @see  #String(byte[], java.nio.charset.Charset)
	        * @see  #String(byte[])
	        */
	       @Deprecated
	       public String(byte ascii[], int hibyte, int offset, int count) {
	           checkBounds(ascii, offset, count);
	           char value[] = new char[count];

	           if (hibyte == 0) {
	               for (int i = count ; i-- > 0 ;) {
	                   value[i] = (char) (ascii[i + offset] & 0xff);
	               }
	           } else {
	               hibyte <<= 8;
	               for (int i = count ; i-- > 0 ;) {
	                   value[i] = (char) (hibyte | (ascii[i + offset] & 0xff));
	               }
	           }
	           this.offset = 0;
	           this.count = count;
	           this.value = value;
	       }

	       /**
	        * Allocates a new {@code String} containing characters constructed from
	        * an array of 8-bit integer values. Each character <i>c</i>in the
	        * resulting string is constructed from the corresponding component
	        * <i>b</i> in the byte array such that:
	        *
	        * <blockquote><pre>
	        *     <b><i>c</i></b> == (char)(((hibyte &amp; 0xff) &lt;&lt; 8)
	        *                         | (<b><i>b</i></b> &amp; 0xff))
	        * </pre></blockquote>
	        *
	        * @deprecated  This method does not properly convert bytes into
	        * characters.  As of JDK&nbsp;1.1, the preferred way to do this is via the
	        * {@code String} constructors that take a {@link
	        * java.nio.charset.Charset}, charset name, or that use the platform's
	        * default charset.
	        *
	        * @param  ascii
	        *         The bytes to be converted to characters
	        *
	        * @param  hibyte
	        *         The top 8 bits of each 16-bit Unicode code unit
	        *
	        * @see  #String(byte[], int, int, java.lang.String)
	        * @see  #String(byte[], int, int, java.nio.charset.Charset)
	        * @see  #String(byte[], int, int)
	        * @see  #String(byte[], java.lang.String)
	        * @see  #String(byte[], java.nio.charset.Charset)
	        * @see  #String(byte[])
	        */
	       @Deprecated
	       public String(byte ascii[], int hibyte) {
	           this(ascii, hibyte, 0, ascii.length);
	       }

	       /* Common private utility method used to bounds check the byte array
	        * and requested offset & length values used by the String(byte[],..)
	        * constructors.
	        */
	       private static void checkBounds(byte[] bytes, int offset, int length) {
	           if (length < 0)
	               throw new StringIndexOutOfBoundsException(length);
	           if (offset < 0)
	               throw new StringIndexOutOfBoundsException(offset);
	           if (offset > bytes.length - length)
	               throw new StringIndexOutOfBoundsException(offset + length);
	       }





	/**
	 * Returns a new string that is a substring of this string. The substring
	 * begins with the character at the specified index and extends to the end
	 * of this string.
	 * <p>
	 * Examples: <blockquote>
	 *
	 * <pre>
	 * "unhappy".substring(2) returns "happy"
	 * "Harbison".substring(3) returns "bison"
	 * "emptiness".substring(9) returns "" (an empty string)
	 * </pre>
	 *
	 * </blockquote>
	 *
	 * @param beginIndex
	 *            the beginning index, inclusive.
	 * @return the specified substring.
	 * @exception IndexOutOfBoundsException
	 *                if <code>beginIndex</code> is negative or larger than the
	 *                length of this <code>String</code> object.
	 */
	public String substring(int beginIndex) {
		return substring(beginIndex, count);
	}
	
    /**
     * Allocates a new string that contains the sequence of characters
     * currently contained in the string builder argument. The contents of the
     * string builder are copied; subsequent modification of the string builder
     * does not affect the newly created string.
     *
     * <p> This constructor is provided to ease migration to {@code
     * StringBuilder}. Obtaining a string from a string builder via the {@code
     * toString} method is likely to run faster and is generally preferred.
     *
     * @param   builder
     *          A {@code StringBuilder}
     *
     * @since  1.5
     */
    public String(StringBuilder builder) {
        String result = builder.toString();
        this.value = result.value;
        this.count = result.count;
        this.offset = result.offset;
    }


    // Package private constructor which shares value array for speed.
    String(int offset, int count, char value[]) {
        this.value = value;
        this.offset = offset;
        this.count = count;
    }

	/**
	 * Returns a new string that is a substring of this string. The substring
	 * begins at the specified <code>beginIndex</code> and extends to the
	 * character at index <code>endIndex - 1</code>. Thus the length of the
	 * substring is <code>endIndex-beginIndex</code>.
	 * <p>
	 * Examples: <blockquote>
	 *
	 * <pre>
	 * "hamburger".substring(4, 8) returns "urge"
	 * "smiles".substring(1, 5) returns "mile"
	 * </pre>
	 *
	 * </blockquote>
	 *
	 * @param beginIndex
	 *            the beginning index, inclusive.
	 * @param endIndex
	 *            the ending index, exclusive.
	 * @return the specified substring.
	 * @exception IndexOutOfBoundsException
	 *                if the <code>beginIndex</code> is negative, or
	 *                <code>endIndex</code> is larger than the length of this
	 *                <code>String</code> object, or <code>beginIndex</code> is
	 *                larger than <code>endIndex</code>.
	 */
	public String substring(int beginIndex, int endIndex) {
		if (beginIndex < 0) {
			throw new StringIndexOutOfBoundsException(beginIndex);
		}
		if (endIndex > count) {
			throw new StringIndexOutOfBoundsException(endIndex);
		}
		if (beginIndex > endIndex) {
			throw new StringIndexOutOfBoundsException(endIndex - beginIndex);
		}
		return ((beginIndex == 0) && (endIndex == count)) ? this : new String(
				offset + beginIndex, endIndex - beginIndex, value);
	}

	@Override
	public int length() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public char charAt(int index) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public int compareTo(String o) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}


}
