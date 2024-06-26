package com.github.maracas.roseau.diff.changes;

import static com.github.maracas.roseau.diff.changes.BreakingChangeNature.ADDITION;
import static com.github.maracas.roseau.diff.changes.BreakingChangeNature.DELETION;
import static com.github.maracas.roseau.diff.changes.BreakingChangeNature.MUTATION;

/*
 * Kinds of breaking changes that can be detected.
 *
 * FIXME: we're currently mostly focused on binary compatibility without
 * much thought given to source compatibility except for generics.
 * We'll probably have to map BCs to their impact on binary/source compatibility
 * and refine some (e.g., METHOD_RETURN_TYPE_CHANGED is too broad: every return
 * type change is binary-incompatible, only some are source-incompatible).
 *
 * It might make sense at some point to merge BC kinds regardless of the declaration
 * they apply to (e.g., TYPE/METHOD_FORMAL_TYPE_PARAMETERS_*, *_REMOVED, or *_PROTECTED.
 */
public enum BreakingChangeKind {
	TYPE_REMOVED(DELETION),
	CLASS_NOW_ABSTRACT(MUTATION),
	CLASS_NOW_FINAL(MUTATION),
	NESTED_CLASS_NOW_STATIC(MUTATION),
	NESTED_CLASS_NO_LONGER_STATIC(MUTATION),
	CLASS_TYPE_CHANGED(MUTATION),
	CLASS_NOW_CHECKED_EXCEPTION(MUTATION),
	TYPE_NOW_PROTECTED(MUTATION),
	SUPERTYPE_REMOVED(MUTATION),
	TYPE_FORMAL_TYPE_PARAMETERS_ADDED(MUTATION),
	TYPE_FORMAL_TYPE_PARAMETERS_REMOVED(MUTATION),
	TYPE_FORMAL_TYPE_PARAMETERS_CHANGED(MUTATION),
	/**
	 * A breaking change where a method has been removed or its accessibility has
	 * been reduced.
	 * <p>
	 * This occurs when a method that was present in the old version of a library is
	 * either:
	 * <ul>
	 * <li>No longer present in the new version.
	 * <li>Its visibility has been reduced, making it inaccessible from some or all of
	 * the previously allowed access points.
	 * <li>It has been overloaded or its parameters have been changed, effectively
	 * removing the original method.
	 * <li> A class or interface no longer extends or implements the parent class or
	 * interface that contains the method.
	 * </ul>
	 */
	METHOD_REMOVED(DELETION),
	METHOD_NOW_PROTECTED(MUTATION),
	METHOD_RETURN_TYPE_CHANGED(MUTATION),
	METHOD_NOW_ABSTRACT(MUTATION),
	METHOD_NOW_FINAL(MUTATION),
	METHOD_NOW_STATIC(MUTATION),
	METHOD_NO_LONGER_STATIC(MUTATION),
	METHOD_NO_LONGER_VARARGS(MUTATION),
	METHOD_NOW_THROWS_CHECKED_EXCEPTION(MUTATION),
	METHOD_NO_LONGER_THROWS_CHECKED_EXCEPTION(MUTATION),
	METHOD_ABSTRACT_ADDED_TO_CLASS(ADDITION),
	METHOD_ADDED_TO_INTERFACE(ADDITION),
	METHOD_FORMAL_TYPE_PARAMETERS_ADDED(MUTATION),
	METHOD_FORMAL_TYPE_PARAMETERS_REMOVED(MUTATION),
	METHOD_FORMAL_TYPE_PARAMETERS_CHANGED(MUTATION),
	METHOD_PARAMETER_GENERICS_CHANGED(MUTATION),

	FIELD_NOW_FINAL(MUTATION),
	FIELD_NOW_STATIC(MUTATION),
	FIELD_NO_LONGER_STATIC(MUTATION),
	FIELD_TYPE_CHANGED(MUTATION),
	FIELD_REMOVED(DELETION),
	FIELD_NOW_PROTECTED(MUTATION),

	CONSTRUCTOR_REMOVED(DELETION),
	CONSTRUCTOR_NOW_PROTECTED(MUTATION);

	/*
	 * To implement, maybe
	 * TYPE_GENERICS_CHANGED
	 * 
	 * FIELD_GENERICS_CHANGED
	 * 
	 * METHOD_RETURN_TYPE_GENERICS_CHANGED
	 * METHOD_PARAMETER_GENERICS_CHANGED
	 * 
	 * CONSTRUCTOR_PARAMS_GENERICS_CHANGED
	 * CONSTRUCTOR_GENERICS_CHANGED
	 * CONSTRUCTOR_FORMAL_TYPE_PARAMETERS_CHANGED
	 * CONSTRUCTOR_FORMAL_TYPE_PARAMETERS_ADDED
	 * CONSTRUCTOR_FORMAL_TYPE_PARAMETERS_REMOVED
	 */

	/*
	 * Compatible changes
	 * ANNOTATION_DEPRECATED_ADDED
	 * METHOD_NEW_DEFAULT
	 * METHOD_MOVED_TO_SUPERCLASS
	 * METHOD_NOW_VARARGS
	 * METHOD_ABSTRACT_NOW_DEFAULT: ???
	 */

	/*
	 * Hierarchy-related that are irrelevant for us?
	 * METHOD_LESS_ACCESSIBLE_THAN_IN_SUPERCLASS
	 * METHOD_IS_STATIC_AND_OVERRIDES_NOT_STATIC
	 * METHOD_IS_NOT_STATIC_AND_OVERRIDES_STATIC
	 * FIELD_STATIC_AND_OVERRIDES_NON_STATIC
	 * FIELD_NON_STATIC_AND_OVERRIDES_STATIC
	 * FIELD_LESS_ACCESSIBLE_THAN_IN_SUPERCLASS
	 */

	private final BreakingChangeNature nature;

	BreakingChangeKind(BreakingChangeNature nature) {
		this.nature = nature;
	}

	public BreakingChangeNature getNature() {
		return nature;
	}
}
