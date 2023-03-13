package functionalClasses;

/**
 * Sets values of the variables
 */

public class SetValues {
    private final int key;
    private final String valueType;
    private final boolean isRequired;
    private final String comment;


    /**
     * Constructs a new SetValues object with the specified key, value type, requirement status, and comment.
     *
     * @param key the key associated with this set of values
     * @param valueType the value type of this set of values
     * @param isRequired indicates whether this set of values is required
     * @param comment a comment associated with this set of values
     */
    public SetValues(int key, String valueType, boolean isRequired, String comment) {
        this.key = key;
        this.valueType = valueType;
        this.isRequired = isRequired;
        this.comment = comment;
    }
    /**
     * Returns the key associated with this set of values.
     *
     * @return the key associated with this set of values
     */
    public int getKey(){
        return key;
    }

    /**
     * Returns the value type of this set of values.
     *
     * @return the value type of this set of values
     */
    public String getValueType(){
        return valueType;
    }

    /**
     * Returns whether this set of values is required.
     *
     * @return true if this set of values is required, false otherwise
     */
    public boolean getIsRequired(){
        return isRequired;
    }

    /**
     * Returns the comment associated with this set of values.
     *
     * @return the comment associated with this set of values
     */
    public String getComment(){
        return comment;
    }

}
