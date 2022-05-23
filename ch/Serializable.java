package ch;

/**
 * Instances of Serializable can be transformed into nomnoml code
 */
@FunctionalInterface
public interface Serializable {
    
    /**
     * Serialize {@code this} into nomnoml code
     * @return nomnoml code describing {@code this}
     */
    public String ser();

}
