package com.github.maracas.roseau.model;


import java.util.List;

/**
 * Represents a constructor declaration within a Java type.
 * This class extends the {@link ElementDeclaration} class and contains information about the constructor's parameters, return type, class, and more.
 */
public class ConstructorDeclaration extends ElementDeclaration {

    /** The type containing the constructor. */
    private TypeDeclaration type;

    /** The return data type of the constructor. */
    private String returnType;


    /** List of the constructor's parameter data types. */
    private List<String> parametersTypes;

    /** List of referenced types for each constructor parameter. */
    private List<List<String>> parametersReferencedTypes;

    /** List of the constructor's formal type parameters. */
    private List<String> formalTypeParameters;


    /** The constructor's signature. */
    private Signature signature;

    /** List of exceptions thrown by the constructor. */
    private List<String> exceptions;




    public ConstructorDeclaration(String name, TypeDeclaration type, AccessModifier visibility, String returnType, List<String> referencedTypes, List<String> parametersTypes, List<List<String>> parametersReferencedTypes, List<String> formalTypeParameters, List<NonAccessModifiers> Modifiers, Signature signature, List<String> exceptions, String position) {
        super(name, visibility, Modifiers, referencedTypes, position);
        this.type = type;
        this.returnType = returnType;
        this.parametersTypes = parametersTypes;
        this.parametersReferencedTypes = parametersReferencedTypes;
        this.formalTypeParameters = formalTypeParameters;
        this.signature = signature;
        this.exceptions = exceptions;
    }



    /**
     * Retrieves the TypeDeclaration containing the constructor.
     * @return Type containing the constructor
     */
    public TypeDeclaration getType() {
        return type;
    }


    /**
     * Retrieves the return data type of the constructor.
     * @return Constructor's return data type
     */
    public String getReturnType() {
        return returnType;
    }


    /**
     * Retrieves the list of parameter data types of the constructor.
     * @return List of parameter data types
     */
    public List<String> getParametersTypes() {
        return parametersTypes;
    }

    /**
     * Retrieves the list of referenced types for each parameter of the constructor.
     * @return Lists of referenced types for parameters
     */
    public List<List<String>> getParametersReferencedTypes() {
        return parametersReferencedTypes;
    }

    /**
     * Retrieves the constructor's formal type parameters.
     * @return List of formal type parameters
     */
    public List<String> getFormalTypeParameters() {
        return formalTypeParameters;
    }


    /**
     * Retrieves the signature of the constructor.
     * @return The constructor's signature
     */
    public Signature getSignature() {
        return signature;
    }

    /**
     * Retrieves the list of exceptions thrown by the constructor.
     * @return List of exceptions thrown by the constructor
     */
    public List<String> getExceptions() {
        return exceptions;
    }

    /**
     * Generates a string representation of the ConstructorDeclaration.
     *
     * @return A formatted string containing the constructor's name, type, return type, parameter types,
     * visibility, modifiers, exceptions, and position.
     */
    @Override
    public String toString() {
        return "Constructor Name: " + getName() + "\n" +
                "Type: " + getType().getName() + "\n" +
                "Return Type: " + getReturnType() + "\n" +
                "Parameter Types: " + getParametersTypes() + "\n" +
                "Visibility: " + getVisibility() + "\n" +
                "Modifiers: " + getModifiers() + "\n" +
                "Exceptions: " + getExceptions() + "\n" +
                "Position: " + getPosition() + "\n\n" ;
    }




}
