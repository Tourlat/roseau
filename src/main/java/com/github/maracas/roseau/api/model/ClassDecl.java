package com.github.maracas.roseau.api.model;

import com.github.maracas.roseau.api.model.reference.TypeReference;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A class declaration is a {@link TypeDecl} with an optional superclass and list of {@link ConstructorDecl}.
 */
public sealed class ClassDecl extends TypeDecl permits RecordDecl, EnumDecl {
	protected final TypeReference<ClassDecl> superClass;

	protected final List<ConstructorDecl> constructors;

	public ClassDecl(String qualifiedName, AccessModifier visibility, List<Modifier> modifiers,
	                 List<Annotation> annotations, SourceLocation location,
	                 List<TypeReference<InterfaceDecl>> implementedInterfaces,
	                 List<FormalTypeParameter> formalTypeParameters, List<FieldDecl> fields, List<MethodDecl> methods,
	                 TypeReference<TypeDecl> enclosingType, TypeReference<ClassDecl> superClass,
	                 List<ConstructorDecl> constructors) {
		super(qualifiedName, visibility, modifiers, annotations, location,
			implementedInterfaces, formalTypeParameters, fields, methods, enclosingType);
		this.superClass = superClass;
		this.constructors = Objects.requireNonNull(constructors);
	}

	@Override
	public boolean isClass() {
		return true;
	}

	public boolean isCheckedException() {
		List<String> superClasses = getAllSuperClasses().map(TypeReference::getQualifiedName).toList();

		return "java.lang.Exception".equals(qualifiedName)
			|| (superClasses.contains("java.lang.Exception") && !isUncheckedException());
	}

	public boolean isUncheckedException() {
		List<String> superClasses = getAllSuperClasses().map(TypeReference::getQualifiedName).toList();

		return "java.lang.RuntimeException".equals(qualifiedName) || superClasses.contains("java.lang.RuntimeException");
	}

	@Override
	public boolean isEffectivelyFinal() {
		// A class without a subclass-accessible constructor cannot be extended
		// If the class had a default constructor, it would be there
		return super.isEffectivelyFinal() || constructors.isEmpty();
	}

	@Override
	public Stream<TypeReference<? extends TypeDecl>> getAllSuperTypes() {
		return Stream.concat(
			super.getAllSuperTypes(),
			getAllSuperClasses()
				.flatMap(ref -> Stream.concat(
					Stream.of(ref),
					ref.getResolvedApiType()
						.map(ClassDecl::getAllSuperTypes)
						.orElseGet(Stream::empty)))
		).distinct();
	}

	public Stream<TypeReference<ClassDecl>> getAllSuperClasses() {
		return superClass == null
			? Stream.empty()
			: Stream.concat(
					Stream.of(superClass),
					superClass.getResolvedApiType().map(ClassDecl::getAllSuperClasses).orElseGet(Stream::empty));
	}

	public Optional<TypeReference<ClassDecl>> getSuperClass() {
		return Optional.ofNullable(superClass);
	}

	public List<ConstructorDecl> getConstructors() {
		return Collections.unmodifiableList(constructors);
	}

	@Override
	public String toString() {
		return """
			%s class %s
			  %s
			  %s
			  %s
			""".formatted(visibility, qualifiedName, constructors, fields, methods);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		ClassDecl classDecl = (ClassDecl) o;
		return Objects.equals(superClass, classDecl.superClass) && Objects.equals(constructors, classDecl.constructors);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), superClass, constructors);
	}
}
